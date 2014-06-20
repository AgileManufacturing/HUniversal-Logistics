package object;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents single timeframe of a {@link object.Route}.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class RoutePoint extends GridPoint {
	/**
	 * The timeframe in which a cell is occupied.
	 */
	@XmlElement
	private int transportAgentID;
	
	/**
	 * The timeframe in which a cell is occupied.
	 */
	@XmlElement
	private int timeframe;

	public RoutePoint() {
		this(-1, -1, -1, -1, -1);
	}
	
	/**
	 * @param transportAgentID The ID of the transport agent.
	 * @param timeframe The timeframe in which a cell is occupied.
	 * @param cellID The ID of the cell.
	 * @param x The x-coordinate of the cell.
	 * @param y The y-coordinate of the cell.
	 */
	public RoutePoint(int transportAgentID, int timeframe, int cellID, int x, int y) {
		super(cellID, x, y);
		this.setTransportAgentID(transportAgentID);
		this.setTimeframe(timeframe);
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
	
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(super.toString());
		buf.append("\ntransportAgentID: " + this.getTransportAgentID());
		buf.append("\ntimeframe: " + this.getTimeframe());
		return buf.toString();
	}
}
