package service;

import java.util.logging.Logger;

import javax.xml.ws.Endpoint;

import handler.DatabaseHandler;
import handler.MariaDBHandler;

public class DatabaseService extends Service {
	private static Logger logger = Logger.getLogger(DatabaseService.class.getName());
	
//	public static void main(String[] args) {
//		// create and publish an endpoint
//		DatabaseHandler dh = new MariaDBHandler();
//		Endpoint.publish(ip + ":" + port, dh);
//		logger.info("DatabaseService published at " + ip + ":" + port);
//	}
}
