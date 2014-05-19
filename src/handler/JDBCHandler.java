package handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.sql.DataSource;

import objects.Cell;
import objects.Route;
import objects.TransportAgent;

/**
 * Handles database connections and commands.
 * For databases which can be used with the Java JDBC API.
 * 
 */
@WebService
public class JDBCHandler extends DatabaseHandler {
	//private static Logger logger = Logger.getLogger(JDBCHandler.class.getName());
	protected DataSource ds;
	
	public JDBCHandler() {

	}

	@Override
	public void addTransportAgent(@WebParam(name = "transportAgent") TransportAgent transportAgent) throws SQLException {
		String s = 
				"insert into " + databaseName + "." + transportAgentsTable + " (" +
				"TransportAgentID, " +
				"MaxFramesMoveOneCellForward, MaxFramesMoveOneCellBackward, " + 
			    "MaxFramesTurnClockwise90, MaxFramesTurnClockwise180, " +
			    "MaxFramesTurnCounterclockwise90, MaxFramesTurnCounterclockwise180, " +
			    "Weight, Length, Width, Height, " +
			    "MaxProductWeight, MaxProductLength, " +
			    "MaxProductWidth, MaxProductHeight " +
			    ") " +
				"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(s);
			ps.setInt(1, transportAgent.getTransportAgentID());
			ps.setInt(2, transportAgent.getMaxFramesMoveOneCellForward());
			ps.setInt(3, transportAgent.getMaxFramesMoveOneCellBackward());
			ps.setInt(4, transportAgent.getMaxFramesTurnClockwise90());
			ps.setInt(5, transportAgent.getMaxFramesTurnClockwise180());
			ps.setInt(6, transportAgent.getMaxFramesTurnCounterclockwise90());
			ps.setInt(7, transportAgent.getMaxFramesTurnCounterClockwise180());
			ps.setInt(8, transportAgent.getWeight());
			ps.setInt(9, transportAgent.getLength());
			ps.setInt(10, transportAgent.getWidth());
			ps.setInt(11, transportAgent.getHeight());
			ps.setInt(12, transportAgent.getMaxProductWeight());
			ps.setInt(13, transportAgent.getMaxProductLength());
			ps.setInt(14, transportAgent.getMaxProductWidth());
			ps.setInt(15, transportAgent.getMaxProductHeight());
			ps.executeUpdate();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}
	
	@Override
	public void updateTransportAgent(@WebParam(name = "transportAgent") TransportAgent transportAgent) throws SQLException {
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
			ps = con.prepareStatement(s);
			ps.setInt(1, transportAgent.getMaxFramesMoveOneCellForward());
			ps.setInt(2, transportAgent.getMaxFramesMoveOneCellBackward());
			ps.setInt(3, transportAgent.getMaxFramesTurnClockwise90());
			ps.setInt(4, transportAgent.getMaxFramesTurnClockwise180());
			ps.setInt(5, transportAgent.getMaxFramesTurnCounterclockwise90());
			ps.setInt(6, transportAgent.getMaxFramesTurnCounterClockwise180());
			ps.setInt(7, transportAgent.getWeight());
			ps.setInt(8, transportAgent.getLength());
			ps.setInt(9, transportAgent.getWidth());
			ps.setInt(10, transportAgent.getHeight());
			ps.setInt(11, transportAgent.getMaxProductWeight());
			ps.setInt(12, transportAgent.getMaxProductLength());
			ps.setInt(13, transportAgent.getMaxProductWidth());
			ps.setInt(14, transportAgent.getMaxProductHeight());
			ps.setInt(15, transportAgent.getTransportAgentID());
			ps.executeUpdate();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}

	@Override
	public void removeTransportAgent(@WebParam(name = "transportAgent") TransportAgent transportAgent) throws SQLException {
		String s = 
				"delete from " + databaseName + "." + transportAgentsTable + " " +
				"where TransportAgentID=?;";
				
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(s);
			ps.setInt(1, transportAgent.getTransportAgentID());
			ps.executeUpdate();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}

	@Override
	public void addCell(@WebParam(name = "cell") Cell cell) throws SQLException {
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
			ps = con.prepareStatement(s);
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
			ps.executeUpdate();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}

	@Override
	public void updateCell(@WebParam(name = "cell") Cell cell) throws SQLException {
		String s = 
				"update " + databaseName + "." + cellsTable + " set " +
				"X=?, Y=?, " +
			    "AccessibleFromUp=?, AccessibleFromRight=?, " +
				"AccessibleFromDown=?, AccessibleFromLeft=?, " +
			    "SlopeInDegreesFromUp=?, SlopeInDegreesFromRight=? " +
			    "SlopeInDegreesFromDown=?, SlopeInDegreesFromLeft=? " +
			    "MaxWeight=?, MaxDiameter=?" +
			    "where CellID=?;";
				
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(s);
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
			ps.executeUpdate();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}

	@Override
	public void removeCell(@WebParam(name = "cell") Cell cell) throws SQLException {
		String s = 
				"delete from " + databaseName + "." + cellsTable + " " +
				"where CellID=?;";
				
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(s);
			ps.setInt(1, cell.getCellID());
			ps.executeUpdate();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		} finally {
			if (ps != null) { ps.close(); }
		    if (con != null) { con.close(); }
		}
	}

	@Override
	public void addRoute(@WebParam(name = "route") Route route) {
		
	}

	@Override
	public void updateRoute(@WebParam(name = "route") Route route) {
		
	}

	@Override
	public void removeRotue(@WebParam(name = "route") Route route) {
		
	}
	

}
