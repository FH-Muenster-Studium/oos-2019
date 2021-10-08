package oosysteme;

public class Main {
	
	/**
	 * Main that creates the robot and executes robot run in endless loop.
	 * @param args arguments
	 * @throws Exception exception
	 */
	public static void main(String[] args) throws Exception {
		Robot robot = new Robot();
		while (true) {
			robot.run();
		}
	}
}
