package communication;

import java.util.ArrayList;

import org.xml.sax.Attributes;

public class MessageElement {
	private MessageElement _parent;
	private ArrayList<MessageElement> _children = new ArrayList<MessageElement>();
	private Attributes _attributes;
	private String _text, _localName, _qName;
	
	/**
	 * @param <b>localName:</b> The localName of the MessageElement
	 * @param <b>qName:</b> The qName of the MessageElement
	 */
	public MessageElement(String localName, String qName) {
		setLocalName(localName);
		setQName(qName);
		setText("");
		setParent(null);
	}
	/**
	 * <b>Default Constructor:</b> Create an empty MessageElement to edit or give children to.
	 */
	public MessageElement() {
		setLocalName("");
		setQName("");
		setText("");
		setParent(null);
	}

	/**
	 * @return <b>ArrayList<MessageElement>:</b> Returns all child MessageElements of this MessageElement.
	 */
	public ArrayList<MessageElement> getChildren() {
		return _children;
	}
	public void addChild(MessageElement child) {
		_children.add(child);
	}
	/**
	 * @return <b>ArrayList<String>:</b> Returns all attributes of this MessageElement.
	 */
	public Attributes getAttributes() {
		return _attributes;
	}
	public void addAttributes(Attributes attributes) {
		this._attributes = attributes;
	}
	
	/**
	 * @return <b>String:</b> Returns the local name of the MessageElement
	 */
	public String getLocalName() {
		return _localName;
	}
	/**
	 * @param name <b>String name:</b> Set a new local name for the MessageElement
	 */
	public void setLocalName(String name) {
		_localName = name;
	}
	/**
	 * @return <b>String:</b> Returns the qName of the MessageElement
	 */
	public String getQName() {
		return _qName;
	}
	/**
	 * @param name <b>String name:</b> Set a new qName for the MessageElement
	 */
	public void setQName(String _qName) {
		this._qName = _qName;
	}
	/**
	 * @return <b>String:</b> Returns the text of the MessageElement
	 */
	public String getText() {
		return _text;
	}
	public void setText(String text) {
		this._text = text;
	}
	public MessageElement getParent() {
		return _parent;
	}
	public void setParent(MessageElement parent) {
		this._parent = parent;
	}
	public boolean hasParent() {
		if (_parent != null)
			return true;
		return false;
	}
}
