package oosysteme;

/**
 * Repository interface.
 *
 * @param <TypeT> type to identify stored value
 * @param <ValueT> value for the given type
 */
public interface Repository<TypeT, ValueT> {
	/**
	 * Returns the current object for the given type.
	 * @param <T> object type
	 * @param type given type
	 * @return current object reference
	 */
	 public <T extends ValueT> T get(TypeT type);
}
