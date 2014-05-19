package handler;

import java.sql.SQLException;
import java.util.List;

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
	protected static final String propertiesPath = "./service.properties";
	protected static final String databaseName = "logistics";
	protected static final String cellsTable = "Cells";
	protected static final String routesTable = "Routes";
	protected static final String transportAgentsTable = "TransportAgents";
	
	public DatabaseHandler() {
		
	}
	
	abstract public void addTransportAgents(@WebParam(name = "transportAgents") List<TransportAgent> transportAgents) throws SQLException;
	
	abstract public void updateTransportAgents(@WebParam(name = "transportAgents") List<TransportAgent> transportAgents) throws SQLException;
		
	abstract public void removeTransportAgents(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs) throws SQLException;
	
	abstract public List<TransportAgent> getTransportAgents(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs) throws SQLException;
	
	abstract public void addCells(@WebParam(name = "cells") List<Cell> cells) throws SQLException;
	
	abstract public void updateCells(@WebParam(name = "cells") List<Cell> cells) throws SQLException;
		
	abstract public void removeCells(@WebParam(name = "cellIDs") List<Integer> cellIDs) throws SQLException;
	
	abstract public List<Cell> getCells(@WebParam(name = "cellIDs") List<Integer> cellIDs) throws SQLException;
	
	abstract public void addRoutes(@WebParam(name = "routes") List<Route> routes) throws SQLException;
	
	abstract public void updateRoutes(@WebParam(name = "routes") List<Route> routes) throws SQLException;
	
	abstract public void removeRoutes(@WebParam(name = "routes") List<Route> routes) throws SQLException;
	
	abstract public List<Route> getRoutes(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs) throws SQLException;
}
