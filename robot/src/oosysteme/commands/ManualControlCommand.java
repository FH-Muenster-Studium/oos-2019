package oosysteme.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import oosysteme.BaseCommand;
import oosysteme.CommandType;
import oosysteme.StrategyController;

/**
 * Command that gets written to bluetooth connection to change robot left and right motor speeds.
 *
 */
public class ManualControlCommand extends BaseCommand {

	private short leftSpeed;

	private short rightSpeed;
	
	/**
	 * Creates manual control command with given motors and sensors to control the robot.
	 * @param lightSensor light sensor of robot
	 * @param leftMotor left motor of robot
	 * @param rightMotor right motor of robot
	 * @param strategyController current strategy controller
	 */
	public ManualControlCommand(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor,
			StrategyController strategyController) {
		super(lightSensor, leftMotor, rightMotor, strategyController);
	}

	/**
	 * Command type.
	 * @return command type
	 */
	@Override
	public CommandType getType() {
		return CommandType.MANUAL_CONTROL;
	}

	/**
	 * Executes command and set the left right speed to the motors.
	 */
	@Override
	public void execute() {
		if (leftSpeed > 0) {
			leftMotor.setSpeed(leftSpeed);
			leftMotor.forward();
		} else if (leftSpeed == 0) {
			leftMotor.stop(true);
		} else {
			leftMotor.setSpeed(-leftSpeed);
			leftMotor.backward();
		}

		if (rightSpeed > 0) {
			rightMotor.setSpeed(rightSpeed);
			rightMotor.forward();
		} else if (rightSpeed == 0) {
			rightMotor.stop(true);
		} else {
			rightMotor.setSpeed(-rightSpeed);
			rightMotor.backward();
		}
	}

	/**
	 * Deserializes the command.
	 * @param stream stream to read command from
	 */
	@Override
	public void read(DataInputStream stream) throws IOException {	
		leftSpeed = stream.readShort();
		rightSpeed = stream.readShort();
		LCD.drawInt(leftSpeed, 2, 6);
		LCD.drawInt(rightSpeed, 6, 6);
	}

	/**
	 * Serializes the command.
	 * @param stream stream to write command to
	 */
	@Override
	public void write(DataOutputStream stream) throws IOException {
		stream.writeShort(leftSpeed);
		stream.writeShort(rightSpeed);
	}
}
