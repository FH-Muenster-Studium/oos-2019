package oosysteme;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Command interface.
 */
public interface Command {
	/**
	 * execute.
	 */
	void execute();
	
	/**
	 * type.
	 * @return current command type
	 */
	CommandType getType();
	
	/**
	 * reads stream and changes command properties.
	 * @param stream stream
	 * @throws IOException exception
	 */
	void read(DataInputStream stream) throws IOException;
	
	/**
	 * serializes command to stream.
	 * @param stream stream
	 * @throws IOException exception
	 */
	void write(DataOutputStream stream) throws IOException;
}
