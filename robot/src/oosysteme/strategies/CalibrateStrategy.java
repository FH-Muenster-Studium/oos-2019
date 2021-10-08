package oosysteme.strategies;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import oosysteme.BaseStrategy;
import oosysteme.StrategyType;

/**
 * Calibrate strategy.
 */
public class CalibrateStrategy extends BaseStrategy {
	
	/**
	 * Calibrate strategy.
	 * @param lightSensor light sensor
	 * @param leftMotor left motor
	 * @param rightMotor right motor
	 */
	public CalibrateStrategy(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor) {
		super(lightSensor, leftMotor, rightMotor);
	}
	
	/**
	 * Executes manual calibrate.
	 */
	@Override
	public void onRun() {
		LCD.drawString("calibrate black", 0, 0);
		Button.waitForAnyPress();
		lightSensor.calibrateLow();
		LCD.drawString("calibrate white", 0, 0);
		Button.waitForAnyPress();
		lightSensor.calibrateHigh();
		
		LCD.clear();
		setStrategy(StrategyType.ZICKZACK);
	}
	
	/**
	 * Executes when strategy gets active.
	 */
	@Override
	public void onStart() {
	}
	
	/**
	 * Executes when strategy gets inactive.
	 */
	@Override
	public void onStop() {
	}
}
