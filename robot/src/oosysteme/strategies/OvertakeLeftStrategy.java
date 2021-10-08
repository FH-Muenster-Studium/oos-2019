package oosysteme.strategies;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import oosysteme.BaseStrategy;
import oosysteme.StrategyType;

/**
 * Overtake left strategy.
 */
public class OvertakeLeftStrategy extends BaseStrategy {

	private long lastMillis;
	
	private int baseSpeed = 1000;
	
	private int slowBaseSpeed = 500;
	
	/**
	 * Overtake strategy.
	 * @param lightSensor light sensor
	 * @param leftMotor left motor
	 * @param rightMotor right motor
	 */
	public OvertakeLeftStrategy(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor) {
		super(lightSensor, leftMotor, rightMotor);
	}
	
	/**
	 * Overtakes via left curve.
	 */
	@Override
	public void onRun() {
		if (lastMillis + 750 < System.currentTimeMillis()) {
			leftMotor.setSpeed(baseSpeed);
			if (lightSensor.getLightValue() >= 50) {
				setStrategy(/*getPreviousStrategy()*/StrategyType.ZICKZACK);
			}
		}
	}

	/**
	 * Executes when strategy gets active and set motors to base speed
	 * and initializes millisecond value.
	 */
	@Override
	public void onStart() {
		rightMotor.setSpeed(baseSpeed);
		leftMotor.setSpeed(slowBaseSpeed);
		lastMillis = System.currentTimeMillis();
	}

	/**
	 * Gets called when strategy gets inactive.
	 */
	@Override
	public void onStop() {
	}
}
