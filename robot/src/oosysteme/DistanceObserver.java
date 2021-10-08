package oosysteme;

/**
 * DistanceObserver to observe for distance values and stop when near a object.
 */
public class DistanceObserver extends BaseStrategyObserver<Integer> {
	
	/**
	 * Creates the distance observer with the strategy controller
	 * to set strategy to halt on near object.
	 * @param strategyController strategy controller to use
	 */
	public DistanceObserver(StrategyController strategyController) {
		super(strategyController);
	}
	
	/**
	 * Regulates distance between object and robot.
	 */
	@Override
	public void onReceive(Integer value) {
		if (getActiveStrategy() == StrategyType.ZICKZACK && value < 35) {
			setStrategy(StrategyType.ZICKZACK_HALT);
		}
		if (getActiveStrategy() == StrategyType.PID && value < 35) {
			setStrategy(StrategyType.PID_HALT);
		}
		if (getActiveStrategy() == StrategyType.ZICKZACK_HALT && value >= 35) {
			setStrategy(StrategyType.ZICKZACK);
		}
		if (getActiveStrategy() == StrategyType.PID_HALT && value >= 35) {
			setStrategy(StrategyType.PID);
		}
	}
}
