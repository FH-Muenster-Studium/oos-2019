package oosysteme.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ZickZackSpeedOffsetChangeCommand implements Command {

    private int speedOffset;

    public ZickZackSpeedOffsetChangeCommand() {
    }

    public void setSpeedOffset(int offset) {
        this.speedOffset = offset;
    }

    @Override
    public CommandType getType() {
        return CommandType.ZICKZACK_SPEED_OFFSET;
    }

    @Override
    public void execute() {
    }

    @Override
    public void read(DataInputStream stream) throws IOException {
    }

    @Override
    public void write(DataOutputStream stream) throws IOException {
        stream.writeInt(speedOffset);
    }
}
