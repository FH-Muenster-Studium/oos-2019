package oosysteme;

import lejos.nxt.LightSensor;

/**
 * LightSensorController that sends new light values to all light sensor observers.
 */
public class LightSensorController extends BaseObservable<Integer> implements Runnable {
	
	private final LightSensor light;
	
	/**
	 * light controller constructor.
	 * @param light light sensor
	 */
	public LightSensorController(LightSensor light) {
		super();
		this.light = light;
	}
	
	/**
	 * Sends new light value to all observers.
	 */
	public void run() {
		int value = light.getLightValue();
		sendValue(value);
	}
}
