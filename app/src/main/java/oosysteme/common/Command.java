package oosysteme.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface Command {
    void execute();

    CommandType getType();

    void read(DataInputStream stream) throws IOException;

    void write(DataOutputStream stream) throws IOException;
}
