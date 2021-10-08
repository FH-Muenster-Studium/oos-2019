package de.ossysteme;

public class StrategyFactory implements Factory<Strategy, StrategyType> {
    @Override
    public Strategy create(StrategyType strategyType) {
        if (strategyType == StrategyType.ZICK_ZACK)
            return new ZickZackStrategy();
        return null;
    }
}
