package object;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents a point in a grid.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
abstract public class GridPoint {
	/**
	 * The ID of the cell at this point.
	 */
	@XmlElement
	protected int cellID;
	/**
	 * The x-coordinate of the point in a grid.
	 */	
	@XmlElement
	protected int x;
	/**
	 * The y-coordinate of the point in a grid.
	 */
	@XmlElement
	protected int y;
	
	public GridPoint() {
		this(-1, -1, -1);
	}
	
	public GridPoint(int setCellID, int x, int y) {
		this.setCellID(setCellID);
		this.setX(x);
		this.setY(y);
	}
	
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
	
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("\ncellID: " + this.getCellID());
		buf.append("\nx: " + this.getX());
		buf.append("\ny: " + this.getY());
		return buf.toString();
	}
}
