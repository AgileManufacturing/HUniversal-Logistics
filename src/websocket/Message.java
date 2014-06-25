package websocket;
/**
 * To be send over a websocket using {@link websocket.MessageTextEncoder} and {@link websocket.MessageTextDecoder}.</br>
 * WARNING: attributes added here should also be added to {@link websocket.MessageTextEncoder} and {@link websocket.MessageTextDecoder}.
 */
public class Message {
	private String classname;
	private Object object;
	//TODO: maybe also "private String action" (insert, update, remove, etc)
	
	/**
	 * @param object The {@link java.lang.Object} to send. Can be any object cast to an generic {@link java.lang.Object}.
	 */
	public Message(Object object) {
		this.classname = object.getClass().getName();
		this.object = object;
	}
	
	/**
	 * @return  The class name of the object. In package/class format.
	 */
	public String getClassname() {
		return this.classname;
	}
	
	/**
	 * @return The object as a generic {@link java.lang.Object}. Can be cast using the classname.
	 */
	public Object getObject() {
		return this.object;
	}
}
