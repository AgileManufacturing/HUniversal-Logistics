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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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
	private DatagramSocket socket;
	ScheduledExecutorService executor;
	Thread thread;
	
	private static final String propertiesPath = "/config/service.properties";
	int requestWaitMs;
	int port;
	private String serviceType;
	private List<String> requiredServices;

	private HashMap<String, List<String>> addresses;
	
	private DiscoveryTask() {
		this.running = false;
		this.addresses = new HashMap<String, List<String>>();
		this.socket = null;
		executor = null;
		thread = null;
		
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
			this.requiredServices = Arrays.asList(prop.getProperty("requiredServices", "").split(","));
			
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
				logger.info("Port is: " + tmpport);
			}
			if (serviceType.equals("")) {
				logger.severe("serviceType key is missing or empty in " + propertiesPath);
			} else {
				logger.info("Service type is: " + serviceType);
			}
			if (requiredServices == null) {
				logger.severe("requiredServices key is missing or empty in " + propertiesPath);
			} else {
				for (String serviceType : requiredServices) {
					addresses.put(serviceType, new ArrayList<String>());
				}
				logger.info("Required services are: " + Arrays.toString(requiredServices.toArray()));
			}
			
		} catch (IOException e) {
			logger.severe("Could not open " + propertiesPath + " (" + e.getMessage() + ")");
		}
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
			
			try {
				socket = new DatagramSocket(port, InetAddress.getByName("0.0.0.0"));
				socket.setBroadcast(true);
			} catch (SocketException e) {
				logger.severe("Could not create or acces socket: " + socket + " (" + e.getMessage() + " )");
			} catch (UnknownHostException e) {
				logger.severe("Host could not be found (" + e.getMessage() + " )");
			}
			
			DiscoveryRequestRunnable discoveryRequestRunnable = new DiscoveryRequestRunnable(this);
			executor = Executors.newScheduledThreadPool(1);
			executor.scheduleAtFixedRate(discoveryRequestRunnable, 0, requestWaitMs, TimeUnit.MILLISECONDS);
			
			thread = new Thread(new DiscoveryResponseRunnable(this));
			thread.start();
			
		} else {
			logger.severe("Trying to start a task which is already running");
		}
	}
	
	public void stop() {
		if (running == true) {
			this.running = false;
			
			executor.shutdown();
			thread.interrupt();
			
			socket.close();
		} else {
			logger.severe("Trying to stop a task which is not running");
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
