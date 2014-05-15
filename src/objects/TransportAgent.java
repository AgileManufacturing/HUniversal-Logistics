package objects;

public class TransportAgent {
	private int transportAgentID;
	private int maxFramesMoveOneCellForward;
	private int maxFramesMoveOneCellBackward;
	private int maxFramesTurnClockwise90;
	private int maxFramesTurnClockwise180;
	private int maxFramesTurnCounterClockwise90;
	private int maxFramesTurnCounterClockwise180;
	private int weight;
	private int length;
	private int width;
	private int height;
	private int maxProductWeight;
	private int maxProductLength;
	private int maxProductWidth;
	private int maxProductHeight;
	
	public TransportAgent(int transportAgentID) {
		this.transportAgentID = transportAgentID;
		this.maxFramesMoveOneCellForward = -1;
		this.maxFramesMoveOneCellBackward = -1;
		this.maxFramesTurnClockwise90 = -1;
		this.maxFramesTurnClockwise180 = -1;
		this.maxFramesTurnCounterClockwise90 = -1;
		this.maxFramesTurnCounterClockwise180 = -1;
		this.weight = -1;
		this.length = -1;
		this.width = -1;
		this.height = -1;
		this.maxProductWeight = -1;
		this.maxProductLength = -1;
		this.maxProductWidth = -1;
		this.maxProductHeight = -1;
	}
	
	public int getTransportAgentID() {
		return transportAgentID;
	}

	/**
	 * @return the maxFramesMoveOneCellForward
	 */
	public int getMaxFramesMoveOneCellForward() {
		return maxFramesMoveOneCellForward;
	}

	/**
	 * @param maxFramesMoveOneCellForward the maxFramesMoveOneCellForward to set
	 */
	public void setMaxFramesMoveOneCellForward(int maxFramesMoveOneCellForward) {
		this.maxFramesMoveOneCellForward = maxFramesMoveOneCellForward;
	}

	/**
	 * @return the maxFramesMoveOneCellBackward
	 */
	public int getMaxFramesMoveOneCellBackward() {
		return maxFramesMoveOneCellBackward;
	}

	/**
	 * @param maxFramesMoveOneCellBackward the maxFramesMoveOneCellBackward to set
	 */
	public void setMaxFramesMoveOneCellBackward(int maxFramesMoveOneCellBackward) {
		this.maxFramesMoveOneCellBackward = maxFramesMoveOneCellBackward;
	}

	/**
	 * @return the maxFramesTurnClockwise90
	 */
	public int getMaxFramesTurnClockwise90() {
		return maxFramesTurnClockwise90;
	}

	/**
	 * @param maxFramesTurnClockwise90 the maxFramesTurnClockwise90 to set
	 */
	public void setMaxFramesTurnClockwise90(int maxFramesTurnClockwise90) {
		this.maxFramesTurnClockwise90 = maxFramesTurnClockwise90;
	}

	/**
	 * @return the maxFramesTurnClockwise180
	 */
	public int getMaxFramesTurnClockwise180() {
		return maxFramesTurnClockwise180;
	}

	/**
	 * @param maxFramesTurnClockwise180 the maxFramesTurnClockwise180 to set
	 */
	public void setMaxFramesTurnClockwise180(int maxFramesTurnClockwise180) {
		this.maxFramesTurnClockwise180 = maxFramesTurnClockwise180;
	}

	/**
	 * @return the maxFramesTurnCounterClockwise90
	 */
	public int getMaxFramesTurnCounterClockwise90() {
		return maxFramesTurnCounterClockwise90;
	}

	/**
	 * @param maxFramesTurnCounterClockwise90 the maxFramesTurnCounterClockwise90 to set
	 */
	public void setMaxFramesTurnCounterClockwise90(
			int maxFramesTurnCounterClockwise90) {
		this.maxFramesTurnCounterClockwise90 = maxFramesTurnCounterClockwise90;
	}

	/**
	 * @return the maxFramesTurnCounterClockwise180
	 */
	public int getMaxFramesTurnCounterClockwise180() {
		return maxFramesTurnCounterClockwise180;
	}

	/**
	 * @param maxFramesTurnCounterClockwise180 the maxFramesTurnCounterClockwise180 to set
	 */
	public void setMaxFramesTurnCounterClockwise180(
			int maxFramesTurnCounterClockwise180) {
		this.maxFramesTurnCounterClockwise180 = maxFramesTurnCounterClockwise180;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the maxProductWeight
	 */
	public int getMaxProductWeight() {
		return maxProductWeight;
	}

	/**
	 * @param maxProductWeight the maxProductWeight to set
	 */
	public void setMaxProductWeight(int maxProductWeight) {
		this.maxProductWeight = maxProductWeight;
	}

	/**
	 * @return the maxProductLength
	 */
	public int getMaxProductLength() {
		return maxProductLength;
	}

	/**
	 * @param maxProductLength the maxProductLength to set
	 */
	public void setMaxProductLength(int maxProductLength) {
		this.maxProductLength = maxProductLength;
	}

	/**
	 * @return the maxProductWidth
	 */
	public int getMaxProductWidth() {
		return maxProductWidth;
	}

	/**
	 * @param maxProductWidth the maxProductWidth to set
	 */
	public void setMaxProductWidth(int maxProductWidth) {
		this.maxProductWidth = maxProductWidth;
	}

	/**
	 * @return the maxProductHeight
	 */
	public int getMaxProductHeight() {
		return maxProductHeight;
	}

	/**
	 * @param maxProductHeight the maxProductHeight to set
	 */
	public void setMaxProductHeight(int maxProductHeight) {
		this.maxProductHeight = maxProductHeight;
	}
}
