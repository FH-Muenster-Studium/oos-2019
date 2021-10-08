package oosysteme;

/**
 * Strategy interface.
 */
public interface Strategy {
	
	/**
	 * runs the strategy.
	 */
	void onRun();
	
	/**
	 * strategy started.
	 */
	void onStart();
	
	/**
	 * strategy stopped.
	 */
	void onStop();
	
	/**
	 * sets strategy controller.
	 * @param strategyController controller
	 */
	void setStrategyController(StrategyController strategyController);
}
