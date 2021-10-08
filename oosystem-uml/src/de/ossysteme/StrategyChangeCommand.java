package de.ossysteme;

import java.io.InputStream;
import java.io.OutputStream;

public class StrategyChangeCommand implements Command {

    private Observer<StrategyType> strategyTypeObserver;

    private StrategyType strategyType;

    @Override
    public void execute() {
        strategyTypeObserver.onReceive(strategyType);
    }

    @Override
    public void read(InputStream inputStream) {
    }

    @Override
    public void write(OutputStream outputStream) {

    }

    @Override
    public CommandType getType() {
        return CommandType.STRATEGY_CHANGE_COMMAND;
    }
}
