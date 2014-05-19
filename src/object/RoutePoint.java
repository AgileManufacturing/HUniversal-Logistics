package objects;

public class RoutePoint extends GridPoint {
	/**
	 * The timeframe in which a cell is occupied.
	 */
	private int transportAgentID;
	
	/**
	 * The timeframe in which a cell is occupied.
	 */
	private int timeframe;

	/**
	 * @param cellID The ID of the cell.
	 * @param x The x-coordinate of the cell.
	 * @param y The y-coordinate of the cell.
	 * @param timeframe The timeframe in which a cell is occupied.
	 */
	public RoutePoint(int transportAgentID, int timeframe, int cellID, int x, int y) {
		this.transportAgentID = transportAgentID;
		this.timeframe = timeframe;
		this.setCellID(cellID);
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * @return The ID of the TransportAgent which occupies a cell.
	 */
	public int getTransportAgentID() {
		return transportAgentID;
	}
	
	/**
	 * @param transportAgentID The ID of the TransportAgent which occupies a cell.
	 */
	public void setTransportAgentID(int transportAgentID) {
		this.transportAgentID = transportAgentID;
	}
	
	/**
	 * @return The timeframe in which a cell is occupied.
	 */
	public int getTimeframe() {
		return timeframe;
	}

	/**
	 * @param timeframe The timeframe in which a cell is occupied.
	 */
	public void setTimeframe(int timeframe) {
		this.timeframe = timeframe;
	}
}
