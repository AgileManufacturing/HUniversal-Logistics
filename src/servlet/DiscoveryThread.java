package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

public class DiscoveryThread implements Runnable {
	private static Logger logger = Logger.getLogger(DiscoveryThread.class.getName());
	
	private static DiscoveryThread instance;
	private Thread thread;
	private boolean running;
	
	private static final String propertiesPath = "/config/service.properties";
	int port;
	private String servicetype;
	private List<String> requiredservices;
	private HashMap<String, List<String>> addresses;
	
	private DiscoveryThread() {
		this.running = false;
		this.addresses = new HashMap<String, List<String>>();
		
		Properties prop = new Properties();
		InputStream input = null;
		String tmpport = null;
		try {
			input = this.getClass().getResourceAsStream(propertiesPath);
			prop.load(input);		
			
			tmpport = prop.getProperty("port", "");
			this.servicetype = prop.getProperty("servicetype", "");
			this.requiredservices = Arrays.asList(prop.getProperty("requiredservices", "").split(","));
			
			if (tmpport.equals("")) {
				logger.severe("port key is missing or empty in " + propertiesPath);
			} else {
				port = Integer.parseInt(tmpport);
				logger.info("Port is: " + tmpport);
			}
			if (servicetype.equals("")) {
				logger.severe("servicetype key is missing or empty in " + propertiesPath);
			} else {
				logger.info("Service type is: " + servicetype);
			}
			if (requiredservices == null) {
				logger.severe("requiredservices key is missing or empty in " + propertiesPath);
			} else {
				for (String serviceType : requiredservices) {
					addresses.put(serviceType, new ArrayList<String>());
				}
				logger.info("Required services are: " + Arrays.toString(requiredservices.toArray()));
			}
			
		} catch (IOException e) {
			logger.severe("Could not open " + propertiesPath + " (" + e.getMessage() + ")");
		}
	}
	
	public static DiscoveryThread getInstance() {
		if (instance == null) {
			instance = new DiscoveryThread();
		}
		return instance;
	}
	
	public void start() {
		if (running != true) {
			this.running = true;
			this.thread = new Thread(this);
			this.thread.start();
		} else {
			logger.severe("Trying to start a thread which is already running");
		}
	}
	
	public void stop() {
		if (running == true) {
			this.running = false;
		} else {
			logger.severe("Trying to stop a thread which is not running");
		}
		
	}
	
	@Override
	public void run() {
		DatagramSocket socket = null;
		
		try {
			socket = new DatagramSocket(port, InetAddress.getByName("0.0.0.0"));
			socket.setBroadcast(true);
		} catch (SocketException e) {
			logger.severe("Could not create or acces socket: " + socket + " (" + e.getMessage() + " )");
		} catch (UnknownHostException e) {
			logger.severe("Host could not be found (" + e.getMessage() + " )");
		}

		while (this.running) {
			try {
				// Broadcast request packets
				for (String serviceType : requiredservices) { // for each required service
					if (addresses.get(serviceType).isEmpty()) {	// for which we don't have an address yet
						// Try 255.255.255.255 first
						byte[] sendData = ("DISCOVER_" + serviceType.toUpperCase() + "_REQUEST").getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), port);
						socket.send(sendPacket);
						
						// Broadcast over all network interfaces
						Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
						while (interfaces.hasMoreElements()) {
							NetworkInterface networkInterface = interfaces.nextElement();
 
							if (networkInterface.isLoopback() || !networkInterface.isUp()) {
								continue; // Don't want to broadcast to the loopback interface
							}
							
							for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
								InetAddress broadcast = interfaceAddress.getBroadcast();
								if (broadcast == null) {
								  	continue;
								}				
								sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, port);
								socket.send(sendPacket);
							}
						}
					}
				}
				// Receive packets
				byte[] recvBuf = new byte[15000];
				DatagramPacket receivedPacket = new DatagramPacket(recvBuf, recvBuf.length);
				socket.setSoTimeout(1000);
				while (true) {
					try { 
						socket.receive(receivedPacket);
					} catch (SocketTimeoutException e) {
							logger.info("A timeout has occured during receiving on socket: " + socket + " (" + e.getMessage() + ")");
							break;
					}
					logger.info("Received a message on socket: " + socket + " (" + new String(receivedPacket.getData()).trim() + ")");
					String message = new String(receivedPacket.getData()).trim();

					// If request for our address
					if (message.equals("DISCOVER_" + servicetype.toUpperCase() + "_REQUEST")) {
						byte[] sendData = ("DISCOVER_" + servicetype.toUpperCase() + "_RESPONSE").getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivedPacket.getAddress(), receivedPacket.getPort());
						try {
						socket.send(sendPacket);
						}  catch (IOException e) {
							logger.severe("Could not receive or send on socket " + socket + " (" + e.getMessage() + ")");
						}
					}
					
					// If response from one of our required services
					for (String serviceType : requiredservices) {
						if (message.equals("DISCOVER_" + serviceType.toUpperCase() + "_RESPONSE")) {
							addresses.get(serviceType).add(receivedPacket.getAddress().toString());
						}
					}
				}
			} catch (IOException e) {
				logger.severe("Could not receive or send on socket: " + socket + " (" + e.getMessage() + ")");
			} 
		}
	}

	public boolean isRunning() {
		return this.running;
	}
	
	public void addAddress(String serviceType, String address) {
		addresses.get(serviceType).add(address.trim());
	}
	
	public void removeAddress(String serviceType, String address) {
		addresses.get(serviceType).remove(address.trim());
	}
	
	public List<String> getAddresses(String serviceType) {
		return addresses.get(serviceType);
	}
}
