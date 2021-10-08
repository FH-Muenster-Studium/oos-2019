package oosysteme;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import oosysteme.strategies.CalibrateStrategy;
import oosysteme.strategies.DistanceHaltStrategy;
import oosysteme.strategies.FindLineStrategy;
import oosysteme.strategies.ManualControlStrategy;
import oosysteme.strategies.OvertakeLeftStrategy;
import oosysteme.strategies.OvertakeRightCircleStrategy;
import oosysteme.strategies.OvertakeRightStrategy;
import oosysteme.strategies.Pid2Strategy;
import oosysteme.strategies.ZickZackStrategy;
import oosysteme.strategies.ZickZackStrategy2;

/**
 * Creates correct strategy for strategy type.
 */
public class StrategyFactory implements Factory<StrategyType, Strategy> {

	private final LightSensor lightSensor;

	private final NXTRegulatedMotor leftMotor;

	private final NXTRegulatedMotor rightMotor;

	/**
	 * StrategyFactory.
	 * @param lightSensor light sensor
	 * @param leftMotor left motor
	 * @param rightMotor right motor
	 */
	public StrategyFactory(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor) {
		this.lightSensor = lightSensor;
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}

	/**
	 * create strategy.
	 * @param type strategy type
	 * @return strategy
	 */
	public Strategy create(StrategyType type) {
		switch (type) {
		case ZICKZACK:
			return new ZickZackStrategy(lightSensor, leftMotor, rightMotor);
		case ZICKZACK2:
			return new ZickZackStrategy2(lightSensor, leftMotor, rightMotor);
		case FIND_LINE:
			return new FindLineStrategy(lightSensor, leftMotor, rightMotor);
		case MANUAL_CONTROL:
			return new ManualControlStrategy(lightSensor, leftMotor, rightMotor);
		case PID:
			return new Pid2Strategy(lightSensor, leftMotor, rightMotor, 10f, 1f, 1f);
		case OVERTAKE_LEFT:
			return new OvertakeLeftStrategy(lightSensor, leftMotor, rightMotor);
		case OVERTAKE_RIGHT:
			return new OvertakeRightStrategy(lightSensor, leftMotor, rightMotor);
		case OVERTAKE_RIGHT_CIRCLE:
			return new OvertakeRightCircleStrategy(lightSensor, leftMotor, rightMotor);
		case CALIBRATE:
			return new CalibrateStrategy(lightSensor, leftMotor, rightMotor);
		case PID_HALT:
		case ZICKZACK_HALT:
			return new DistanceHaltStrategy(lightSensor, leftMotor, rightMotor);
		default:
			return null;
		}
	}
}
