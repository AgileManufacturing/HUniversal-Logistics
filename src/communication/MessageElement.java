package communication;

import java.util.ArrayList;

import org.xml.sax.Attributes;

public class MessageElement {
	private MessageElement parent;
	private ArrayList<MessageElement> children = new ArrayList<MessageElement>();
	private Attributes attributes;
	private String text;
	
	/**
	 * @return <b>ArrayList<MessageElement>:</b> Returns all child MessageElements of this MessageElement.
	 */
	public ArrayList<MessageElement> getChildren() {
		return children;
	}
	public void addChild(MessageElement child) {
		children.add(child);
	}
	/**
	 * @return <b>ArrayList<String>:</b> Returns all attributes of this MessageElement.
	 */
	public Attributes getAttributes() {
		return attributes;
	}
	public void addAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	/**
	 * @return <b>String:</b> Returns the text of the MessageElement
	 */
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public MessageElement getParent() {
		return parent;
	}
	public void setParent(MessageElement parent) {
		this.parent = parent;
	}
	public boolean hasParent() {
		if (parent != null)
			return true;
		return false;
	}
}
