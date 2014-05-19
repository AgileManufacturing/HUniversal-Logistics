package handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

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
	private static Logger logger = Logger.getLogger(DatabaseHandler.class.getName());
	protected static final String propertiesPath = "./database.properties";
	protected static final String databaseName = "logistics";
	protected static final String cellsTable = "Cells";
	protected static final String routesTable = "Routes";
	protected static final String transportAgentsTable = "TransportAgents";
	protected String ip;
	protected String user;
	protected String password;
	
	public DatabaseHandler() {
		Properties prop = new Properties();
		
		this.ip = prop.getProperty("ip");
		this.user = prop.getProperty("user");
		this.password = prop.getProperty("password");
		
		try {
			prop.load(new FileInputStream(propertiesPath));
		} catch (IOException e) {
			logger.severe("Could not find or open configuration file " + propertiesPath);
			logger.severe(e.getMessage());
		}
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
