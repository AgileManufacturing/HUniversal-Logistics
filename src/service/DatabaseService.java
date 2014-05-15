package service;

import javax.xml.ws.Endpoint;

import handlers.DatabaseHandler;
import handlers.MariaDBHandler;

public class DatabaseService extends Service {
    private static final String dbServerName = "localhost";
    private static final String dbUser = "root";
    private static final String dbPassword = "rt62en914d";
	
	public static void main(String[] args) {
		// create and publish an endpoint
		DatabaseHandler dh = new MariaDBHandler(dbServerName, dbUser, dbPassword);
		Endpoint.publish(serviceIP + ":" + servicePort, dh);
	}
}
