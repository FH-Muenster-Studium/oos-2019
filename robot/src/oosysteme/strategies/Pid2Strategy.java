package oosysteme.strategies;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.util.PIDController;
import oosysteme.BaseStrategy;

/**
 * Pid strategy.
 */
public class Pid2Strategy extends BaseStrategy {
	
	private final PIDController pidController;
    
    /**
     * pid constructor.
     * @param pvalue p
     * @param ivalue i
     * @param dvalue d
     */
    public Pid2Strategy(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor, 
			float pvalue, float ivalue, float dvalue) {
		super(lightSensor, leftMotor, rightMotor);
		this.pidController = new PIDController(0, 10);

        this.pidController.setPIDParam(PIDController.PID_KP, pvalue);
        this.pidController.setPIDParam(PIDController.PID_KI, ivalue);
        this.pidController.setPIDParam(PIDController.PID_KD, dvalue);
	}
	
	public void setP(float pvalue) {
		this.pidController.setPIDParam(PIDController.PID_KP, pvalue);
	}
	
	public void setI(float ivalue) {
		this.pidController.setPIDParam(PIDController.PID_KI, ivalue);
	}
	
	public void setD(float dvalue) {
		this.pidController.setPIDParam(PIDController.PID_KD, dvalue);
	}
    
    /**
	 * Executes when strategy gets active and sets motor speeds.
	 */
    @Override
    public void onStart() {
    	Motor.A.forward();
    	Motor.B.forward();
    }
    
    /**
	 * Executes when strategy gets inactive.
	 */
    @Override
    public void onStop() {
    }
    
    /**
     * Calculates motor speeds via pid controller.
     */
    @Override
    public void onRun() {
        int bias = lightSensor.getLightValue() - 50;
  		int result = pidController.doPID(bias);
  		LCD.drawInt(result, 6, 6);
  		float leftSpeed;
  		float rightSpeed;
  		leftSpeed = 250 + result;
  		rightSpeed = 250 - result;
  		leftMotor.setSpeed(leftSpeed);
  		rightMotor.setSpeed(rightSpeed);
    }

}
