package discovery;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Background task which handles finding the addresses of other web services and
 * responding to requests for the address of this web service or the addresses of
 * web services known by this web service.</br>
 * Uses /config/service.properties to store settings.</br>
 * Uses /config/addresses.properties to store ip addresses.
 * This class is a singleton and can not be constructed directly.
 */
public class DiscoveryTask {
	private static Logger logger = Logger.getLogger(DiscoveryTask.class.getName());
	/**
	 * The path of the ip addresses file in the format modified_package_name/name
	 * ("." in the package name should be replaced with "/").</br>
	 * TODO: Currently read-only, should be writeable as well,
	 * see also addAddress() and removeAddress().
	 */
	private static final String addressesPath = "/config/addresses.properties";
	
	/**
	 * The path of the configuration file in the format modified_package_name/name
	 * ("." in the package name should be replaced with "/").
	 */
	private static final String propertiesPath = "/config/service.properties";
	
	/**
	 * The instance of {@link discovery.DiscoveryTask}.
	 */
	private static DiscoveryTask instance;
	
	/**
	 * Whether this task is running or not.
	 */
	private boolean running;
	
	/**
	 * Whether this task is busy reopening the socket or not.</br>
	 * This prevents multiple reopening attempts at the same time.
	 */
	private boolean reopeningSocket;
	
	/**
	 * The socket used to send and receive packets.
	 */
	private DatagramSocket socket;
	
	/**
	 * The instance of {@link discovery.DiscoveryRequestThread} used to send
	 * requests.
	 */
	private DiscoveryRequestThread discoveryRequestThread;
	
	/**
	 * The instance of {@link discovery.DiscoveryResponseThread} used to
	 * receive requests and send responses.
	 */
	private DiscoveryResponseThread discoveryResponseThread;
	
	
	/**
	 * The time in milliseconds between requests for addresses of required
	 * services for which no address is currently known.
	 */
	private int requestWaitMs;
	
	/**
	 * The port of this web service
	 */
	private int port;
	
	/**
	 * The service type of this web service.
	 */
	private String serviceType;
	
	/**
	 * The service types for which this web service requires addresses.
	 */
	private List<String> requiredServices;
	/**
	 * The addresses of required services found so far.
	 */
	private HashMap<String, List<String>> addresses;
	
	private DiscoveryTask() {
		this.running = false;
		this.addresses = new HashMap<String, List<String>>();
		this.socket = null;
		Properties serviceProp = new Properties();
		Properties addressesProp = new Properties();
		InputStream input = null;
		String tmpport = null;
		String tmpwait = null;
		try {
			input = this.getClass().getResourceAsStream(propertiesPath);
			serviceProp.load(input);		
			
			tmpwait = serviceProp.getProperty("requestWaitMs", "");
			tmpport = serviceProp.getProperty("port", "");
			this.serviceType = serviceProp.getProperty("serviceType", "");
			this.requiredServices = Arrays.asList(serviceProp.getProperty(serviceType, "").split(","));
			
			if (tmpwait.equals("")) {
				logger.severe("requestWaitMs key is missing or empty in " + propertiesPath);
			} else {
				this.requestWaitMs = Integer.parseInt(tmpwait);
				logger.info("Request wait time (ms) is " + tmpwait);
			}
			if (tmpport.equals("")) {
				logger.severe("port key is missing or empty in " + propertiesPath);
			} else {
				this.port = Integer.parseInt(tmpport);
			}
			if (serviceType.equals("")) {
				logger.severe("serviceType key is missing or empty in " + propertiesPath);
			}
			if (requiredServices == null) {
				logger.severe("requiredServices key is missing or empty in " + propertiesPath);
			} else {
				for (String service : requiredServices) {
					addresses.put(service, new ArrayList<String>());
				}
			}
		} catch (IOException e) {
			logger.severe("Could not open " + propertiesPath + " (" + e.getMessage() + ")");
		}
		
		this.discoveryRequestThread = new DiscoveryRequestThread(this, requestWaitMs);
		this.discoveryResponseThread = new DiscoveryResponseThread(this);
		
		try {
			input = this.getClass().getResourceAsStream(addressesPath);
			addressesProp.load(input);
			
			for (Service service : Service.values()) {
				String address = addressesProp.getProperty(service.toString(), "");
				if (!address.equals("")) {
					this.addAddress(service.toString(), address);
				}
			}
		} catch (IOException e) {
			logger.severe("Could not open " + addressesPath + " (" + e.getMessage() + ")");
		}
		
		logger.info("Discovery service for " + serviceType + " started on port " + port + ", required services " + Arrays.toString(requiredServices.toArray()));
	}
	
	/**
	 * Returns the instance of this class.
	 */
	public static DiscoveryTask getInstance() {
		if (instance == null) {
			instance = new DiscoveryTask();
		}
		return instance;
	}
	
	/**
	 * Starts the task.
	 */
	public void start() {
		if (running != true) {
			this.running = true;
			
			openSocket();
			this.discoveryRequestThread.start();
			this.discoveryResponseThread.start();
		} else {
			logger.warning("Trying to start a task which is already running");
		}
	}

	/**
	 * Stops the task.
	 */
	public void stop() {
		if (running == true) {
			this.running = false;
			
			this.discoveryRequestThread.stop();
			this.discoveryResponseThread.stop();
			
			while (this.discoveryRequestThread.isRunning() ||
					this.discoveryResponseThread.isRunning()) {
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					logger.severe("Interrupted while sleeping (" + e.getMessage() + ")");
				}
			}
			
			closeSocket();
		} else {
			logger.warning("Trying to stop a task which is not running");
		}
	}
	
	/**
	 * Restarts the task.
	 */
	public void restart() {
		if (running = true) {
			this.stop();
		}
		this.start();
	}
	
	/**
	 * Opens the socket.
	 */
	public void openSocket() {
		while (socket == null || socket.isClosed()) {
			try {
				socket = new DatagramSocket(port, InetAddress.getByName("0.0.0.0"));
				socket.setBroadcast(true);
			} catch (SocketException e) {
				//logger.severe("Could not create or access socket: " + socket + " (" + e.getMessage() + " )");
				closeSocket();
			} catch (UnknownHostException e) {
				logger.severe("Host could not be found (" + e.getMessage() + " )");
				closeSocket();
			}
		}
	}
	
	/**
	 * Closes the socket.
	 */
	public void closeSocket() {
		if (socket != null) {
			socket.close();
		}
	}
	
	/**
	 * Closes and reopens the socket.
	 */
	public void reopenSocket() {
		if (!reopeningSocket) {
			reopeningSocket = true;
			closeSocket();
			openSocket();
			reopeningSocket = false;
		}
	}
	
	/**
	 * @return The socket.
	 */
	public DatagramSocket getSocket() {
		return socket;
	}

	/**
	 * @return Whether the task is running or not.
	 */
	public boolean isRunning() {
		return this.running;
	}
	
	/**
	 * @return The type of this service.
	 */
	public String getServiceType() {
		return serviceType;
	}
	
	/**
	 * @return The list of services required by this service.
	 */
	public List<String> getRequiredservices() {
		return requiredServices;
	}
	
	/**
	 * @return The port used by this service.
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Add an addresses for a given service type.</br>
	 * TODO: store address to a file
	 * @param serviceType The service type for which to add an address.
	 * @param address The address to add.
	 */
	public void addAddress(String serviceType, String address) {
		synchronized (addresses) {
			if (!addresses.containsKey(serviceType)) {
				addresses.put(serviceType, new ArrayList<String>());
			}
			addresses.get(serviceType).add(address.trim());
		}	
	}
	
	/**
	 * Remove an addresses for a given service type.</br>
	 * TODO: remove address to a file
	 * @param serviceType The service type from which to remove an address.
	 * @param address The address to remove.
	 */
	public void removeAddress(String serviceType, String address) {
		synchronized (addresses) {
			addresses.get(serviceType).remove(address.trim());
		}
	}
	
	/**
	 * Get a list of known addresses for a given service type.
	 * @param serviceType The service type for which to get a list of addresses.
	 * @return A list of known addresses for the given service type.
	 */
	public List<String> getAddresses(String serviceType) {
		return new ArrayList<String>(addresses.get(serviceType));
	}
}
