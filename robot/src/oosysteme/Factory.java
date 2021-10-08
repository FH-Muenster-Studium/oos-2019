package oosysteme;

/**
 * Generic factory interface.
 *
 * @param <TypeT> type that is required to create object
 * @param <ValueT> object created from the type
 */
public interface Factory<TypeT, ValueT> {
	/**
	 * Creates ValueT from TypeT.
	 * @param type type of value to create
	 * @return value created from type
	 */
	ValueT create(TypeT type);
}
