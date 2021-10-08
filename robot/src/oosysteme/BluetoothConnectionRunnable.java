package oosysteme;

import java.io.DataInputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

/**
 * BluetoothConnectionRunnable that handles the bluetooth connection.
 *
 */
public class BluetoothConnectionRunnable implements Runnable {

	private final BluetoothController controller;

	private BTConnection connection;

	/**
	 * Initializes the BluetoothConnectionRunnable with the bluetooth controller.
	 * @param controller bluetooth controller the runnable  belongs to
	 */
	public BluetoothConnectionRunnable(BluetoothController controller) {
		this.controller = controller;
	}

	/**
	 * Executes the connection establishment.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				LCD.drawString("con", 0, 4);
				connection = Bluetooth.waitForConnection();
				LCD.drawString("con2", 0, 4);
				connection.setIOMode(BTConnection.PACKET);
				controller.setConnection(connection);
				DataInputStream inputStream = connection.openDataInputStream();
				byte type;
				while (true) {
					type = inputStream.readByte();
					LCD.drawString("avail", 0, 4);
					controller.onReceive(type, inputStream);	
				}
			} catch (IOException exception) {
				LCD.drawString("exc", 0, 8);
			}
		}
	}
}
