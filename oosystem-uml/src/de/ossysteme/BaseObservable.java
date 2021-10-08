package de.ossysteme;

import java.util.ArrayList;
import java.util.List;

public class BaseObservable<T> implements Observable<T> {

    private final List<Observer<T>> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer<T> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<T> observer) {
        observers.remove(observer);
    }

    @Override
    public void sendValue(T value) {
        for (Observer<T> observer : observers) {
            observer.onReceive(value);
        }
    }
}
