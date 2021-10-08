package oosysteme.commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import oosysteme.BaseCommand;
import oosysteme.CommandType;
import oosysteme.StrategyController;
import oosysteme.StrategyType;
import oosysteme.strategies.Pid2Strategy;

/**
 * Receives pid values from bluetooth connection and uses the new values for the pid strategy.
 */
public class PidChangeCommand extends BaseCommand {

	private float pvalue;
	
	private float ivalue;
	
	private float dvalue;
	
	/**
	 * Creates pid change command with given motors and sensors to control the robot.
	 * @param lightSensor light sensor of robot
	 * @param leftMotor left motor of robot
	 * @param rightMotor right motor of robot
	 * @param strategyController current strategy controller
	 */
	public PidChangeCommand(LightSensor lightSensor, NXTRegulatedMotor 
			leftMotor, NXTRegulatedMotor rightMotor,
			StrategyController strategyController) {
		super(lightSensor, leftMotor, rightMotor, strategyController);
	}
	
	/**
	 * Command type.
	 * @return command type
	 */
	@Override
	public CommandType getType() {
		return CommandType.PID;
	}
	
	/**
	 * Deserializes the command.
	 * @param stream stream to read command from
	 */
	@Override
	public void read(DataInputStream stream) throws IOException {
		pvalue = stream.readFloat();
		ivalue = stream.readFloat();
		dvalue = stream.readFloat();
	}
	
	/**
	 * Serializes the command.
	 * @param stream stream to write command to
	 */
	@Override
	public void write(DataOutputStream stream) throws IOException {
		stream.writeFloat(pvalue);
		stream.writeFloat(ivalue);
		stream.writeFloat(dvalue);
	}

	/**
	 * Sets the new received pid values to pid strategy.
	 */
	@Override
	public void execute() {
		Pid2Strategy strategy = strategyController.get(StrategyType.PID);
		strategy.setP(pvalue);
		strategy.setI(ivalue);
		strategy.setD(dvalue);
	}
}
