package object;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents a cell in a grid.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class Cell extends GridPoint {
	/**
	 * Whether a cell is accessible from the top when viewing the 0,0 coordinates
	 * of the grid as the lower left corner.
	 */
	@XmlElement
	private boolean accessibleFromUp;
	/**
	 * Whether a cell is accessible from the right when viewing the 0,0 coordinates
	 * of the grid as the lower left corner.
	 */
	@XmlElement
	private boolean accessibleFromRight;
	/**
	 * Whether a cell is accessible from the bottom when viewing the 0,0 coordinates
	 * of the grid as the lower left corner.
	 */
	@XmlElement
	private boolean accessibleFromDown;
	/**
	 * Whether a cell is accessible from the left when viewing the 0,0 coordinates
	 * of the grid as the lower left corner.
	 */
	@XmlElement
	private boolean accessibleFromLeft;
	/**
	 * The slope in degrees when the cell is accessed from the top when viewing
	 * the 0,0 coordinates of the grid as the lower left corner.
	 */
	@XmlElement
	private int slopeInDegreesFromUp;
	/**
	 * The slope in degrees when the cell is accessed from the right when viewing
	 * the 0,0 coordinates of the grid as the lower left corner.
	 */
	@XmlElement
	private int slopeInDegreesFromRight;
	/**
	 * The slope in degrees when the cell is accessed from the bottom when viewing
	 * the 0,0 coordinates of the grid as the lower left corner.
	 */
	@XmlElement
	private int slopeInDegreesFromDown;
	/**
	 * The slope in degrees when the cell is accessed from the left when viewing
	 * the 0,0 coordinates of the grid as the lower left corner.
	 */
	@XmlElement
	private int slopeInDegreesFromLeft;
	/**
	 * The maximal weight in gram with which the cell can be loaded.
	 */
	@XmlElement
	private int maxWeight;
	/**
	 * The maximal diameter in millimeter of an object which can access the cell.
	 */
	@XmlElement
	private int maxDiameter;
	
	public Cell() {
		this(-1, -1, -1);
	}
	
	/**
	 * @param cellID The ID of this cell.
	 */
	public Cell(int CellID, int x, int y) {
		super();
		this.setAccessibleFromUp(false);
		this.setAccessibleFromRight(false);
		this.setAccessibleFromDown(false);
		this.setAccessibleFromLeft(false);
		this.setSlopeInDegreesFromUp(0);
		this.setSlopeInDegreesFromRight(0);
		this.setSlopeInDegreesFromDown(0);
		this.setSlopeInDegreesFromLeft(0);
		this.setMaxWeight(0);
		this.setMaxDiameter(0);
	}

	/**
	 * @return Whether a cell is accessible from the top when viewing the 0,0
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
	 * @return Whether a cell is accessible from the right when viewing the 0,0
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
	 * @return Whether a cell is accessible from the bottom when viewing the 0,0
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
	 * @return Whether a cell is accessible from the left when viewing the 0,0
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
	
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(super.toString());
		buf.append("\naccessibleFromUp: " + this.isAccessibleFromUp());
		buf.append("\naccessibleFromRight: " + this.isAccessibleFromRight());
		buf.append("\naccessibleFromDown: " + this.isAccessibleFromDown());	
		buf.append("\naccessibleFromLeft: " + this.isAccessibleFromLeft());
		buf.append("\nslopeInDegreesFromUp: " + this.getSlopeInDegreesFromUp());
		buf.append("\nslopeInDegreesFromRight: " + this.getSlopeInDegreesFromRight());
		buf.append("\nslopeInDegreesFromDown: " + this.getSlopeInDegreesFromDown());
		buf.append("\nslopeInDegreesFromLeft: " + this.getSlopeInDegreesFromLeft());
		buf.append("\nmaxWeight: " + this.getMaxWeight());
		buf.append("\nmaxDiameter: " + this.getMaxDiameter());
		return buf.toString();
	}
}
