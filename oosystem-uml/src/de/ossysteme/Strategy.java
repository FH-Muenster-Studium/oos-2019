package de.ossysteme;

public interface Strategy {
    void run();

    void onActive();

    void onInactive();
}
