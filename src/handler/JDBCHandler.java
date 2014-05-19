package handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.sql.DataSource;

import objects.Cell;
import objects.Route;
import objects.RoutePoint;
import objects.TransportAgent;

/**
 * Handles database connections and commands.
 * For databases which can be used with the Java JDBC API.
 * 
 */
@WebService
public class JDBCHandler extends DatabaseHandler {
	private static Logger logger = Logger.getLogger(JDBCHandler.class.getName());
	protected DataSource ds;
	
	public JDBCHandler() {

	}

	@Override
	public void addTransportAgents(@WebParam(name = "transportAgents") List<TransportAgent> transportAgents) throws SQLException {
		String s = 
				"insert into " + databaseName + "." + transportAgentsTable + " (" +
				"TransportAgentID, " +
				"MaxFramesMoveOneCellForward, MaxFramesMoveOneCellBackward, " + 
			    "MaxFramesTurnClockwise90, MaxFramesTurnClockwise180, " +
			    "MaxFramesTurnCounterclockwise90, MaxFramesTurnCounterclockwise180, " +
			    "Weight, Length, Width, Height, " +
			    "MaxProductWeight, MaxProductLength, " +
			    "MaxProductWidth, MaxProductHeight" +
			    ") " +
				"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(s);
			for (TransportAgent transportAgent : transportAgents) {
				ps.setInt(1, transportAgent.getTransportAgentID());
				ps.setInt(2, transportAgent.getMaxFramesMoveOneCellForward());
				ps.setInt(3, transportAgent.getMaxFramesMoveOneCellBackward());
				ps.setInt(4, transportAgent.getMaxFramesTurnClockwise90());
				ps.setInt(5, transportAgent.getMaxFramesTurnClockwise180());
				ps.setInt(6, transportAgent.getMaxFramesTurnCounterclockwise90());
				ps.setInt(7, transportAgent.getMaxFramesTurnCounterclockwise180());
				ps.setInt(8, transportAgent.getWeight());
				ps.setInt(9, transportAgent.getLength());
				ps.setInt(10, transportAgent.getWidth());
				ps.setInt(11, transportAgent.getHeight());
				ps.setInt(12, transportAgent.getMaxProductWeight());
				ps.setInt(13, transportAgent.getMaxProductLength());
				ps.setInt(14, transportAgent.getMaxProductWidth());
				ps.setInt(15, transportAgent.getMaxProductHeight());
				ps.addBatch();
			}
			ps.executeBatch();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			con.rollback();
			logger.severe("Could not add TranportAgent(s) to database: " + databaseName + " table: " + transportAgentsTable);
			logger.severe(e.getMessage());
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}
	
	@Override
	public void updateTransportAgents(@WebParam(name = "transportAgents") List<TransportAgent> transportAgents) throws SQLException {
		String s = 
				"update " + databaseName + "." + transportAgentsTable + " set " +
				"MaxFramesMoveOneCellForward=?, MaxFramesMoveOneCellBackward=?, " + 
			    "MaxFramesTurnClockwise90=?, " +
				"MaxFramesTurnClockwise180=?, " +
			    "MaxFramesTurnCounterclockwise90=?, " +
				"MaxFramesTurnCounterclockwise180=?, " +
			    "Weight=?, Length=?, Width=?, Height=?, " +
			    "MaxProductWeight=?, MaxProductLength=?, " +
			    "MaxProductWidth=?, MaxProductHeight=? " +
			    "where TransportAgentID=?;";	
	
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(s);
			for (TransportAgent transportAgent : transportAgents) {
				ps.setInt(1, transportAgent.getMaxFramesMoveOneCellForward());
				ps.setInt(2, transportAgent.getMaxFramesMoveOneCellBackward());
				ps.setInt(3, transportAgent.getMaxFramesTurnClockwise90());
				ps.setInt(4, transportAgent.getMaxFramesTurnClockwise180());
				ps.setInt(5, transportAgent.getMaxFramesTurnCounterclockwise90());
				ps.setInt(6, transportAgent.getMaxFramesTurnCounterclockwise180());
				ps.setInt(7, transportAgent.getWeight());
				ps.setInt(8, transportAgent.getLength());
				ps.setInt(9, transportAgent.getWidth());
				ps.setInt(10, transportAgent.getHeight());
				ps.setInt(11, transportAgent.getMaxProductWeight());
				ps.setInt(12, transportAgent.getMaxProductLength());
				ps.setInt(13, transportAgent.getMaxProductWidth());
				ps.setInt(14, transportAgent.getMaxProductHeight());
				ps.setInt(15, transportAgent.getTransportAgentID());
				ps.addBatch();
			}
			ps.executeBatch();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			con.rollback();
			logger.severe("Could not update TranportAgent(s) in database: " + databaseName + " table: " + transportAgentsTable);
			logger.severe(e.getMessage());
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}

	@Override
	public void removeTransportAgents(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs) throws SQLException {
		String s = 
				"delete from " + databaseName + "." + transportAgentsTable + " " +
				"where TransportAgentID=?;";
				
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(s);
			for (Integer transportAgentID : transportAgentIDs) {
				ps.setInt(1, transportAgentID);
				ps.addBatch();
			}
			ps.executeBatch();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			con.rollback();
			logger.severe("Could not remove TranportAgent(s) from database: " + databaseName + " table: " + transportAgentsTable);
			logger.severe(e.getMessage());
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}
	
	@Override
	public List<TransportAgent> getTransportAgents(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs) throws SQLException {
		return null;
	}


	@Override
	public void addCells(@WebParam(name = "cells") List<Cell> cells) throws SQLException {
		String s = 
				"insert into " + databaseName + "." + cellsTable + " (" +
				"CellID, X, Y, " +
				"AccessibleFromUp, AccessibleFromRight, " + 
			    "AccessibleFromDown, AccessibleFromLeft, " +
			    "SlopeInDegreesFromUp, SlopeInDegreesFromRight, " +
			    "SlopeInDegreesFromDown, SlopeInDegreesFromLeft, " +
			    "MaxWeight, MaxDiameter" +
			    ") " +
				"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(s);
			for (Cell cell : cells) {
				ps.setInt(1, cell.getCellID());
				ps.setInt(2, cell.getX());
				ps.setInt(3, cell.getY());
				ps.setBoolean(4, cell.isAccessibleFromUp());
				ps.setBoolean(5, cell.isAccessibleFromRight());
				ps.setBoolean(6, cell.isAccessibleFromDown());
				ps.setBoolean(7, cell.isAccessibleFromLeft());
				ps.setInt(8, cell.getSlopeInDegreesFromUp());
				ps.setInt(9, cell.getSlopeInDegreesFromRight());
				ps.setInt(10, cell.getSlopeInDegreesFromDown());
				ps.setInt(11, cell.getSlopeInDegreesFromLeft());
				ps.setInt(12, cell.getMaxWeight());
				ps.setInt(13, cell.getMaxDiameter());
				ps.addBatch();
			}			
			ps.executeBatch();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			con.rollback();
			logger.severe("Could not add Cell(s) to database: " + databaseName + " table: " + cellsTable);
			logger.severe(e.getMessage());
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}

	@Override
	public void updateCells(@WebParam(name = "cells") List<Cell> cells) throws SQLException {
		String s = 
				"update " + databaseName + "." + cellsTable + " set " +
				"X=?, Y=?, " +
			    "AccessibleFromUp=?, AccessibleFromRight=?, " +
				"AccessibleFromDown=?, AccessibleFromLeft=?, " +
			    "SlopeInDegreesFromUp=?, SlopeInDegreesFromRight=? " +
			    "SlopeInDegreesFromDown=?, SlopeInDegreesFromLeft=? " +
			    "MaxWeight=?, MaxDiameter=? " +
			    "where CellID=?;";
				
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(s);
			for (Cell cell : cells) {
				ps.setInt(1, cell.getX());
				ps.setInt(2, cell.getY());
				ps.setBoolean(3, cell.isAccessibleFromUp());
				ps.setBoolean(4, cell.isAccessibleFromRight());
				ps.setBoolean(5, cell.isAccessibleFromDown());
				ps.setBoolean(6, cell.isAccessibleFromLeft());
				ps.setInt(7, cell.getSlopeInDegreesFromUp());
				ps.setInt(8, cell.getSlopeInDegreesFromRight());
				ps.setInt(9, cell.getSlopeInDegreesFromDown());
				ps.setInt(10, cell.getSlopeInDegreesFromLeft());
				ps.setInt(11, cell.getMaxWeight());
				ps.setInt(12, cell.getMaxDiameter());
				ps.setInt(13, cell.getCellID());
				ps.addBatch();
			}
			ps.executeBatch();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			con.rollback();
			logger.severe("Could not update Cell(s) in database: " + databaseName + " table: " + cellsTable);
			logger.severe(e.getMessage());
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}

	@Override
	public void removeCells(@WebParam(name = "cellIDs") List<Integer> cellIDs) throws SQLException {
		String s = 
				"delete from " + databaseName + "." + cellsTable + " " +
				"where CellID=?;";
				
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(s);
			for (Integer cellID : cellIDs) {
				ps.setInt(1, cellID);
				ps.addBatch();
			}
			ps.executeBatch();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			con.rollback();
			logger.severe("Could not remove Cell(s) from database: " + databaseName + " table: " + cellsTable);
			logger.severe(e.getMessage());
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}
	
	@Override
	public List<Cell> getCells(@WebParam(name = "cellIDs") List<Integer> cellIDs) throws SQLException {
		return null;
	}

	@Override
	public void addRoutes(@WebParam(name = "routes") List<Route> routes) throws SQLException {
		String s = 
				"insert into " + databaseName + "." + routesTable + " (" +
				"TransportAgentID, TimeFrame, CellID" +
			    ") " +
				"values (?, ?, ?);";
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(s);
			for (Route route : routes) {
				for (RoutePoint routepoint : route.getRoute()) {
					ps.setInt(1, routepoint.getTransportAgentID());
					ps.setInt(2, routepoint.getTimeframe());
					ps.setInt(3, routepoint.getCellID());
					ps.addBatch();
				}
			}			
			ps.executeBatch();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			con.rollback();
			logger.severe("Could not add Route(s) to database: " + databaseName + " table: " + routesTable);
			logger.severe(e.getMessage());
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}

	@Override
	public void updateRoutes(@WebParam(name = "routes") List<Route> routes) throws SQLException {
		String s = 
				"update " + databaseName + "." + routesTable + " set " +
				"CellID=? " +
			    "where TransportAgentID=? and Timeframe=?;";
				
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(s);
			for (Route route : routes) {
				for (RoutePoint routepoint : route.getRoute()) {
					ps.setInt(1, routepoint.getCellID());
					ps.setInt(2, routepoint.getTransportAgentID());
					ps.setInt(3, routepoint.getTimeframe());
					ps.addBatch();
				}
			}	
			ps.executeBatch();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			con.rollback();
			logger.severe("Could not update Cell(s) in database: " + databaseName + " table: " + cellsTable);
			logger.severe(e.getMessage());
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}

	@Override
	public void removeRoutes(@WebParam(name = "routes") List<Route> routes) throws SQLException {
		String s = 
				"delete from " + databaseName + "." + routesTable + " " +
				"where TransportAgentID=? and Timeframe=?;";
				
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(s);
			for (Route route : routes) {
				for (RoutePoint routepoint : route.getRoute()) {
					ps.setInt(1, routepoint.getTransportAgentID());
					ps.setInt(2, routepoint.getTimeframe());
					ps.addBatch();
				}
			}
			ps.executeBatch();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			con.rollback();
			logger.severe("Could not remove Route(s) from database: " + databaseName + " table: " + routesTable);
			logger.severe(e.getMessage());
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}
	
	@Override
	public List<Route> getRoutes(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs) throws SQLException {
		return null;
	}
}
