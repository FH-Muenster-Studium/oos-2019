package de.ossysteme;

public class Robot implements Runnable {

    private StrategyController strategyController;

    private BluetoothReceiver bluetoothReceiver;

    private DistanceController distanceController;

    public Robot() {
        //bluetoothReceiver.startThread();
    }

    @Override
    public void run() {
        strategyController.run();
        distanceController.run();
    }
}
