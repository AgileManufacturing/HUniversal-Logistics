package servlet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Sends requests for web services which are required but which do not have
 * a known address. Responses to these requests are handled by
 * {@link servlet.DiscoveryResponseThread}.
 */
public class DiscoveryRequestThread implements Runnable {
	private static Logger logger = Logger.getLogger(DiscoveryRequestThread.class.getName());
	
	/**
	 * The instance of {@link servlet.DiscoveryTask}.
	 */
	private DiscoveryTask discoveryTask;
	private ScheduledExecutorService executor;
	
	/**
	 * The time in milliseconds between requests for addresses of required
	 * services for which no address is currently known.
	 */
	private int requestWaitMs;
	
	public DiscoveryRequestThread(DiscoveryTask discoveryTask, int requestWaitMs) {
		this.discoveryTask = discoveryTask;
		this.requestWaitMs = requestWaitMs;
	}
	
	/**
	 * Starts the thread.
	 */
	public void start() {
		executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(this, 0, requestWaitMs, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Stops the thread.
	 */
	public void stop() {
		executor.shutdown();
	}
	
	/**
	 * @return Whether the thread is still running or not.
	 */
	public boolean isRunning() {
		return !executor.isTerminated();
	}
	
	@Override
	public void run() {
		try {
			// Broadcast request packets
			for (String serviceType : discoveryTask.getRequiredservices()) { // for each required service
				if (discoveryTask.getAddresses(serviceType).isEmpty()) {	// for which we don't have an address yet
					
					// Try 255.255.255.255 first
					byte[] sendData = ("DISCOVER_" + serviceType.toUpperCase() + "_REQUEST").getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), discoveryTask.getPort());
					discoveryTask.getSocket().send(sendPacket);
					//logger.info("Send a message on socket: " + discoveryTask.getSocket() + " (" + new String(sendPacket.getData()).trim() + ")");
					
					
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
							sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, discoveryTask.getPort());
							discoveryTask.getSocket().send(sendPacket);
							//logger.info("Sending a message on socket: " + discoveryTask.getSocket() + " (" + new String(sendPacket.getData()).trim() + ")");
						}
					}
				}
			}
		} catch (IOException e) {
			logger.severe("Could not receive or send on socket: " + discoveryTask.getSocket() + " (" + e.getMessage() + ")");
			discoveryTask.reopenSocket();
		} 
	}
}
