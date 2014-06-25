package websocket;

import java.util.logging.Logger;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class MessageTextEncoder implements Encoder.Text<websocket.Message>{
	private static Logger logger = Logger.getLogger(MessageTextEncoder.class.getName());
	private String packagename = "object";

	@Override
	public void init(EndpointConfig endpointConfig) {
		logger.info("Encoder initialized");
	}
	
	@Override
	public void destroy() {
		logger.info("Encoder destroyed");
	}

	@Override
	public String encode(Message message) throws EncodeException {
		String s = null;
		Class<?> cls = null;
		try {
			cls = Class.forName(message.getClassname());
		} catch (ClassNotFoundException e) {
			logger.severe("Could not encode message (" + e.getMessage() + ")");
		}
		
		if (!message.getClassname().substring(0, message.getClassname().indexOf(".")).equals(packagename)) {
			logger.severe("Trying to encode to an object not in package " + packagename);
			cls = null; // don't encode
		}
		
		if (cls != null) {
			s = message.getClassname() + ";";
			s += new Gson().toJson((cls.cast(message.getObject())));
		}
		return s;
	}
}
