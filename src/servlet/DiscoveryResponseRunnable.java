package servlet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.logging.Logger;

public class DiscoveryResponseRunnable implements Runnable {
	private static Logger logger = Logger.getLogger(DiscoveryResponseRunnable.class.getName());
	
	private DiscoveryTask discoveryTask;
	
	public DiscoveryResponseRunnable(DiscoveryTask discoveryTask) {
		this.discoveryTask = discoveryTask;
	}	

	@Override
	public void run() {
		while (true) {
			try {
				// Receive packets
				byte[] recvBuf = new byte[15000];
				DatagramPacket receivedPacket = new DatagramPacket(recvBuf, recvBuf.length);

				discoveryTask.getSocket().receive(receivedPacket);
				//logger.info("Received a message on socket: " + discoveryTask.getSocket() + " (" + new String(receivedPacket.getData()).trim() + ")");
				
				String message = new String(receivedPacket.getData()).trim();

				// If request for our address
				if (message.equals("DISCOVER_" + discoveryTask.getServiceType().toUpperCase() + "_REQUEST")) {
					byte[] sendData = ("DISCOVER_" + discoveryTask.getServiceType().toUpperCase() + "_RESPONSE").getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivedPacket.getAddress(), receivedPacket.getPort());
					try {
						discoveryTask.getSocket().send(sendPacket);
					}  catch (IOException e) {
						logger.severe("Could not receive or send on socket " + discoveryTask.getSocket() + " (" + e.getMessage() + ")");
					}
				}
				
				// If response from one of our required services
				for (String serviceType : discoveryTask.getRequiredservices()) {
					if (message.equals("DISCOVER_" + serviceType.toUpperCase() + "_RESPONSE")) {
						discoveryTask.getAddresses(serviceType).add(receivedPacket.getAddress().toString());
					}
				}

			} catch (IOException e) {
				logger.severe("Could not receive or send on socket: " + discoveryTask.getSocket() + " (" + e.getMessage() + ")");
			}
		}
	}
}
