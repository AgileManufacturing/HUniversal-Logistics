package communication;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class Message {
	private List<Host> _recievers;
	private Host _sender;
	private MessageElement _message;
	
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
		setMessage(xmlHand.parseStream(information));
	}
	/**
	 * Add reciever to the list of recievers
	 * @param <b>reciever:</b> Add this reciever to the list of recievers
	 */
	public void addReciever(Host reciever) {
		if (_recievers == null)
			_recievers = new ArrayList<Host>();
		_recievers.add(reciever);
	}
	/**
	 * Send the message to its recievers
	 * @return
	 * 	<b>True:</b> The message has succesfully been send.</br>
	 *  <b>False:</b> An error occured during the sending of the message, or no network connection could be found/created.
	 */
	public boolean send() {
		return Protocol.send(this, _recievers);
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
	/**
	 * @return <b>MessageElement:</b> Returns the MessageElement stored in the Message.
	 */
	public MessageElement getMessage() {
		return _message;
	}
	/**
	 * @param <b>message:</b> Store a new MessageElement in the Message.
	 */
	public void setMessage(MessageElement message) {
		_message = message;
	}
}
