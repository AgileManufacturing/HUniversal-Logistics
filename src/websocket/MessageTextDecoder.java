package websocket;

import java.util.logging.Logger;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class MessageTextDecoder implements Decoder.Text<websocket.Message> {
	private static Logger logger = Logger.getLogger(MessageTextDecoder.class.getName());
	private String packagename = "object";
	
	@Override
	public void init(EndpointConfig endpointConfig) {
		logger.info("Decoder initialized");
	}
	
	@Override
	public void destroy() {
		logger.info("Decoder destroyed");
	}

	@Override
	public Message decode(String string) throws DecodeException {
		Message message = null;
		
		String classname = null;
		String objectJson = null;
		if(string.contains(";")){
			classname = string.substring(0, string.indexOf(";"));
			objectJson = string.substring(string.indexOf(";") + 1, string.length());
		}
		
		if (!classname.substring(0, classname.indexOf(".")).equals(packagename)) {
			logger.severe("Trying to decode to an object not in package " + packagename);
			classname = null; // don't decode
			objectJson = null;
		}
		
		if (classname != null && objectJson != null) {
			Class<?> cls = null;
			try {
				cls = Class.forName(classname);
			} catch (ClassNotFoundException e) {
				logger.severe("Could not decode message (" + e.getMessage() + ")");
			}
			Object object = new Gson().fromJson(objectJson, cls);
			message = new Message(classname, object);
		}
		return message;
	}

	@Override
	public boolean willDecode(String string) {
		try {
			decode(string);
			return true;
		} catch (DecodeException e) {
			return false;
		}
	}
}
