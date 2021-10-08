package oosysteme;

/**
 * light sensor observer that changes strategy to findline when line is missing for long time.
 */
public class MissingLineObserver implements Observer<Integer> {

	private final StrategyController controller;

	private int ticksSinceBlack = -1;
	
	/**
	 * Creates MissingLineObserver with current strategy controller.
	 * @param controller strategy controller
	 */
	public MissingLineObserver(StrategyController controller) {
		this.controller = controller;
	}

	/**
	 * Checks light sensor value and set strategy to find line when missing line for 50 ticks.
	 */
	@Override
	public void onReceive(Integer value) {
		if (controller.getActiveStrategy() == StrategyType.FIND_LINE) return;
		if (controller.getActiveStrategy() == StrategyType.MANUAL_CONTROL) return;
		if (controller.getActiveStrategy() == StrategyType.PID_HALT) return;
		if (controller.getActiveStrategy() == StrategyType.ZICKZACK_HALT) return;
		if (controller.getActiveStrategy() == StrategyType.OVERTAKE_LEFT) return;
		if (controller.getActiveStrategy() == StrategyType.OVERTAKE_RIGHT) return;
		if (controller.getActiveStrategy() == StrategyType.OVERTAKE_RIGHT_CIRCLE) return;
		int delta = value - 50;
		if (delta < 30) {
			ticksSinceBlack = 0;
		} else {
			ticksSinceBlack++;
		}
		if (ticksSinceBlack > 70) {
			ticksSinceBlack = 0;
			controller.setStrategy(StrategyType.FIND_LINE);
		}
	}
}
