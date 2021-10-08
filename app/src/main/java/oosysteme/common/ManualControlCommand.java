package oosysteme.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ManualControlCommand implements Command {

    private short leftSpeed;

    private short rightSpeed;

    public ManualControlCommand() {
    }

    public void setLeftSpeed(short leftSpeed) {
        this.leftSpeed = leftSpeed;
    }

    public void setRightSpeed(short rightSpeed) {
        this.rightSpeed = rightSpeed;
    }

    public short getLeftSpeed() {
        return leftSpeed;
    }

    public short getRightSpeed() {
        return rightSpeed;
    }

    @Override
    public CommandType getType() {
        return CommandType.MANUAL_CONTROL;
    }

    @Override
    public void execute() {
    }

    @Override
    public void read(DataInputStream stream) throws IOException {
        leftSpeed = stream.readShort();
        rightSpeed = stream.readShort();
    }

    @Override
    public void write(DataOutputStream stream) throws IOException {
        stream.writeShort(leftSpeed);
        stream.writeShort(rightSpeed);
    }
}
