package oosysteme;

/**
 * Observable interface.
 *
 * @param <T> observable value type
 */
public interface Observable<T> {
	
	/**
	 * add a observer.
	 * @param observer observer
	 */
	void addObserver(Observer<T> observer);
	
	/**
	 * remove a observer.
	 * @param observer observer
	 */
	void removeObserver(Observer<T> observer);
	
	/**
	 * sends values to observers.
	 * @param value value to send
	 */
	void sendValue(T value);
}
