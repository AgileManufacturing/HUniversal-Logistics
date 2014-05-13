package communication;

import java.io.InputStream;

//import javax.xml.crypto.dsig.XMLObject;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
	MessageElement currentElement = new MessageElement();
	
	public MessageElement parseStream(InputStream xml) {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			SAXParser sp = spf.newSAXParser();
			sp.parse(xml, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return getMessageByElements();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		MessageElement newElement = new MessageElement();
		newElement.addAttributes(attributes);
		currentElement.addChild(newElement);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (currentElement.hasParent())
			currentElement = currentElement.getParent();
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		currentElement.setText(ch.toString());
	}
	
	private MessageElement getMessageByElements() {
		if (currentElement.hasParent()){
			currentElement = currentElement.getParent();
			return getMessageByElements();
		}
		return currentElement;			
	}
}