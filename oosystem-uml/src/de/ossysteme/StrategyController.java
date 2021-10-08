package de.ossysteme;

import java.util.List;

public class StrategyController implements Observable<Strategy>, Observer<StrategyType>, Runnable {

    private Factory<Strategy, StrategyType> factory;

    private List<Observer<Strategy>> observers;

    private Strategy active;

    @Override
    public void addObserver(Observer<Strategy> observer) {

    }

    @Override
    public void removeObserver(Observer<Strategy> observer) {

    }

    @Override
    public void sendValue(Strategy value) {

    }

    @Override
    public void onReceive(StrategyType value) {
        if (active != null) {
            active.onInactive();
        }
        active = factory.create(value);
        active.onActive();
        sendValue(active);
    }

    @Override
    public void run() {
        if (active != null) active.run();
    }
}
