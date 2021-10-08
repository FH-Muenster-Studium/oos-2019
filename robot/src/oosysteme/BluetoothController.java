package oosysteme;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;

/**
 * BluetoothController that manages the bluetooth commands and connections.
 *
 */
public class BluetoothController implements Observer<Command>, Repository<CommandType, Command> {

	private final Thread thread;
	
	private DataOutputStream outputStream;
	
	private final Command[] commands;

	/**
	 * bluetooth controller constructor.
	 * @param factory command factory
	 */
	public BluetoothController(Factory<CommandType, Command> factory) {
		CommandType[] types = CommandType.values();
		int length = types.length;
		this.commands = new Command[length];
		for (int i = 0;i < length;i++) {
			this.commands[i] = factory.create(types[i]);
		}
		this.thread = new Thread(new BluetoothConnectionRunnable(this));
		this.thread.start();
	}
	
	/**
	 * Returns a command to a given command byte type.
	 * @param <T> Command
	 * @param type command type
	 * @return the current command object
	 */
	@SuppressWarnings("unchecked")
	public <T extends Command> T get(byte type) {
		if (type < 0 || type >= this.commands.length) return null;
		return (T)this.commands[type];
	}
	
	/**
	 * Returns a command to a given command type.
	 * @param <T> Command
	 * @param type command type
	 * @return the current command object
	 */
	public <T extends Command> T get(CommandType type) {
		return get(type.getIndex());
	}

	/**
	 * Sets the current active bluetooth connection.
	 * @param connection active bluetooth connection
	 */
	public void setConnection(BTConnection connection) {
		this.outputStream = connection.openDataOutputStream();
	}

	/**
	 * receives message.
	 * @param stream stream
	 */
	public void onReceive(byte type, DataInputStream stream) {
		try {
			LCD.drawInt(type, 8, 4);
			Command command = get(type);
			if (command == null) return;
			command.read(stream);
			command.execute();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * sends message.
	 * @param command command
	 */
	@Override
	public void onReceive(Command command) {
		try {
			DataOutputStream stream = outputStream;
			if (stream == null) return;
			stream.writeByte(command.getType().getIndex());
			command.write(stream);
			stream.flush();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
