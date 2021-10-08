package oosysteme;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;

/**
 * BaseCommand implementation that all other commands should implement
 * to reduce code for initialization.
 *
 */
public abstract class BaseCommand implements Command {
	
	public final LightSensor lightSensor;
	
	public final NXTRegulatedMotor leftMotor;
	public final NXTRegulatedMotor rightMotor;
	
	public final StrategyController strategyController;
	
	/**
	 * base command constructor that initializes the base command with all required values.
	 * @param lightSensor left sensor
	 * @param leftMotor left motor
	 * @param rightMotor right motor
	 */
	public BaseCommand(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, 
			NXTRegulatedMotor rightMotor,
			StrategyController strategyController) {
		this.lightSensor = lightSensor;	
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.strategyController = strategyController;
	}
}
