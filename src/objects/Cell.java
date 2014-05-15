package objects;

/**
 * Represents a cell in a grid.
 */
public class Cell {
	/**
	 * The ID of this cell.
	 */
	private int cellID;
	/**
	 * The x-coordinate of the cell in a grid.
	 */	
	private int x;
	/**
	 * The y-coordinate of the cell in a grid.
	 */
	private int y;
	/**
	 * Whether a cell is accessible from the top when viewing the 0,0 coordinates
	 * of the grid as the lower left corner.
	 */
	private boolean accessibleFromUp;
	/**
	 * Whether a cell is accessible from the right when viewing the 0,0 coordinates
	 * of the grid as the lower left corner.
	 */
	private boolean accessibleFromRight;
	/**
	 * Whether a cell is accessible from the bottom when viewing the 0,0 coordinates
	 * of the grid as the lower left corner.
	 */
	private boolean accessibleFromDown;
	/**
	 * Whether a cell is accessible from the left when viewing the 0,0 coordinates
	 * of the grid as the lower left corner.
	 */
	private boolean accessibleFromLeft;
	/**
	 * The slope in degrees when the cell is accessed from the top when viewing
	 * the 0,0 coordinates of the grid as the lower left corner.
	 */
	private int slopeInDegreesFromUp;
	/**
	 * The slope in degrees when the cell is accessed from the right when viewing
	 * the 0,0 coordinates of the grid as the lower left corner.
	 */
	private int slopeInDegreesFromRight;
	/**
	 * The slope in degrees when the cell is accessed from the bottom when viewing
	 * the 0,0 coordinates of the grid as the lower left corner.
	 */
	private int slopeInDegreesFromDown;
	/**
	 * The slope in degrees when the cell is accessed from the left when viewing
	 * the 0,0 coordinates of the grid as the lower left corner.
	 */
	private int slopeInDegreesFromLeft;
	/**
	 * The maximal weight in gram with which the cell can be loaded.
	 */
	private int maxWeight;
	/**
	 * The maximal diameter in centimeter of an object which can acces the cell.
	 */
	private int maxDiameter;
	
	/**
	 * @param cellID The ID of this cell.
	 */
	public Cell(int CellID) {
		this.cellID = CellID;
		this.x = -1;
		this.y = -1;
		this.accessibleFromUp = false;
		this.accessibleFromRight = false;
		this.accessibleFromDown = false;
		this.accessibleFromLeft = false;
		this.slopeInDegreesFromUp = 0;
		this.slopeInDegreesFromRight = 0;
		this.slopeInDegreesFromDown = 0;
		this.slopeInDegreesFromLeft = 0;
		this.maxWeight = 0;
		this.maxDiameter = 0;
	}

	/**
	 * @return The ID of this cell.
	 */
	public int getCellID() {
		return cellID;
	}

	/**
	 * @param cellID The ID of this cell.
	 */
	public void setCellID(int cellID) {
		this.cellID = cellID;
	}
	
	/**
	 * @return The x-coordinate of the cell in a grid.
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x The x-coordinate of the cell in a grid.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return The y-coordinate of the cell in a grid.
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y The y-coordinate of the cell in a grid.
	 */
	public void setY(int y) {
	}

	/**
	 * @return the accessibleFromUp
	 */
	public boolean isAccessibleFromUp() {
		return accessibleFromUp;
	}

	/**
	 * @param accessibleFromUp the accessibleFromUp to set
	 */
	public void setAccessibleFromUp(boolean accessibleFromUp) {
		this.accessibleFromUp = accessibleFromUp;
	}

	/**
	 * @return the accessibleFromRight
	 */
	public boolean isAccessibleFromRight() {
		return accessibleFromRight;
	}

	/**
	 * @param accessibleFromRight the accessibleFromRight to set
	 */
	public void setAccessibleFromRight(boolean accessibleFromRight) {
		this.accessibleFromRight = accessibleFromRight;
	}

	/**
	 * @return the accessibleFromDown
	 */
	public boolean isAccessibleFromDown() {
		return accessibleFromDown;
	}

	/**
	 * @param accessibleFromDown the accessibleFromDown to set
	 */
	public void setAccessibleFromDown(boolean accessibleFromDown) {
		this.accessibleFromDown = accessibleFromDown;
	}

	/**
	 * @return the accessibleFromLeft
	 */
	public boolean isAccessibleFromLeft() {
		return accessibleFromLeft;
	}

	/**
	 * @param accessibleFromLeft the accessibleFromLeft to set
	 */
	public void setAccessibleFromLeft(boolean accessibleFromLeft) {
		this.accessibleFromLeft = accessibleFromLeft;
	}

	/**
	 * @return the slopeInDegreesFromUp
	 */
	public int getSlopeInDegreesFromUp() {
		return slopeInDegreesFromUp;
	}

	/**
	 * @param slopeInDegreesFromUp the slopeInDegreesFromUp to set
	 */
	public void setSlopeInDegreesFromUp(int slopeInDegreesFromUp) {
		this.slopeInDegreesFromUp = slopeInDegreesFromUp;
	}

	/**
	 * @return the slopeInDegreesFromRight
	 */
	public int getSlopeInDegreesFromRight() {
		return slopeInDegreesFromRight;
	}

	/**
	 * @param slopeInDegreesFromRight the slopeInDegreesFromRight to set
	 */
	public void setSlopeInDegreesFromRight(int slopeInDegreesFromRight) {
		this.slopeInDegreesFromRight = slopeInDegreesFromRight;
	}

	/**
	 * @return the slopeInDegreesFromDown
	 */
	public int getSlopeInDegreesFromDown() {
		return slopeInDegreesFromDown;
	}

	/**
	 * @param slopeInDegreesFromDown the slopeInDegreesFromDown to set
	 */
	public void setSlopeInDegreesFromDown(int slopeInDegreesFromDown) {
		this.slopeInDegreesFromDown = slopeInDegreesFromDown;
	}

	/**
	 * @return the slopeInDegreesFromLeft
	 */
	public int getSlopeInDegreesFromLeft() {
		return slopeInDegreesFromLeft;
	}

	/**
	 * @param slopeInDegreesFromLeft the slopeInDegreesFromLeft to set
	 */
	public void setSlopeInDegreesFromLeft(int slopeInDegreesFromLeft) {
		this.slopeInDegreesFromLeft = slopeInDegreesFromLeft;
	}

	/**
	 * @return the maxWeight
	 */
	public int getMaxWeight() {
		return maxWeight;
	}

	/**
	 * @param maxWeight the maxWeight to set
	 */
	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	/**
	 * @return the maxDiameter
	 */
	public int getMaxDiameter() {
		return maxDiameter;
	}

	/**
	 * @param maxDiameter the maxDiameter to set
	 */
	public void setMaxDiameter(int maxDiameter) {
		this.maxDiameter = maxDiameter;
	}
}
