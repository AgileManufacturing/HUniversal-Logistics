package gateway;

import database.DatabaseHandler;
import database.JDBCDatabaseHandler;

/**
 * Called from {@link websocket.GatewayServerEndpoint} to handle requests and form responses.
 * TODO: gateway logic still needs to be added.
 */
public class Gateway {
	private static Gateway instance = null;
	private DatabaseHandler db = null;
	
	private Gateway() {
		db = new JDBCDatabaseHandler();
	}
	
	public static Gateway getInstance() {
		if (instance == null) {
			instance = new Gateway();
		}
		return instance;
	}
	
	public void handleRequest() {
		
	}
}
