package de.ossysteme;

import java.io.InputStream;
import java.io.OutputStream;

public interface Command {
    void execute();

    void read(InputStream inputStream);

    void write(OutputStream outputStream);

    CommandType getType();
}
