package oosysteme.strategies;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import oosysteme.BaseStrategy;
import oosysteme.StrategyType;

/**
 * Find line strategy.
 */
public class FindLineStrategy extends BaseStrategy {

	private static final int TARGET_VALUE = 30;
	private static final int MAX_SPEED = 600;
	private static final float MAX_RATIO = 0.8f;

	private long lastMillis;
	private float ratio;

	public FindLineStrategy(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor) {
		super(lightSensor, leftMotor, rightMotor);
	}

	/**
	 * Drives while line not found.
	 */
	@Override
	public void onRun() {
		int delta = lightSensor.getLightValue() - TARGET_VALUE;

		if (delta < 0) {
			setStrategy(StrategyType.ZICKZACK);
		} else {
			leftMotor.setSpeed(MAX_SPEED * ratio);
			rightMotor.setSpeed(MAX_SPEED);
		}

		if (lastMillis + 250 < System.currentTimeMillis()) {
			lastMillis = System.currentTimeMillis();
			if (ratio < MAX_RATIO)
				ratio += 0.008f;
		}
	}

	/**
	 * Executes when strategy gets active and activates motors and sets initiale values.
	 */
	@Override
	public void onStart() {
		lastMillis = 0;
		ratio = 0.4f;

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
