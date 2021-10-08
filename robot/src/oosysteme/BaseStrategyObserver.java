package oosysteme;

/**
 * The BaseStrategyObserver each observer that needs the strategy controller should implement.
 *
 * @param <ValueT> observable value type
 */
public abstract class BaseStrategyObserver<ValueT> implements Observer<ValueT> {
	private final StrategyController strategyController;
	
	/**
	 * Initializes the BaseStrategyObserver with the strategy controller.
	 * @param strategyController strategy controller observer belongs to
	 */
	public BaseStrategyObserver(StrategyController strategyController) {
		this.strategyController = strategyController;
	}
	
	/**
	 * Set the next strategy for the controller.
	 * @param nextStrategy next strategy
	 */
	public void setStrategy(StrategyType nextStrategy) {
		this.strategyController.setStrategy(nextStrategy);
	}
	
	/**
	 * Returns the current active strategy.
	 * @return current active strategy
	 */
	public StrategyType getActiveStrategy() {
		return this.strategyController.getActiveStrategy();
	}
	
	/**
	 * Returns the previews active strategy
	 * @return previews active strategy
	 */
	public StrategyType getPreviousStrategy() {
		return this.strategyController.getPreviousStrategy();
	}
}
