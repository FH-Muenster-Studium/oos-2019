package oosysteme;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;

/**
 * The BaseStrategy each strategy should implement.
 *
 */
public abstract class BaseStrategy implements Strategy {
	
	private StrategyController strategyController;
	
	public final LightSensor lightSensor;
	
	public final NXTRegulatedMotor leftMotor;
	public final NXTRegulatedMotor rightMotor;
	
	/**
	 * constructor.
	 * @param lightSensor left sensor
	 * @param leftMotor left motor
	 * @param rightMotor right motor
	 */
	public BaseStrategy(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, 
			NXTRegulatedMotor rightMotor) {
		this.lightSensor = lightSensor;	
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}
	
	/**
	 * sets next strategy.
	 * @param nextStrategy next strategy
	 */
	public void setStrategy(StrategyType nextStrategy) {
		this.strategyController.setStrategy(nextStrategy);
	}
	
	/**
	 * Returns the active strategy type.
	 * @return current active strategy type
	 */
	public StrategyType getActiveStrategy() {
		return this.strategyController.getActiveStrategy();
	}
	
	/**
	 * Returns the previews active strategy type.
	 * @return previews active strategy type
	 */
	public StrategyType getPreviousStrategy() {
		return this.strategyController.getPreviousStrategy();
	}
	
	/**
	 * Sets the strategy controller the strategy belongs to.
	 * @param strategyController sets the strategy controller the strategy belongs to
	 */
	public void setStrategyController(StrategyController strategyController) {
		this.strategyController = strategyController;
	}
}
