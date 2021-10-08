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
import oosysteme.strategies.ZickZackStrategy;

public class ZickZackSpeedOffsetChangeCommand extends BaseCommand {
	
	private int speedOffset;
	
	/**
	 * Creates zick zack change command with given motors and sensors to control the robot.
	 * @param lightSensor light sensor of robot
	 * @param leftMotor left motor of robot
	 * @param rightMotor right motor of robot
	 * @param strategyController current strategy controller
	 */
	public ZickZackSpeedOffsetChangeCommand(LightSensor lightSensor, NXTRegulatedMotor 
			leftMotor, NXTRegulatedMotor rightMotor,
			StrategyController strategyController) {
		super(lightSensor, leftMotor, rightMotor, strategyController);
	}
	
	public void setSpeedOffset(int offset) {
		this.speedOffset = offset;
	}
	
	/**
	 * Command type.
	 * @return command type
	 */
	@Override
	public CommandType getType() {
		return CommandType.ZICKZACK_SPEED_OFFSET;
	}
	
	/**
	 * Deserializes the command.
	 * @param stream stream to read command from
	 */
	@Override
	public void read(DataInputStream stream) throws IOException {
		speedOffset = stream.readInt();
	}
	
	/**
	 * Serializes the command.
	 * @param stream stream to write command to
	 */
	@Override
	public void write(DataOutputStream stream) throws IOException {
		stream.writeInt(speedOffset);
	}

	/**
	 * Sets the new received pid values to pid strategy.
	 */
	@Override
	public void execute() {
		ZickZackStrategy strategy = strategyController.get(StrategyType.ZICKZACK);
		strategy.setSpeedOffset(speedOffset);
	}
}
