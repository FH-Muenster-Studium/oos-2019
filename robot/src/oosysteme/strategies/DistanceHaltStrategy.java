package oosysteme.strategies;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import oosysteme.BaseStrategy;

/**
 * Distance halt strategy that gets called when robot needs to stop because of object in front.
 */
public class DistanceHaltStrategy extends BaseStrategy {

	/**
	 * Distance halt strategy.
	 * @param lightSensor light sensor
	 * @param leftMotor left motor
	 * @param rightMotor right motor
	 */
	public DistanceHaltStrategy(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor) {
		super(lightSensor, leftMotor, rightMotor);
	}
	
	/**
	 * Executes when strategy gets active and deactivates motors.
	 */
	@Override
	public void onStart() {
		leftMotor.stop(true);
		rightMotor.stop(true);
	}
	
	/**
	 * Executes when strategy gets inactive.
	 */
	@Override
	public void onStop() {
	}
	
	/**
	 * Runs strategy.
	 */
	@Override
	public void onRun() {
	}
}
