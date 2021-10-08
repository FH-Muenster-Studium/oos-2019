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

/**
 * Command is used to send new active strategy type to bluetooth connection
 * and receive new strategy to use from bluetooth connection.
 */
public class StrategyChangeCommand extends BaseCommand {
	
	private final StrategyType[] types;

	private StrategyType type;
	
	/**
	 * Creates strategy change command with given motors and sensors to control the robot.
	 * @param lightSensor light sensor of robot
	 * @param leftMotor left motor of robot
	 * @param rightMotor right motor of robot
	 * @param strategyController current strategy controller
	 */
	public StrategyChangeCommand(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor,
			StrategyController strategyController) {
		super(lightSensor, leftMotor, rightMotor, strategyController);
		this.types = StrategyType.values();
	}
	
	/**
	 * Command type.
	 * @return command type
	 */
	@Override
	public CommandType getType() {
		return CommandType.STRATEGY_CHANGE;
	}
	
	/**
	 * Sets strategy type that gets serialized.
	 * @param type strategy type to serialize
	 */
	public void setType(StrategyType type) {
		this.type = type;
	}
	
	/**
	 * Deserializes the command.
	 * @param stream stream to read command from
	 */
	@Override
	public void read(DataInputStream stream) throws IOException {
		byte currType = stream.readByte();
		if (currType < 0 || currType >= types.length) return;
		type = types[currType];
	}
	
	/**
	 * Serializes the command.
	 * @param stream stream to write command to
	 */
	@Override
	public void write(DataOutputStream stream) throws IOException {
		stream.write((byte) type.getIndex());
	}
	
	/**
	 * Sets recevied strategy type as next active strategy.
	 */
	@Override
	public void execute() {
		if (type != null) {
			strategyController.setStrategy(type);
		}
	}
}
