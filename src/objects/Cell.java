package objects;

/**
 * Represents a cell in a grid.
 */
public class Cell extends GridPoint {
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
	 * The maximal diameter in millimeter of an object which can access the cell.
	 */
	private int maxDiameter;
	
	/**
	 * @param cellID The ID of this cell.
	 */
	public Cell(int CellID) {
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
		this.setCellID(cellID);
		this.setX(-1);
		this.setY(-1);
	}

	/**
	 * @return 	Whether a cell is accessible from the top when viewing the 0,0
	 * coordinates of the grid as the lower left corner.
	 */
	public boolean isAccessibleFromUp() {
		return accessibleFromUp;
	}

	/**
	 * @param accessibleFromUp Whether a cell is accessible from the top when
	 * viewing the 0,0 coordinates of the grid as the lower left corner.
	 */
	public void setAccessibleFromUp(boolean accessibleFromUp) {
		this.accessibleFromUp = accessibleFromUp;
	}

	/**
	 * @return 	Whether a cell is accessible from the right when viewing the 0,0
	 * coordinates of the grid as the lower left corner.
	 */
	public boolean isAccessibleFromRight() {
		return accessibleFromRight;
	}

	/**
	 * @param accessibleFromUp Whether a cell is accessible from the right when
	 * viewing the 0,0 coordinates of the grid as the lower left corner.
	 */
	public void setAccessibleFromRight(boolean accessibleFromRight) {
		this.accessibleFromRight = accessibleFromRight;
	}

	/**
	 * @return 	Whether a cell is accessible from the bottom when viewing the 0,0
	 * coordinates of the grid as the lower left corner.
	 */
	public boolean isAccessibleFromDown() {
		return accessibleFromDown;
	}

	/**
	 * @param accessibleFromUp Whether a cell is accessible from the bottom when
	 * viewing the 0,0 coordinates of the grid as the lower left corner.
	 */
	public void setAccessibleFromDown(boolean accessibleFromDown) {
		this.accessibleFromDown = accessibleFromDown;
	}

	/**
	 * @return 	Whether a cell is accessible from the left when viewing the 0,0
	 * coordinates of the grid as the lower left corner.
	 */
	public boolean isAccessibleFromLeft() {
		return accessibleFromLeft;
	}

	/**
	 * @param accessibleFromUp Whether a cell is accessible from the left when
	 * viewing the 0,0 coordinates of the grid as the lower left corner.
	 */
	public void setAccessibleFromLeft(boolean accessibleFromLeft) {
		this.accessibleFromLeft = accessibleFromLeft;
	}

	/**
	 * @return The slope in degrees when the cell is accessed from the top when
	 * viewing the 0,0 coordinates of the grid as the lower left corner.
	 */
	public int getSlopeInDegreesFromUp() {
		return slopeInDegreesFromUp;
	}

	/**
	 * @param slopeInDegreesFromUp The slope in degrees when the cell is accessed
	 * from the top when viewing the 0,0 coordinates of the grid as the lower left
	 * corner.
	 */
	public void setSlopeInDegreesFromUp(int slopeInDegreesFromUp) {
		this.slopeInDegreesFromUp = slopeInDegreesFromUp;
	}

	/**
	 * @return The slope in degrees when the cell is accessed from the right when
	 * viewing the 0,0 coordinates of the grid as the lower left corner.
	 */
	public int getSlopeInDegreesFromRight() {
		return slopeInDegreesFromRight;
	}

	/**
	 * @param slopeInDegreesFromRight The slope in degrees when the cell is accessed
	 * from the right when viewing the 0,0 coordinates of the grid as the lower left
	 * corner.
	 */
	public void setSlopeInDegreesFromRight(int slopeInDegreesFromRight) {
		this.slopeInDegreesFromRight = slopeInDegreesFromRight;
	}

	/**
	 * @return The slope in degrees when the cell is accessed from the bottom when
	 * viewing the 0,0 coordinates of the grid as the lower left corner.
	 */
	public int getSlopeInDegreesFromDown() {
		return slopeInDegreesFromDown;
	}

	/**
	 * @param slopeInDegreesFromDown The slope in degrees when the cell is accessed
	 * from the bottom when viewing the 0,0 coordinates of the grid as the lower left
	 * corner.
	 */
	public void setSlopeInDegreesFromDown(int slopeInDegreesFromDown) {
		this.slopeInDegreesFromDown = slopeInDegreesFromDown;
	}

	/**
	 * @return The slope in degrees when the cell is accessed from the left when
	 * viewing the 0,0 coordinates of the grid as the lower left corner.
	 */
	public int getSlopeInDegreesFromLeft() {
		return slopeInDegreesFromLeft;
	}

	/**
	 * @param slopeInDegreesFromLeft The slope in degrees when the cell is accessed
	 * from the left when viewing the 0,0 coordinates of the grid as the lower left
	 * corner.
	 */
	public void setSlopeInDegreesFromLeft(int slopeInDegreesFromLeft) {
		this.slopeInDegreesFromLeft = slopeInDegreesFromLeft;
	}

	/**
	 * @return The maximal weight in gram with which the cell can be loaded.
	 */
	public int getMaxWeight() {
		return maxWeight;
	}

	/**
	 * @param maxWeight The maximal weight in gram with which the cell can be loaded.
	 */
	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	/**
	 * @return The maximal diameter in millimeter of an object which can access the cell.
	 */
	public int getMaxDiameter() {
		return maxDiameter;
	}

	/**
	 * @param maxDiameter The maximal diameter in millimeter of an object which can access the cell.
	 */
	public void setMaxDiameter(int maxDiameter) {
		this.maxDiameter = maxDiameter;
	}
}
