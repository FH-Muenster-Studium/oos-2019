package de.ossysteme;

public interface Observer<T> {
    void onReceive(T value);
}
