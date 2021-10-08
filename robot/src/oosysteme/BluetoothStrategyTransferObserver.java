package oosysteme;

import oosysteme.commands.StrategyChangeCommand;

/**
 * Sends the current active strategy to the bluetooth device.
 */
public class BluetoothStrategyTransferObserver<T extends 
Repository<CommandType, Command> & Observer<Command>> implements Observer<StrategyType> {
	
	private final T commandRepositoryAndObserver;
	
	/**
	 * constructor.
	 * @param commandRepositoryAndObserver command receiver that sends
	 *        and received commands to bluetooth device
	 */
	public BluetoothStrategyTransferObserver(T commandRepositoryAndObserver) {
		this.commandRepositoryAndObserver = commandRepositoryAndObserver;
	}
	
	/**
	 * Receives strategy type to send via bluetooth connection.
	 * @param strategyType strategy type to send
	 */
	@Override
	public void onReceive(StrategyType strategyType) {
		StrategyChangeCommand command = commandRepositoryAndObserver.get(
				CommandType.STRATEGY_CHANGE
				);
		command.setType(strategyType);
		commandRepositoryAndObserver.onReceive(command);
	}
}
