package oosysteme;

import lejos.nxt.UltrasonicSensor;

/**
 * DistanceController to send distance changes the distance observers.
 */
public class DistanceController extends BaseObservable<Integer> implements Runnable {
	
	private final UltrasonicSensor sound;
	
	/**
	 * distance controller constructor.
	 * @param sound sound
	 */
	public DistanceController(UltrasonicSensor sound) {
		this.sound = sound;
		
		if (this.sound.getMode() != UltrasonicSensor.MODE_CONTINUOUS) {
			this.sound.continuous();
		}
	}
	
	/**
	 * Collects the ultrasonic sensor value and send it to all observers.
	 */
	public void run() {
		int distance = sound.getDistance();
		sendValue(distance);
	}
}
