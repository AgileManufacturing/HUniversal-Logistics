package handlers;

import java.sql.SQLException;

import javax.jws.WebParam;
import javax.jws.WebService;

import objects.Cell;
import objects.Route;
import objects.TransportAgent;

/**
 * Handles database connections and commands. Abstract class.
 *
 */
@WebService
public abstract class DatabaseHandler {
	//private static Logger logger = Logger.getLogger(DatabaseHandler.class.getName());
	protected static final String databaseName = "logistics";
	protected static final String cellsTable = "Cells";
	protected static final String routesTable = "Cells";
	protected static final String transportAgentsTable = "TransportAgents";
	
	public DatabaseHandler() {
		
	}
	
	abstract public void addTransportAgent(@WebParam(name = "transportAgent") TransportAgent transportAgent) throws SQLException;
	
	abstract public void updateTransportAgent(@WebParam(name = "transportAgent") TransportAgent transportAgent) throws SQLException;
	
	abstract public void removeTransportAgent(@WebParam(name = "transportAgent") TransportAgent transportAgent) throws SQLException;
	
	abstract public void addCell(@WebParam(name = "cell") Cell cell) throws SQLException;
	
	abstract public void updateCell(@WebParam(name = "cell") Cell cell) throws SQLException;
	
	abstract public void removeCell(@WebParam(name = "cell") Cell cell) throws SQLException;
	
	public abstract void addRoute(@WebParam(name = "route") Route route);
	
	public abstract void updateRoute(@WebParam(name = "route") Route route);
	
	public abstract void removeRotue(@WebParam(name = "route") Route route);
}
