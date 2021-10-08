package oosysteme;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

/**
 * Robot implementation with the controllers, sensors and motors.
 */
public class Robot implements Runnable {

	private final NXTRegulatedMotor leftMotor;

	private final NXTRegulatedMotor rightMotor;

	private final UltrasonicSensor ultrasonicSensor;

	private final LightSensor lightSensor;

	//private final DistanceController distanceController;

	private final LightSensorController lightSensorController;

	private final StrategyController strategyController;

	private final BluetoothController bluetoothController;

	/**
	 * Initializes the robot.
	 */
	public Robot() {
		this.leftMotor = Motor.A;
		this.rightMotor = Motor.B;
		this.ultrasonicSensor = new UltrasonicSensor(SensorPort.S2);
		this.lightSensor = new LightSensor(SensorPort.S1);
		//this.distanceController = new DistanceController(this.ultrasonicSensor);
		this.lightSensorController = new LightSensorController(this.lightSensor);

		Factory<StrategyType, Strategy> strategyFactory = new StrategyFactory(
				this.lightSensor, this.leftMotor, this.rightMotor
				);
		this.strategyController = new StrategyController(strategyFactory);

		Factory<CommandType, Command> commandFactory = 
				new CommandFactory(this.lightSensor, this.leftMotor, 
						this.rightMotor, this.strategyController);
		this.bluetoothController = new BluetoothController(commandFactory);

		this.strategyController.addObserver(
				new BluetoothStrategyTransferObserver<BluetoothController>(
						this.bluetoothController
						)
				);

		/*this.distanceController.addObserver(
				new DistanceObserver(this.strategyController)
				);*/

		this.lightSensorController.addObserver(
				new MissingLineObserver(this.strategyController)
				);

		// Set default strategy
		this.strategyController.setStrategy(StrategyType.CALIBRATE);
	}

	/**
	 * Executes the run method for all runnable controllers inside the robot.
	 */
	@Override
	public void run() {
		this.strategyController.run();
		//this.distanceController.run();
		this.lightSensorController.run();
	}
}
