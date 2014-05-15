package handlers;

import java.sql.SQLException;

import objects.Cell;
import objects.Route;
import objects.TransportAgent;

/**
 * Handles database connections and commands. Abstract class.
 *
 */
public abstract class DatabaseHandler {
	//private static Logger logger = Logger.getLogger(DatabaseHandler.class.getName());
	protected static final String databaseName = "logistics";
	protected static final String cellsTable = "Cells";
	protected static final String routesTable = "Cells";
	protected static final String transportAgentsTable = "TransportAgents";
	
	public DatabaseHandler() {
		
	}
	
	abstract public void addTransportAgent(TransportAgent transportAgent) throws SQLException;
	
	abstract public void updateTransportAgent(TransportAgent transportAgent) throws SQLException;
	
	abstract public void removeTransportAgent(TransportAgent transportAgent) throws SQLException;
	
	abstract public void addCell(Cell cell) throws SQLException;
	
	abstract public void updateCell(Cell cell) throws SQLException;
	
	abstract public void removeCell(Cell cell) throws SQLException;
	
	public abstract void addRoute(Route route);
	
	public abstract void updateRoute(Route route);
	
	public abstract void removeRotue(Route route);
}
