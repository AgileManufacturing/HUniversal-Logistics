package service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import object.Cell;
import object.Route;
import object.TransportAgent;

/**
 * Handles database connections and commands. Abstract class.
 */
@WebService
public interface DatabaseService {
	public static final String databaseName = "logistics";
	public static final String cellsTable = "Cells";
	public static final String routesTable = "Routes";
	public static final String transportAgentsTable = "TransportAgents";
	
	/**
	 * Adds {@link object.TransportAgent} objects to the database.
	 * @param transportAgents The list of {@link object.TransportAgent} to add.
	 * @return The number of {@link object.TransportAgent} objects added to the database.
	 */
	@WebMethod
	abstract public int addTransportAgents(@WebParam(name = "transportAgents") List<TransportAgent> transportAgents);
	
	/**
	 * Update {@link object.TransportAgent} objects in the database.
	 * @param transportAgents The list of {@link object.TransportAgent} to update.
	 * @return The number of {@link object.TransportAgent} objects updated in the database.
	 */
	@WebMethod
	abstract public int updateTransportAgents(@WebParam(name = "transportAgents") List<TransportAgent> transportAgents);
		
	/**
	 * Remove {@link object.TransportAgent} objects from the database.
	 * @param transportAgentIDs The list of transportAgentIDs to remove.
	 * @return The number of {@link object.TransportAgent} objects removed from the database.
	 */
	@WebMethod
	abstract public int removeTransportAgents(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs);
	
	/**
	 * Get {@link object.TransportAgent} from the database.
	 * @param transportAgentIDs The list of transportAgentIDs to get.
	 * @return The list of {@link object.TransportAgent} agents.
	 */
	@WebMethod
	@WebResult(name="transportAgent")
	abstract public List<TransportAgent> getTransportAgents(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs);
	
	/**
	 * Adds {@link object.Cell} objects to the database.
	 * @param cells The list of {@link object.Cell} objects to add.
	 * @return The number of {@link object.Cell} objects added to the database.
	 */
	@WebMethod
	abstract public int addCells(@WebParam(name = "cells") List<Cell> cells);
	
	/**
	 * Updates {@link object.Cell} objects in the database.
	 * @param cells The list of {@link object.Cell} objects to update.
	 * @return The number of {@link object.Cell} objects updated in the database.
	 */
	@WebMethod
	abstract public int updateCells(@WebParam(name = "cells") List<Cell> cells);
		
	/**
	 * Remove {@link object.Cell} objects from the database.
	 * @param cells The list of cellIDs to remove.
	 * @return The number of {@link object.Cell} objects removed from the database.
	 */
	@WebMethod
	abstract public int removeCells(@WebParam(name = "cellIDs") List<Integer> cellIDs);
	
	/**
	 * Get {@link object.Cell} objects from the database.
	 * @param cellIDs The list of cellIDs to get.
	 * @return The list of {@link object.Cell} objects.
	 */
	@WebMethod
	@WebResult(name="cell")
	abstract public List<Cell> getCells(@WebParam(name = "cellIDs") List<Integer> cellIDs);
	
	/**
	 * Adds {@link object.Route} objects to the database.
	 * @param routes The list of {@link object.Route} objects to add.
	 * @return The number of {@link object.Route} objects added to the database.
	 */
	@WebMethod
	abstract public int addRoutes(@WebParam(name = "routes") List<Route> routes);
	
	/**
	 * Updates {@link object.Route} objects in the database.
	 * @param routes The list of {@link object.Route} objects to update.
	 * @return The number of {@link object.Route} objects updated in the database.
	 */
	@WebMethod
	abstract public int updateRoutes(@WebParam(name = "routes") List<Route> routes);
	
	/**
	 * Remove {@link object.Route} objects from the database.
	 * @param routes The list of {@link object.Route} objects to remove.
	 * @return The number of {@link object.Route} objects removed from the database.
	 */
	@WebMethod
	abstract public int removeRoutes(@WebParam(name = "routes") List<Route> routes);
	
	/**
	 * Get {@link object.Route} objects from the database.
	 * @param transportAgentIDs The list of transportAgentIDs for which to get {@link object.Route} objects.
	 * @return The list of {@link object.Route} objects.
	 */
	@WebMethod
	@WebResult(name="route")
	abstract public List<Route> getRoutes(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs);
}
