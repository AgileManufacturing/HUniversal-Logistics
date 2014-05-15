package objects;

public class RoutePoint extends GridPoint {
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
	public RoutePoint(int cellID, int x, int y, int timeframe) {
		this.cellID = cellID;
		this.x = x;
		this.y = y;
		this.timeframe = timeframe;
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
