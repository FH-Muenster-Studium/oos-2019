package oosysteme;

import lejos.nxt.LCD;

/**
 * StrategyController that controls and manages the strategies.
 */
public class StrategyController extends BaseObservable<StrategyType> implements 
Repository<StrategyType, Strategy>, Runnable, Observer<StrategyType> {
	
	private Strategy activeStrategy;

	private StrategyType activeStrategyType;

	private StrategyType previousStrategyType;

	private final Strategy[] strategies;

	/**
	 * Strategy constructor.
	 * @param factory strategy factory
	 */
	public StrategyController(Factory<StrategyType, Strategy> factory) {
		super();
		StrategyType[] types = StrategyType.values();
		int length = types.length;
		this.strategies = new Strategy[length];
		for (int i = 0;i < length;i++) {
			this.strategies[i] = factory.create(types[i]);
			this.strategies[i].setStrategyController(this);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Strategy> T get(StrategyType type) {
		if (type.getIndex() < 0 || type.getIndex() >= this.strategies.length) return null;
		return (T)this.strategies[type.getIndex()];
	}

	/**
	 * Sets the next strategy.
	 * @param nextStrategy next strategy
	 */
	public synchronized void setStrategy(StrategyType nextStrategy) {
		if (activeStrategyType != null 
				&& activeStrategyType.getIndex() == nextStrategy.getIndex()) {
			return;
		}
		previousStrategyType = activeStrategyType;
		if (activeStrategy != null) {
			activeStrategy.onStop();
		}
		activeStrategy = strategies[nextStrategy.getIndex()];
		activeStrategy.onStart();
		activeStrategyType = nextStrategy;

		if (previousStrategyType != null) {
			LCD.drawInt(previousStrategyType.getIndex(), 0, 3);
		}
		LCD.drawString("->", 2, 3);
		LCD.drawInt(activeStrategyType.getIndex(), 6, 3);
		sendValue(activeStrategyType);
	}
	
	/**
	 * Listens for strategy types to change active strategy.
	 * @param nextStrategy next strategy to use
	 */
	@Override
	public void onReceive(StrategyType nextStrategy) {
		setStrategy(nextStrategy);
	}

	/**
	 * Returns active strategy.
	 * @return current active strategy type
	 */
	public StrategyType getActiveStrategy() {
		return this.activeStrategyType;
	}

	/**
	 * Returns previews active strategy.
	 * @return previews active strategy type
	 */
	public StrategyType getPreviousStrategy() {
		return this.previousStrategyType;
	}

	/**
	 * Executes current active strategy run method.
	 */
	@Override
	public void run() {
		if (activeStrategy == null) return;
		activeStrategy.onRun();
	}
}
