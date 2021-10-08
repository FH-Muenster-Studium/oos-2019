package oosysteme.strategies;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import oosysteme.BaseStrategy;

/**
 * Zickzack strategy.
 */
public class ZickZackStrategy extends BaseStrategy {
	
	private static final int TARGET_VALUE = 50;
	private static final int SPEED = 500;
	
	private volatile int offset = 200;
	
	private volatile int speedOffset = 0;
	
	/**
	 * Zick zack strategy.
	 * @param lightSensor light sensor
	 * @param leftMotor left motor
	 * @param rightMotor right motor
	 */
	public ZickZackStrategy(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor) {
		super(lightSensor, leftMotor, rightMotor);
	}
	
	public void setSpeedOffset(int offset) {
		this.speedOffset = offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	/**
	 * Drives on line via zick zack.
	 */
	@Override
	public void onRun() {
		int delta = lightSensor.getLightValue() - TARGET_VALUE;
		
		if (delta < 0) {
			leftMotor.setSpeed(SPEED + speedOffset);
			rightMotor.setSpeed(SPEED - offset + speedOffset);
		} else {
			leftMotor.setSpeed(SPEED - offset + speedOffset);
			rightMotor.setSpeed(SPEED + speedOffset);
		}
	}
	
	/**
	 * Executes when strategy gets active and activates motors.
	 */
	@Override
	public void onStart() {
		leftMotor.forward();
		rightMotor.forward();
	}
	
	/**
	 * Executes when strategy gets inactive.
	 */
	@Override
	public void onStop() {
	}
}
