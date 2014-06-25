package websocket;

import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import object.Cell;
import object.GridPoint;
import object.Route;
import object.RoutePoint;
import object.TransportAgent;

//TODO: javadoc instance for every connection (=client), client state variables
//TODO: javadoc link to http://docs.oracle.com/javaee/7/tutorial/doc/websocket.htm#GKJIQ5
@ServerEndpoint(
		value = "/websocket",
		encoders = { websocket.MessageTextEncoder.class },
		decoders = { websocket.MessageTextDecoder.class }		
		)
public class GatewayServerEndpoint {
	private static Logger logger = Logger.getLogger(GatewayServerEndpoint.class.getName());

	@OnOpen
	public void onOpen(Session session, EndpointConfig conf) {
		logger.info("WebSocket opened.");
	}
	
    @OnMessage
    public void onMessage(Session session, Message message) {
    	logger.info("Text message received.");
    	Message response = null;

		switch (message.getClassname()) {
    	case "object.Cell":
    		System.out.println(((Cell) message.getObject()).toString());
    		break;
    	case "object.GridPoint":
    		System.out.println(((GridPoint) message.getObject()).toString());
    		break;
    	case "object.Route":
    		System.out.println(((Route) message.getObject()).toString());
    		break;
    	case "object.RoutePoint":
    		System.out.println(((RoutePoint) message.getObject()).toString());
    		break;
    	case "object.TransportUnit":
    		System.out.println(((TransportAgent) message.getObject()).toString());
    		break;
    	}
		
		if (response != null) {
			session.getAsyncRemote().sendObject(response);
		}
    }
 
    @OnError
    public void onError(Session session, Throwable error) {
    	logger.info("WebSocket error.");
    }
    
    @OnClose
    public void onClose(Session session, CloseReason reason) {
    	logger.info("WebSocket closed.");
    }
    
}
