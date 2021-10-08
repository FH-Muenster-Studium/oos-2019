package oosysteme.strategies;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import oosysteme.BaseStrategy;

/**
 * Pid strategy.
 */
public class PidStrategy extends BaseStrategy {

	private static final int TARGET = 50;
	private float pvalue = 1.5f;
	private float ivalue = .0023f;
	private float dvalue = 0.015f;
	private static final float BASE_SPEED = 500;
	int[] lastErrors = new int[100];
	int lastErrorIndex = 0;
	int filledErrors = 0;
	float leftSpeed;
	float rightSpeed;
	int sensorData;
	float integral = 0;
	float lastErr = 0; 
	float deriv = 0; 

	/**
	 * Pid strategy.
	 * @param lightSensor light sensor
	 * @param leftMotor left motor
	 * @param rightMotor right motor
	 */
	public PidStrategy(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor) {
		super(lightSensor, leftMotor, rightMotor);
	}
	
	/**
	 * drives.
	 * @param l left speed
	 * @param r right speed
	 */
	public void drive(float l, float r) {
		// A-> to left B-> to right

		leftMotor.setSpeed(Math.abs(l));
		rightMotor.setSpeed(Math.abs(r));
		if (l > 0) {
			leftMotor.forward();
		} else if (l < 0) {
			leftMotor.backward();
		} else {
			leftMotor.stop(true);
		}

		if (r > 0) {
			rightMotor.forward();
		} else if (r < 0) {
			rightMotor.backward();
		} else {
			rightMotor.stop(true);
		}
	}
	
	/**
	 * Adds a error value.
	 * @param error error value
	 */
	void addError(int error) {
		lastErrors[lastErrorIndex] = error;
		lastErrorIndex += 1;
		lastErrorIndex %= 100;
		if (filledErrors < 100) {
			filledErrors += 1;
		}
	}

	/**
	 * Calculates medium error.
	 * @return medium error value
	 */
	float calcMediumError() {
		int sum = 0;
		for (int i = 0, length = filledErrors;i < length;i++) {
			sum += lastErrors[i];
		}
		return sum / filledErrors;
	}
	
	/**
	 * Set p value.
	 * @param pvalue p
	 */
	public void setP(float pvalue) {
		this.pvalue = pvalue;
	}
	
	/**
	 * Set i value.
	 * @param ivalue i
	 */
	public void setI(float ivalue) {
		this.ivalue = ivalue;
	}
	
	/**
	 * Set d value.
	 * @param dvalue d
	 */
	public void setD(float dvalue) {
		this.dvalue = dvalue;
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

	/**
	 * Calculates motor speeds via pid calculations.
	 */
	@Override
	public void onRun() {
		sensorData = lightSensor.getLightValue();
		int err = TARGET - sensorData;

		integral *= 0.98;
		integral += err;
		deriv = err - lastErr; 
		lastErr = err; 

		leftSpeed = BASE_SPEED + pvalue * err + ivalue * integral + dvalue * deriv; 
		rightSpeed = BASE_SPEED - (pvalue * err + ivalue * integral + dvalue * deriv);
		LCD.drawString(String.valueOf(integral), 0, 4);
		LCD.drawString(String.valueOf(deriv), 0, 5);
		drive(leftSpeed, rightSpeed);
	}
}
