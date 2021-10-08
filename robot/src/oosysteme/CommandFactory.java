package oosysteme;

import lejos.nxt.LightSensor;
import lejos.nxt.NXTRegulatedMotor;
import oosysteme.commands.ManualControlCommand;
import oosysteme.commands.PidChangeCommand;
import oosysteme.commands.StrategyChangeCommand;
import oosysteme.commands.ZickZackOffsetChangeCommand;
import oosysteme.commands.ZickZackSpeedOffsetChangeCommand;

/**
 * Command factory to construct commands.
 */
public class CommandFactory implements Factory<CommandType, Command> {

	private final LightSensor lightSensor;

	private final NXTRegulatedMotor leftMotor;

	private final NXTRegulatedMotor rightMotor;
	
	private final StrategyController strategyController;

	/**
	 * CommandFactory.
	 * @param lightSensor light sensor
	 * @param leftMotor left motor
	 * @param rightMotor right motor
	 * @param strategyController to change strategy
	 */
	public CommandFactory(LightSensor lightSensor, 
			NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor, 
			StrategyController strategyController) {
		this.lightSensor = lightSensor;
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.strategyController = strategyController;
	}

	/**
	 * create command.
	 * @param type command type
	 * @return command
	 */
	public Command create(CommandType type) {
		switch (type) {
		case MANUAL_CONTROL:
			return new ManualControlCommand(lightSensor, leftMotor, rightMotor, 
					strategyController);
		case PID:
			return new PidChangeCommand(lightSensor, leftMotor, rightMotor, 
					strategyController);
		case STRATEGY_CHANGE:
			return new StrategyChangeCommand(lightSensor, leftMotor, rightMotor, 
					strategyController);
		case ZICKZACK_OFFSET:
			return new ZickZackOffsetChangeCommand(lightSensor, leftMotor, rightMotor, 
					strategyController);
		case ZICKZACK_SPEED_OFFSET:
			return new ZickZackSpeedOffsetChangeCommand(lightSensor, leftMotor, 
					rightMotor, strategyController);
		default:
			return null;
		}
	}
}
