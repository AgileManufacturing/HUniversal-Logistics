package object;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents a transport agent.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class TransportAgent {
	/**
	 * The ID of the transport agent.
	 */
	@XmlElement
	private int transportAgentID;
	/** 
	 * The maximal amount of timeframes it takes to move one cell forward.
	 */
	@XmlElement
	private int maxFramesMoveOneCellForward;
	/** 
	 * The maximal amount of timeframes it takes to move one cell backward.
	 */
	@XmlElement
	private int maxFramesMoveOneCellBackward;
	/** 
	 * The maximal amount of timeframes it takes to turn 90 degrees clockwise.
	 */
	@XmlElement
	private int maxFramesTurnClockwise90;
	/** 
	 * The maximal amount of timeframes it takes to turn 180 degrees clockwise.
	 */
	@XmlElement
	private int maxFramesTurnClockwise180;
	/** 
	 * The maximal amount of timeframes it takes to turn 90 degrees counterclockwise.
	 */
	@XmlElement
	private int maxFramesTurnCounterclockwise90;
	/** 
	 * The maximal amount of timeframes it takes to turn 180 degrees counterclockwise.
	 */
	@XmlElement
	private int maxFramesTurnCounterclockwise180;
	/** 
	 * The weight in gram of the transport unit.
	 */
	@XmlElement
	private int weight;
	/**
	 * The length in millimeter of the transport unit.
	 */
	@XmlElement
	private int length;
	/**
	 * The width in millimeter of the transport unit.
	 */
	@XmlElement
	private int width;
	/**
	 * The height in millimeter of the transport unit.
	 */
	@XmlElement
	private int height;
	/**
	 * The maximal weight in gram of the product this transport agent can transport.
	 */
	@XmlElement
	private int maxProductWeight;
	/**
	 * The maximal length in millimeter of the product this transport agent can transport.
	 */
	@XmlElement
	private int maxProductLength;
	/**
	 * The maximal width in millimeter of the product this transport agent can transport.
	 */
	@XmlElement
	private int maxProductWidth;
	/**
	 * The maximal height in millimeter of the product this transport agent can transport.
	 */
	@XmlElement
	private int maxProductHeight;
	
	/**
	 * param transportAgentID The ID of the transport agent.
	 */	
	public TransportAgent() {
		this(-1);
	}
	
	/**
	 * param transportAgentID The ID of the transport agent.
	 */	
	public TransportAgent(int transportAgentID) {
		this.setTransportAgentID(transportAgentID);
		this.setMaxFramesMoveOneCellForward(-1);
		this.setMaxFramesMoveOneCellBackward(-1);
		this.setMaxFramesTurnClockwise90(-1);
		this.setMaxFramesTurnClockwise180(-1);
		this.setMaxFramesTurnCounterclockwise90(-1);
		this.setMaxFramesTurnCounterclockwise180(-1);
		this.setWeight(-1);
		this.setLength(-1);
		this.setWidth(-1);
		this.setHeight(-1);
		this.setMaxProductWeight(-1);
		this.setMaxProductLength(-1);
		this.setMaxProductWidth(-1);
		this.setMaxProductHeight(-1);
	}
	
	/**
	 * param transportAgentID The ID of the transport agent.
	 */	
	public void setTransportAgentID(int transportAgentID) {
		this.transportAgentID = transportAgentID;
	}
	
	/**
	 * @return The ID of the transport agent.
	 */	
	public int getTransportAgentID() {
		return transportAgentID;
	}

	/**
	 * @return The maximal amount of timeframes it takes to move one cell forward.
	 */
	public int getMaxFramesMoveOneCellForward() {
		return maxFramesMoveOneCellForward;
	}

	/**
	 * @param maxFramesMoveOneCellForward The maximal amount of timeframes it takes to move one cell forward.
	 */
	public void setMaxFramesMoveOneCellForward(int maxFramesMoveOneCellForward) {
		this.maxFramesMoveOneCellForward = maxFramesMoveOneCellForward;
	}

	/**
	 * @return The maximal amount of timeframes it takes to move one cell backward.
	 */
	public int getMaxFramesMoveOneCellBackward() {
		return maxFramesMoveOneCellBackward;
	}

	/**
	 * @param maxFramesMoveOneCellBackward The maximal amount of timeframes it takes to move one cell backward.
	 */
	public void setMaxFramesMoveOneCellBackward(int maxFramesMoveOneCellBackward) {
		this.maxFramesMoveOneCellBackward = maxFramesMoveOneCellBackward;
	}

	/**
	 * @return The maximal amount of timeframes it takes to turn 90 degrees clockwise.
	 */
	public int getMaxFramesTurnClockwise90() {
		return maxFramesTurnClockwise90;
	}

	/**
	 * @param maxFramesTurnClockwise90 The maximal amount of timeframes it takes to turn 90 degrees clockwise.
	 */
	public void setMaxFramesTurnClockwise90(int maxFramesTurnClockwise90) {
		this.maxFramesTurnClockwise90 = maxFramesTurnClockwise90;
	}

	/**
	 * @return The maximal amount of timeframes it takes to turn 180 degrees clockwise.
	 */
	public int getMaxFramesTurnClockwise180() {
		return maxFramesTurnClockwise180;
	}

	/**
	 * @param maxFramesTurnClockwise180 The maximal amount of timeframes it takes to turn 180 degrees clockwise.
	 */
	public void setMaxFramesTurnClockwise180(int maxFramesTurnClockwise180) {
		this.maxFramesTurnClockwise180 = maxFramesTurnClockwise180;
	}

	/**
	 * @return The maximal amount of timeframes it takes to turn 90 degrees counterclockwise.
	 */
	public int getMaxFramesTurnCounterclockwise90() {
		return maxFramesTurnCounterclockwise90;
	}

	/**
	 * @param maxFramesTurnCounterclockwise90 The maximal amount of timeframes it takes to turn 90 degrees counterclockwise.
	 */
	public void setMaxFramesTurnCounterclockwise90(int maxFramesTurnCounterclockwise90) {
		this.maxFramesTurnCounterclockwise90 = maxFramesTurnCounterclockwise90;
	}

	/**
	 * @return The maximal amount of timeframes it takes to turn 180 degrees counterclockwise.
	 */
	public int getMaxFramesTurnCounterclockwise180() {
		return maxFramesTurnCounterclockwise180;
	}

	/**
	 * @param maxFramesTurnCounterClockwise180 The maximal amount of timeframes it takes to turn 180 degrees counterclockwise.
	 */
	public void setMaxFramesTurnCounterclockwise180(
			int maxFramesTurnCounterClockwise180) {
		this.maxFramesTurnCounterclockwise180 = maxFramesTurnCounterClockwise180;
	}

	/**
	 * @return The weight in gram of the transport unit.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight The weight in gram of the transport unit.
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return The length in millimeter of the transport unit.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length The length in millimeter of the transport unit.
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return The width in millimeter of the transport unit.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width The width in millimeter of the transport unit.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return The height in millimeter of the transport unit.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height The height in millimeter of the transport unit.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return The maximal weight in gram of the product this transport agent can transport.
	 */
	public int getMaxProductWeight() {
		return maxProductWeight;
	}

	/**
	 * @param maxProductWeight The maximal weight in gram of the product this transport agent can transport.
	 */
	public void setMaxProductWeight(int maxProductWeight) {
		this.maxProductWeight = maxProductWeight;
	}

	/**
	 * @return The maximal length in millimeter of the product this transport agent can transport.
	 */
	public int getMaxProductLength() {
		return maxProductLength;
	}

	/**
	 * @param maxProductLength The maximal length in millimeter of the product this transport agent can transport.
	 */
	public void setMaxProductLength(int maxProductLength) {
		this.maxProductLength = maxProductLength;
	}

	/**
	 * @return The maximal width in millimeter of the product this transport agent can transport.
	 */
	public int getMaxProductWidth() {
		return maxProductWidth;
	}

	/**
	 * @param maxProductWidth The maximal width in millimeter of the product this transport agent can transport.
	 */
	public void setMaxProductWidth(int maxProductWidth) {
		this.maxProductWidth = maxProductWidth;
	}

	/**
	 * @return The maximal height in millimeter of the product this transport agent can transport.
	 */
	public int getMaxProductHeight() {
		return maxProductHeight;
	}

	/**
	 * @param maxProductHeight The maximal height in millimeter of the product this transport agent can transport.
	 */
	public void setMaxProductHeight(int maxProductHeight) {
		this.maxProductHeight = maxProductHeight;
	}
	
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("\ntransportAgentID: " + this.getTransportAgentID());
		buf.append("\nmaxFramesMoveOneCellForward: " + this.getMaxFramesMoveOneCellForward());
		buf.append("\nmaxFramesMoveOneCelBackward: " + this.getMaxFramesMoveOneCellForward());
		buf.append("\nmaxFramesTurnClockwise90: " + this.getMaxFramesTurnClockwise90());
		buf.append("\nmaxFramesTurnClockwise180: " + this.getMaxFramesTurnClockwise180());
		buf.append("\nmaxFramesTurnCounterclockwise90: " + this.getMaxFramesTurnCounterclockwise90());
		buf.append("\nmaxFramesTurnCounterclockwise180: " + this.getMaxFramesTurnCounterclockwise180());
		buf.append("\nweight: " + this.getWeight());
		buf.append("\nlength: " + this.getLength());
		buf.append("\nwidth: " + this.getWidth());
		buf.append("\nheight: " + this.getHeight());
		buf.append("\nmaxProductWeight: " + this.getMaxProductWeight());
		buf.append("\nmaxProductLength: " + this.getMaxProductLength());
		buf.append("\nmaxProductWidth: " + this.getMaxProductWidth());
		buf.append("\nmaxProductHeight: " + this.getMaxProductHeight());
		return buf.toString();
	}
}
