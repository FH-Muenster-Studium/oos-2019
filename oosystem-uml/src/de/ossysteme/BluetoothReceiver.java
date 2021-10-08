package de.ossysteme;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BluetoothReceiver extends BaseObservable<Command> implements Observer<InputStream>, Runnable {

    private Factory<Command, CommandType> factory;

    // Receives command from bluetooth connection from phone
    @Override
    public void onReceive(InputStream value) {
        try {
            byte type = ((DataInputStream) value).readByte();
            Command command = factory.create(CommandType.fromByte(type));
            command.read(value);
            sendValue(command);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    // Sends command over bluetooth connection to phone
    /*@Override
    public void onReceive(Command value) {

    }*/

    @Override
    public void run() {

    }
}
