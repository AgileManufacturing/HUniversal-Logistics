package server;

import java.sql.SQLException;

import objects.Cell;
import objects.TransportAgent;
import handlers.DatabaseHandler;
import handlers.MariaDBHandler;

public class DatabaseServer {
    private static final  String serverName = "localhost";
    private static final  String user = "root";
    private static final String password = "rt62en914d";
	
	public static void main(String[] args) {
		DatabaseHandler dh = new MariaDBHandler(serverName, user, password);
		Cell c0 = new Cell(0);
		Cell c1 = new Cell(1);
		Cell c2 = new Cell(2);
		Cell c3 = new Cell(3);
		TransportAgent ta0 = new TransportAgent(0);
		TransportAgent ta1 = new TransportAgent(1);
		TransportAgent ta2 = new TransportAgent(2);
		TransportAgent ta3 = new TransportAgent(3);
		try {
			dh.addCell(c0);
			dh.addCell(c1);
			dh.addCell(c2);
			dh.addCell(c3);
			dh.addTransportAgent(ta0);
			dh.addTransportAgent(ta1);
			dh.addTransportAgent(ta2);
			dh.addTransportAgent(ta3);
		} catch (SQLException e) {
			
		}
		
	}
}
