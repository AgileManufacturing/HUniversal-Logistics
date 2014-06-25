package websocket;

//TODO: javadoc attributes added here should also be added to encoder/decoder
public class Message {
	private String classname;
	private Object object;
	//TODO: maybe also "private String action" (insert, update, remove, etc)
	
	public Message(Object object) {
		this.classname = object.getClass().getName();
		this.object = object;
	}
	
	public String getClassname() {
		return this.classname;
	}
	
	public Object getObject() {
		return this.object;
	}
}
