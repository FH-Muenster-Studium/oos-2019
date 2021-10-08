package de.ossysteme;

public interface Observable<T> {
    void addObserver(Observer<T> observer);

    void removeObserver(Observer<T> observer);

    void sendValue(T value);
}
