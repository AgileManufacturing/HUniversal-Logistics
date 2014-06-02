package servlet;

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
 * Background task which handles finding the addresses of other webservices and
 * responding to requests for the address of this webservice.</br>
 * Uses /config/service.properties to store settings.
 */
public class DiscoveryTask {
	private static Logger logger = Logger.getLogger(DiscoveryTask.class.getName());
	
	private static DiscoveryTask instance;
	private boolean running;
	private boolean reopeningSocket;
	private DatagramSocket socket;
	private DiscoveryRequestThread discoveryRequestThread;
	private DiscoveryResponseThread discoveryResponseThread;
	
	private static final String propertiesPath = "/config/service.properties";
	private int requestWaitMs;
	private int port;
	private String serviceType;
	private List<String> requiredServices;
	private HashMap<String, List<String>> addresses;
	
	private DiscoveryTask() {
		this.running = false;
		this.addresses = new HashMap<String, List<String>>();
		this.socket = null;
		Properties prop = new Properties();
		InputStream input = null;
		String tmpport = null;
		String tmpwait = null;
		try {
			input = this.getClass().getResourceAsStream(propertiesPath);
			prop.load(input);		
			
			tmpwait = prop.getProperty("requestWaitMs", "");
			tmpport = prop.getProperty("port", "");
			this.serviceType = prop.getProperty("serviceType", "");
			this.requiredServices = Arrays.asList(prop.getProperty(serviceType, "").split(","));
			
			if (tmpwait.equals("")) {
				logger.severe("requestWaitMs key is missing or empty in " + propertiesPath);
			} else {
				this.requestWaitMs = Integer.parseInt(tmpwait);
				logger.info("Request wait time (ms) is: " + tmpwait);
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
		logger.info("Service " + serviceType + " started on port " + port + ", required services " + Arrays.toString(requiredServices.toArray()));
	}
	
	public static DiscoveryTask getInstance() {
		if (instance == null) {
			instance = new DiscoveryTask();
		}
		return instance;
	}
	
	public void start() {
		if (running != true) {
			this.running = true;
			
			openSocket();
			this.discoveryRequestThread.start();
			this.discoveryResponseThread.start();
		} else {
			logger.severe("Trying to start a task which is already running");
		}
	}
	
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
			logger.severe("Trying to stop a task which is not running");
		}
	}
	
	public void restart() {
		if (running = true) {
			this.stop();
		}
		this.start();
	}
	
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
	
	public void closeSocket() {
		if (socket != null) {
			socket.close();
		}
	}
	
	public void reopenSocket() {
		if (!reopeningSocket) {
			reopeningSocket = true;
			closeSocket();
			openSocket();
			reopeningSocket = false;
		}
	}

	public boolean isRunning() {
		return this.running;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	
	public List<String> getRequiredservices() {
		return requiredServices;
	}

	public DatagramSocket getSocket() {
		return socket;
	}
	
	public int getPort() {
		return port;
	}
	
	/**
	 * Add an addresses for a given service type.
	 * @param serviceType The service type for which to add an address.
	 * @param address The address to add.
	 */
	public void addAddress(String serviceType, String address) {
		addresses.get(serviceType).add(address.trim());
	}
	
	/**
	 * Remove an addresses for a given service type.
	 * @param serviceType The service type from which to remove an address.
	 * @param address The address to remove.
	 */
	public void removeAddress(String serviceType, String address) {
		addresses.get(serviceType).remove(address.trim());
	}
	
	/**
	 * Get a list of known addresses for a given service type.
	 * @param serviceType The service type for which to get a list of addresses.
	 * @return The list of known addresses for the given service type.
	 */
	public List<String> getAddresses(String serviceType) {
		return addresses.get(serviceType);
	}
	
	
}
