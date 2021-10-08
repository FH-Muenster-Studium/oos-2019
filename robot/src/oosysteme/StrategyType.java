package oosysteme;

/**
 * StrategType.
 */
public enum StrategyType {
	CALIBRATE(0), ZICKZACK(1), ZICKZACK_HALT(2), 
	MANUAL_CONTROL(3), PID(4), PID_HALT(5), FIND_LINE(6),
	OVERTAKE_LEFT(7),OVERTAKE_RIGHT(8),OVERTAKE_RIGHT_CIRCLE(9),ZICKZACK2(10);
	
	private final int index;
	
	/**
	 * Creates strategy type with index.
	 * @param index index of strategy type
	 */
	private StrategyType(int index) {
		this.index = index;
	}
	
	/**
	 * type index.
	 * @return type index as byte
	 */
	public int getIndex() {
		return this.index;
	}
}