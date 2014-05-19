package objects;

/**
 * Represents a point in a grid.
 */
abstract public class GridPoint {
	/**
	 * The ID of the cell at this point.
	 */
	protected int cellID;
	/**
	 * The x-coordinate of the point in a grid.
	 */	
	protected int x;
	/**
	 * The y-coordinate of the point in a grid.
	 */
	protected int y;
	
	/**
	 * @return The ID of the cell at this point.
	 */
	public int getCellID() {
		return cellID;
	}
	
	/**
	 * @param cellID The ID of the cell at this point.
	 */
	public void setCellID(int cellID) {
		this.cellID = cellID;
	}
	
	/**
	 * @return The x-coordinate of the point in a grid.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param x The x-coordinate of the point in a grid.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return The y-coordinate of the point in a grid.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param y The y-coordinate of the point in a grid.
	 */
	public void setY(int y) {
		this.y = y;
	}
}
