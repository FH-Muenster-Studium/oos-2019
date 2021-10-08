package oosysteme;

/**
 * Observer interface.
 *
 * @param <ValueT> observable value type
 */
public interface Observer<ValueT> {
	/**
	 * receives a value.
	 * @param value value
	 */
	void onReceive(ValueT value);
}
