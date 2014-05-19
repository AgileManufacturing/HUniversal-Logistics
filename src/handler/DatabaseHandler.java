package handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jws.WebParam;
import javax.jws.WebService;

import object.Cell;
import object.Route;
import object.TransportAgent;

/**
 * Handles database connections and commands. Abstract class.
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
	
	protected DatabaseHandler() {
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
	
	/**
	 * Adds {@link object.TransportAgent} objects to the database.
	 * @param transportAgents The list of {@link object.TransportAgent} to add.
	 * @throws SQLException
	 */
	abstract public void addTransportAgents(@WebParam(name = "transportAgents") List<TransportAgent> transportAgents) throws SQLException;
	
	/**
	 * Update {@link object.TransportAgent} objects in the database.
	 * @param transportAgents The list of {@link object.TransportAgent} to update.
	 * @throws SQLException
	 */
	abstract public void updateTransportAgents(@WebParam(name = "transportAgents") List<TransportAgent> transportAgents) throws SQLException;
		
	/**
	 * Remove {@link object.TransportAgent} objects from the database.
	 * @param transportAgentIDs The list of transportAgentIDs to remove.
	 * @throws SQLException
	 */
	abstract public void removeTransportAgents(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs) throws SQLException;
	
	/**
	 * Get {@link object.TransportAgent} from the database.
	 * @param transportAgentIDs The list of transportAgentIDs to get.
	 * @return The list of {@link object.TransportAgent} agents.
	 * @throws SQLException
	 */
	abstract public List<TransportAgent> getTransportAgents(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs) throws SQLException;
	
	/**
	 * Adds {@link object.Cell} objects to the database.
	 * @param cells The list of {@link object.Cell} objects to add.
	 * @throws SQLException
	 */
	abstract public void addCells(@WebParam(name = "cells") List<Cell> cells) throws SQLException;
	
	/**
	 * Updates {@link object.Cell} objects in the database.
	 * @param cells The list of {@link object.Cell} objects to update.
	 * @throws SQLException
	 */
	abstract public void updateCells(@WebParam(name = "cells") List<Cell> cells) throws SQLException;
		
	/**
	 * Remove {@link object.Cell} objects from the database.
	 * @param cells The list of cellIDs to remove.
	 * @throws SQLException
	 */
	abstract public void removeCells(@WebParam(name = "cellIDs") List<Integer> cellIDs) throws SQLException;
	
	/**
	 * Get {@link object.Cell} objects from the database.
	 * @param cellIDs The list of cellIDs to get.
	 * @return The list of {@link object.Cell} objects.
	 * @throws SQLException
	 */
	abstract public List<Cell> getCells(@WebParam(name = "cellIDs") List<Integer> cellIDs) throws SQLException;
	
	/**
	 * Adds {@link object.Route} objects to the database.
	 * @param routes The list of {@link object.Route} objects to add.
	 * @throws SQLException
	 */
	abstract public void addRoutes(@WebParam(name = "routes") List<Route> routes) throws SQLException;
	
	/**
	 * Updates {@link object.Route} objects in the database.
	 * @param routes The list of {@link object.Route} objects to update.
	 * @throws SQLException
	 */
	abstract public void updateRoutes(@WebParam(name = "routes") List<Route> routes) throws SQLException;
	
	/**
	 * Remove {@link object.Route} objects from the database.
	 * @param routes The list of {@link object.Route} objects to remove.
	 * @throws SQLException
	 */
	abstract public void removeRoutes(@WebParam(name = "routes") List<Route> routes) throws SQLException;
	
	/**
	 * Get {@link object.Route} objects from the database.
	 * @param transportAgentIDs The list of transportAgentIDs for which to get {@link object.Route} objects.
	 * @return The list of {@link object.Route} objects.
	 * @throws SQLException
	 */
	abstract public List<Route> getRoutes(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs) throws SQLException;
}
