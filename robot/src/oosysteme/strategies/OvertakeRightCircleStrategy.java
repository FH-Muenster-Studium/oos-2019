package oosysteme.strategies;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import oosysteme.BaseStrategy;
import oosysteme.StrategyType;

/**
 * Overtake right strategy.
 */
public class OvertakeRightCircleStrategy extends BaseStrategy {
	
	private final int overtakeStart = 0;
	
	private final int overtakeEnd = 1;
	
	private final int overtakeStartTime = 1000;
	
	private final int overtakeEndTime = 3000;
	
	private int state = overtakeStart;
	
	private long lastMillis;
	
	/**
	 * Overtake strategy.
	 * @param lightSensor light sensor
	 * @param leftMotor left motor
	 * @param rightMotor right motor
	 */
	public OvertakeRightCircleStrategy(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor) {
		super(lightSensor, leftMotor, rightMotor);
	}
	
	/**
	 * Overtakes via right curve.
	 */
	@Override
	public void onRun() {
		if (state == overtakeStart) {
			leftMotor.setSpeed(1000);
			rightMotor.setSpeed(800);
			//rightMotor.setSpeed(500 + ((lastMillis / overtakeStartTime) * 500));
			if (lastMillis + overtakeStartTime >= System.currentTimeMillis()) {
				lastMillis = System.currentTimeMillis();
				state = overtakeEnd;
			}
		} else if (state == overtakeEnd) {
			leftMotor.setSpeed(750);
			rightMotor.setSpeed(750 + ((lastMillis / overtakeEndTime) * 250));
			if (lastMillis + overtakeEndTime >= System.currentTimeMillis()) {
				if (lightSensor.getLightValue() >= 50) {
					setStrategy(/*getPreviousStrategy()*/StrategyType.ZICKZACK);
				}
			}
		}
	}

	/**
	 * Executes when strategy gets active and set motors to base speed
	 * and initializes millisecond value.
	 */
	@Override
	public void onStart() {
		leftMotor.setSpeed(1000);
		rightMotor.setSpeed(800);
		lastMillis = System.currentTimeMillis();
		state = overtakeStart;
	}

	/**
	 * Gets called when strategy gets inactive.
	 */
	@Override
	public void onStop() {
	}
}