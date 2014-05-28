package servlet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.logging.Logger;

public class DiscoveryRequestRunnable implements Runnable {
	private static Logger logger = Logger.getLogger(DiscoveryRequestRunnable.class.getName());
	
	private DiscoveryTask discoveryTask;
	
	public DiscoveryRequestRunnable(DiscoveryTask discoveryTask) {
		this.discoveryTask = discoveryTask;
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
							//logger.info("Send a message on socket: " + discoveryTask.getSocket() + " (" + new String(sendPacket.getData()).trim() + ")");
						}
					}
				}
			}
		} catch (IOException e) {
			logger.severe("Could not receive or send on socket: " + discoveryTask.getSocket() + " (" + e.getMessage() + ")");
		} 
	}
}
