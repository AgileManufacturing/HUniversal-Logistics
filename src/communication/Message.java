package communication;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class Message {
	private List<Host> recievers;
	private Host sender;
	
	
	/**
	 * Initialize an empty request, to be able to form a new request to send
	 */
	public Message() {
		
	}
	/**
	 * Initialize the request with known information
	 * @param <b>Information:</b> Build the message from known XML information.  
	 */
	public Message(InputStream information) {
		XMLHandler xmlHand = new XMLHandler();
		MessageElement message = xmlHand.parseStream(information);
	}
	/**
	 * Add reciever to the list of recievers
	 * @param <b>reciever:</b> Add this reciever to the list of recievers
	 */
	public void addReciever(Host reciever) {
		if (recievers == null)
			recievers = new ArrayList<Host>();
		recievers.add(reciever);
	}
	/**
	 * Send the message to its recievers
	 * @return
	 * 	<b>True:</b> The message has succesfully been send.</br>
	 *  <b>False:</b> An error occured during the sending of the message, or no network connection could be found/created.
	 */
	public boolean send() {
		return Protocol.send(this, recievers);
	}
	/**
	 * Send the message to the whole network (according to the protocol)
	 * @return
	 *  <b>True:</b> The message has succesfully been broadcasted.</br>
	 *  <b>False:</b> An error occured during the broadcasting of the message, or no network connection could be found/created.
	 */
	public boolean broadcast() {
		return Protocol.broadcast(this);
	}
}
