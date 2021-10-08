package oosysteme;

import java.util.ArrayList;

/**
 * BaseObservable implementation based on a array list.
 *
 * @param <ValueT> observable value type
 */
public class BaseObservable<ValueT> implements Observable<ValueT> {
	
	private final ArrayList<Observer<ValueT>> listener;
	
	/**
	 * constructor that initializes the array list.
	 */
	public BaseObservable() {
		this.listener = new ArrayList<Observer<ValueT>>();
	}
	
	/**
	 * Add observer.
	 * @param observer observer that observes for value changes
	 */
	public void addObserver(Observer<ValueT> observer) {
		this.listener.add(observer);
	}
	
	/**
	 * Remove observer.
	 * @param observer observer to remove.
	 */
	public void removeObserver(Observer<ValueT> observer) {
		this.listener.remove(observer);
	}
	
	/**
	 * sends distance value to observers.
	 * @param value value to send to all observers
	 */
	public void sendValue(ValueT value) {
		for (int i = 0,length = this.listener.size();i < length;i++) {
			this.listener.get(i).onReceive(value);
		}
	}
}
