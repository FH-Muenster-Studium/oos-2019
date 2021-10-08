package oosysteme.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ZickZackOffsetChangeCommand implements Command {

    private int offset;

    public ZickZackOffsetChangeCommand() {
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public CommandType getType() {
        return CommandType.ZICKZACK_OFFSET;
    }

    @Override
    public void execute() {
    }

    @Override
    public void read(DataInputStream stream) throws IOException {
    }

    @Override
    public void write(DataOutputStream stream) throws IOException {
        stream.writeInt(offset);
    }
}
