package de.ossysteme;

public class DistanceController extends BaseObservable<Integer> implements Runnable {

    @Override
    public void run() {
        sendValue(1/*distance sensor*/);
    }
}
