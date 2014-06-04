package servlet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Receives requests and sends responses depending on whether the address asked
 * for is known. Also handles responses on requests send by
 * {@link servlet.DiscoveryRequestThread}.
 */
public class DiscoveryResponseThread implements Runnable {
	private static Logger logger = Logger.getLogger(DiscoveryResponseThread.class.getName());
	
	/**
	 * The instance of {@link servlet.DiscoveryTask}.
	 */
	private DiscoveryTask discoveryTask;
	
	/**
	 * The thread used to execute the run method.
	 */
	private Thread thread;
	
	/**
	 * Used to make a distinction between a socket closing on it's own or a
	 * socket closing because the stop method is called.
	 */
	private boolean isRunning;
	
	public DiscoveryResponseThread(DiscoveryTask discoveryTask) {
		this.discoveryTask = discoveryTask;
		this.isRunning = false;
	}	
	
	/**
	 * Starts the thread.
	 */
	public void start() {
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	/**
	 * Stops the thread.
	 */
	public void stop() {
		// socket.receive() blocks, so close the socket and catch the exception to exit
		isRunning = false;
		discoveryTask.closeSocket();
	}
	
	/**
	 * @return Whether the thread is still running or not.
	 */
	public boolean isRunning() {
		return thread.isAlive();
	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				// Receive packets
				byte[] recvBuf = new byte[15000];
				DatagramPacket receivedPacket = new DatagramPacket(recvBuf, recvBuf.length);
				
				discoveryTask.getSocket().receive(receivedPacket);
				//logger.info("Received a message on socket: " + discoveryTask.getSocket() + " (" + new String(receivedPacket.getData()).trim() + ")");
				
				String message = new String(receivedPacket.getData()).trim();
				String[] msgArray = message.split("_|\\,"); // split on _ or ,
				String serviceType = null;
				String action = null;
				String[] addresses = null;
				if (msgArray.length >= 2) {
					serviceType = msgArray[1].toLowerCase();
					action = msgArray[2];
				}
				if (msgArray.length >= 3) {
					addresses = Arrays.copyOfRange(msgArray, 3, msgArray.length);
				}
				
				byte[] sendData = null;
				DatagramPacket sendPacket = null;

				// If response from one of our required services
				if (action.equals("RESPONSE")) {
					if (discoveryTask.getRequiredservices().contains(serviceType)) {
						// If message contains addresses take those
						if (addresses != null) {
							for (String address : addresses) {
								discoveryTask.addAddress(serviceType, address);
							}
						// Else take sender address
						} else {
							discoveryTask.addAddress(serviceType, receivedPacket.getAddress() + ":" +  receivedPacket.getPort());
						}
					}
				// If request for our address
				} else if (action.equals("REQUEST") && discoveryTask.getServiceType().equals(serviceType)) {
					sendData = ("DISCOVER_" + serviceType.toUpperCase() + "_RESPONSE").getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, receivedPacket.getAddress(), receivedPacket.getPort());						
				// If request for address of one of our required services
				} else if (action.equals("REQUEST") && discoveryTask.getRequiredservices().contains(serviceType)) {
					if (!discoveryTask.getAddresses(serviceType).isEmpty()) {
						String s = "DISCOVER_" + serviceType.toUpperCase() + "_RESPONSE";
						for (String address : discoveryTask.getAddresses(serviceType)) {
							s += "," + address;
						}
						sendData = (s).getBytes();
						sendPacket = new DatagramPacket(sendData, sendData.length, receivedPacket.getAddress(), receivedPacket.getPort());						
					}
				}

				if (sendPacket != null) {
					discoveryTask.getSocket().send(sendPacket);
				}
				
			} catch (IOException e) {
				if (isRunning) {
					logger.severe("Could not receive or send on socket: " + discoveryTask.getSocket() + " (" + e.getMessage() + ")");
					discoveryTask.reopenSocket();
				} else {
					// socket.receive() blocks, so close the socket and catch the exception to exit
					break;
				}
			}
		}
	}
}
