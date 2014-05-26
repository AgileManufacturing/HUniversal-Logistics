package handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import object.Cell;
import object.Route;
import object.RoutePoint;
import object.TransportAgent;

/**
 * Handles database connections and commands.
 * For databases which can be used with the Java JDBC API.
 */
@WebService
public class JDBCDatabaseHandler implements DatabaseHandler {
	private static Logger logger = Logger.getLogger(JDBCDatabaseHandler.class.getName());

	//@Resource(name="jdbc/logistics") // Broken in Tomcat 7, handled in constructor instead
	protected DataSource ds;
	
	public JDBCDatabaseHandler() {
		// DataSource loaded here instead of using @Resource
		Context ctx;
		String datasource = "java:comp/env/jdbc/logistics";
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup(datasource);
		} catch (NamingException e) {
			logger.severe("Failed to look up DataSource " + datasource);
			logger.severe(e.getMessage());
		}
	}

	@WebMethod
	public int addTransportAgents(@WebParam(name = "transportAgents") List<TransportAgent> transportAgents) {
		int addedTransportAgents = 0;
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
			addedTransportAgents = ps.executeBatch().length;
			con.setAutoCommit(true);
		} catch (SQLException e) {
			logger.severe("Could not add TranportAgent(s) to " + databaseName + "." + transportAgentsTable);
			logger.severe(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.severe("Could not roll back changes to " + databaseName + "." + transportAgentsTable);
				logger.severe(e.getMessage());
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.severe("Could not close PreparedStatement");
					logger.severe(e.getMessage());
				}
			}
		    if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.severe("Could not close Connection");
					logger.severe(e.getMessage());
				}
			}
		}
		return addedTransportAgents;
	}
	
	@WebMethod
	public int updateTransportAgents(@WebParam(name = "transportAgents") List<TransportAgent> transportAgents) {
		int updatedTransportAgents = 0;
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
			updatedTransportAgents = ps.executeBatch().length;
			con.setAutoCommit(true);
		} catch (SQLException e) {
			logger.severe("Could not update TranportAgent(s) in " + databaseName + "." + transportAgentsTable);
			logger.severe(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.severe("Could not roll back changes to " + databaseName + "." + transportAgentsTable);
				logger.severe(e.getMessage());
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.severe("Could not close PreparedStatement");
					logger.severe(e.getMessage());
				}
			}
		    if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.severe("Could not close Connection");
					logger.severe(e.getMessage());
				}
			}
		}
		return updatedTransportAgents;
	}

	@WebMethod
	public int removeTransportAgents(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs) {
		int removedTransportAgents = 0;
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
			removedTransportAgents = ps.executeBatch().length;
			con.setAutoCommit(true);
		} catch (SQLException e) {
			logger.severe("Could not remove TranportAgent(s) from " + databaseName + "." + transportAgentsTable);
			logger.severe(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.severe("Could not roll back changes to " + databaseName + "." + transportAgentsTable);
				logger.severe(e.getMessage());
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.severe("Could not close PreparedStatement");
					logger.severe(e.getMessage());
				}
			}
		    if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.severe("Could not close Connection");
					logger.severe(e.getMessage());
				}
			}
		}
		return removedTransportAgents;
	}
	
	@WebMethod
	@WebResult(name="transportAgent")
	public List<TransportAgent> getTransportAgents(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs) {
		List<TransportAgent> transportAgents = new ArrayList<TransportAgent>();
		String s =
				"select TransportAgentID, " +
				"MaxFramesMoveOneCellForward, MaxFramesMoveOneCellBackward, " + 
			    "MaxFramesTurnClockwise90, " +
				"MaxFramesTurnClockwise180, " +
			    "MaxFramesTurnCounterclockwise90, " +
				"MaxFramesTurnCounterclockwise180, " +
			    "Weight, Length, Width, Height, " +
			    "MaxProductWeight, MaxProductLength, " +
			    "MaxProductWidth, MaxProductHeight " +
			    "from " + databaseName + "." + transportAgentsTable + " " +
			    "where TransportAgentID=?;";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(s);
			for (Integer transportAgentID : transportAgentIDs) {
				ps.setInt(1, transportAgentID);
				ps.execute();
				rs = ps.getResultSet();
				
				while (rs.next()) {
					TransportAgent transportAgent = new TransportAgent(rs.getInt("TransportAgentID"));
					transportAgent.setMaxFramesMoveOneCellForward(rs.getInt("MaxFramesMoveOneCellForward"));
					transportAgent.setMaxFramesMoveOneCellBackward(rs.getInt("MaxFramesMoveOneCellBackward"));
					transportAgent.setMaxFramesTurnClockwise90(rs.getInt("MaxFramesTurnClockwise90"));
					transportAgent.setMaxFramesTurnClockwise180(rs.getInt("MaxFramesTurnClockwise180"));
					transportAgent.setMaxFramesTurnCounterclockwise90(rs.getInt("MaxFramesTurnCounterclockwise90"));
					transportAgent.setMaxFramesTurnCounterclockwise180(rs.getInt("MaxFramesTurnCounterclockwise180"));
					transportAgent.setWeight(rs.getInt("Weight"));
					transportAgent.setLength(rs.getInt("Length"));
					transportAgent.setWidth(rs.getInt("Width"));
					transportAgent.setHeight(rs.getInt("Height"));
					transportAgent.setMaxProductWeight(rs.getInt("MaxProductWeight"));
					transportAgent.setMaxProductLength(rs.getInt("MaxProductLength"));
					transportAgent.setMaxProductWidth(rs.getInt("MaxProductWidth"));
					transportAgent.setMaxProductHeight(rs.getInt("MaxProductHeight"));
					transportAgents.add(transportAgent);
				}
			}		
		} catch (SQLException e) {
			logger.severe("Could not get TranportAgent(s) from " + databaseName + "." + transportAgentsTable);
			logger.severe(e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.severe("Could not close PreparedStatement");
					logger.severe(e.getMessage());
				}
			}
		    if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.severe("Could not close Connection");
					logger.severe(e.getMessage());
				}
			}
		}
		return transportAgents;
	}

	@WebMethod
	public int addCells(@WebParam(name = "cells") List<Cell> cells) {
		int addedCells = 0;
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
			addedCells = ps.executeBatch().length;
			con.setAutoCommit(true);
		} catch (SQLException e) {
			logger.severe("Could not add Cell(s) to " + databaseName + "." + cellsTable);
			logger.severe(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.severe("Could not roll back changes to " + databaseName + "." + cellsTable);
				logger.severe(e.getMessage());
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.severe("Could not close PreparedStatement");
					logger.severe(e.getMessage());
				}
			}
		    if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.severe("Could not close Connection");
					logger.severe(e.getMessage());
				}
			}
		}
		return addedCells;
	}

	@WebMethod
	public int updateCells(@WebParam(name = "cells") List<Cell> cells) {
		int updatedCells = 0;
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
			updatedCells = ps.executeBatch().length;
			con.setAutoCommit(true);
		} catch (SQLException e) {
			logger.severe("Could not update Cell(s) in " + databaseName + "." + cellsTable);
			logger.severe(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.severe("Could not roll back changes to " + databaseName + "." + cellsTable);
				logger.severe(e.getMessage());
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.severe("Could not close PreparedStatement");
					logger.severe(e.getMessage());
				}
			}
		    if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.severe("Could not close Connection");
					logger.severe(e.getMessage());
				}
			}
		}
		return updatedCells;
	}

	@WebMethod
	public int removeCells(@WebParam(name = "cellIDs") List<Integer> cellIDs) {
		int removedCells = 0;
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
			removedCells = ps.executeBatch().length;
			con.setAutoCommit(true);
		} catch (SQLException e) {
			logger.severe("Could not remove Cell(s) from " + databaseName + "." + cellsTable);
			logger.severe(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.severe("Could not roll back changes to " + databaseName + "." + cellsTable);
				logger.severe(e.getMessage());
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.severe("Could not close PreparedStatement");
					logger.severe(e.getMessage());
				}
			}
		    if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.severe("Could not close Connection");
					logger.severe(e.getMessage());
				}
			}
		}
		return removedCells;
	}
	
	@WebMethod
	@WebResult(name="cell")
	public List<Cell> getCells(@WebParam(name = "cellIDs") List<Integer> cellIDs) {
		List<Cell> cells = new ArrayList<Cell>();
		String s =
				"select CellID, " +
				"X, Y, " +
			    "AccessibleFromUp, AccessibleFromRight, " +
				"AccessibleFromDown, AccessibleFromLeft, " +
			    "SlopeInDegreesFromUp, SlopeInDegreesFromRight " +
			    "SlopeInDegreesFromDown, SlopeInDegreesFromLeft " +
			    "MaxWeight, MaxDiameter " +
			    "from " + databaseName + "." + cellsTable + " " +
			    "where CellID=?;";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(s);
			for (Integer cellID : cellIDs) {
				ps.setInt(1, cellID);
				ps.execute();
				rs = ps.getResultSet();

				while (rs.next()) {
					Cell cell = new Cell(
							rs.getInt("CellID"),
							rs.getInt("X"),
							rs.getInt("Y")
							);
					cell.setAccessibleFromUp(rs.getBoolean("AccessibleFromUp"));
					cell.setAccessibleFromRight(rs.getBoolean("AccessibleFromRight"));
					cell.setAccessibleFromDown(rs.getBoolean("AccessibleFromDown"));
					cell.setAccessibleFromLeft(rs.getBoolean("AccessibleFromLeft"));
					cell.setSlopeInDegreesFromUp(rs.getInt("SlopeInDegreesFromUp"));
					cell.setSlopeInDegreesFromRight(rs.getInt("SlopeInDegreesFromRight"));
					cell.setSlopeInDegreesFromDown(rs.getInt("SlopeInDegreesFromDown"));
					cell.setSlopeInDegreesFromLeft(rs.getInt("SlopeInDegreesFromLeft"));
					cell.setMaxWeight(rs.getInt("MaxWeight"));
					cell.setMaxDiameter(rs.getInt("MaxDiameter"));
					cells.add(cell);
				}
			}
		} catch (SQLException e) {
			logger.severe("Could not get Cell(s) from " + databaseName + "." + cellsTable);
			logger.severe(e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.severe("Could not close PreparedStatement");
					logger.severe(e.getMessage());
				}
			}
		    if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.severe("Could not close Connection");
					logger.severe(e.getMessage());
				}
			}
		}
		return cells;
	}

	@WebMethod
	public int addRoutes(@WebParam(name = "routes") List<Route> routes) {
		int addedRoutes = 0;
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
			addedRoutes = ps.executeBatch().length;
			con.setAutoCommit(true);
		} catch (SQLException e) {
			logger.severe("Could not add Route(s) to " + databaseName + "." + routesTable);
			logger.severe(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.severe("Could not roll back changes to " + databaseName + "." + routesTable);
				logger.severe(e.getMessage());
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.severe("Could not close PreparedStatement");
					logger.severe(e.getMessage());
				}
			}
		    if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.severe("Could not close Connection");
					logger.severe(e.getMessage());
				}
			}
		}
		return addedRoutes;
	}

	@WebMethod
	public int updateRoutes(@WebParam(name = "routes") List<Route> routes) {
		int updatedRoutes = 0;
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
			updatedRoutes = ps.executeBatch().length;
			con.setAutoCommit(true);
		} catch (SQLException e) {
			logger.severe("Could not update Cell(s) in " + databaseName + "." + cellsTable);
			logger.severe(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.severe("Could not roll back changes to " + databaseName + "." + routesTable);
				logger.severe(e.getMessage());
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.severe("Could not close PreparedStatement");
					logger.severe(e.getMessage());
				}
			}
		    if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.severe("Could not close Connection");
					logger.severe(e.getMessage());
				}
			}
		}
		return updatedRoutes;
	}

	@WebMethod
	public int removeRoutes(@WebParam(name = "routes") List<Route> routes) {
		int removedRoutes = 0;
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
			removedRoutes = ps.executeBatch().length;
			con.setAutoCommit(true);
		} catch (SQLException e) {
			logger.severe("Could not remove Route(s) from " + databaseName + "." + routesTable);
			logger.severe(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.severe("Could not roll back changes to " + databaseName + "." + routesTable);
				logger.severe(e.getMessage());
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.severe("Could not close PreparedStatement");
					logger.severe(e.getMessage());
				}
			}
		    if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.severe("Could not close Connection");
					logger.severe(e.getMessage());
				}
			}
		}
		return removedRoutes;
	}
	
	@WebMethod
	@WebResult(name="route")
	public List<Route> getRoutes(@WebParam(name = "transportAgentIDs") List<Integer> transportAgentIDs) {
		List<Route> routes = new ArrayList<Route>();
		String s =
				"select " +
				routesTable + ".TransportAgentID, " +
				routesTable + ".Timeframe, " +
				routesTable + ".CellID, " + 
				cellsTable + ".X, " +
				cellsTable + ".Y " +
			    "from " + databaseName + "." + routesTable + " " +
				"inner join " + databaseName + "." + cellsTable + " " +
			    "on " + databaseName + "." + routesTable + ".CellID" +
			    "=" + databaseName + "." + routesTable + ".CellID " +
			    "where TransportAgentID=?;";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(s);
			for (Integer transportAgentID : transportAgentIDs) {
				ps.setInt(1, transportAgentID);
				ps.execute();
				rs = ps.getResultSet();

				int currentTransportAgentID = -1;
				Route currentRoute = null;
				while (rs.next()) {
					if (rs.getInt("TransportAgentID") != currentTransportAgentID) {
						if (currentRoute != null) {
							routes.add(currentRoute);
						}
						currentTransportAgentID = rs.getInt("TransportAgentID");
						currentRoute = new Route();
					}
					currentRoute.getRoute().add(new RoutePoint(
							rs.getInt("TransportAgentID"),
							rs.getInt("Timeframe"),
							rs.getInt("CellID"),
							rs.getInt("X"),
							rs.getInt("Y")
							));
				}
			}
		} catch (SQLException e) {
			logger.severe("Could not get Route(s) from " + databaseName + "." + routesTable + " and " + cellsTable);
			logger.severe(e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.severe("Could not close PreparedStatement");
					logger.severe(e.getMessage());
				}
			}
		    if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.severe("Could not close Connection");
					logger.severe(e.getMessage());
				}
			}
		}
		return routes;
	}
}
