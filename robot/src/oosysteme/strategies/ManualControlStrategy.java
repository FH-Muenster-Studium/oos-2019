package oosysteme.strategies;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import oosysteme.BaseStrategy;

/**
 * Manual control strategy that doesn't influence motors so bluetooth connection can control it.
 */
public class ManualControlStrategy extends BaseStrategy {

	/**
	 * Manual control strategy.
	 * @param lightSensor light sensor
	 * @param leftMotor left motor
	 * @param rightMotor right motor
	 */
	public ManualControlStrategy(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor) {
		super(lightSensor, leftMotor, rightMotor);
	}
	
	/**
	 * Executes strategy.
	 */
	@Override
	public void onRun() {
	}
	
	/**
	 * Executes when strategy gets active.
	 */
	@Override
	public void onStart() {
	}
	
	/**
	 * Executes when strategy gets inactive and stops motors.
	 */
	@Override
	public void onStop() {
		leftMotor.stop(true);
		rightMotor.stop(true);
	}
}
