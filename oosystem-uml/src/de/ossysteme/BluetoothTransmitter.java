package de.ossysteme;

import java.io.DataOutputStream;
import java.io.IOException;

public class BluetoothTransmitter implements Observer<Command> {

    private DataOutputStream connection;

    @Override
    public void onReceive(Command value) {
        try {
            connection.writeByte(value.getType().getValue());
            value.write(connection);
            connection.flush();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
