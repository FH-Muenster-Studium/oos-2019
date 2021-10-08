package de.ossysteme;

public class CommandFactory implements Factory<Command, CommandType> {
    @Override
    public Command create(CommandType commandType) {
        if (commandType == CommandType.STRATEGY_CHANGE_COMMAND)
            return new StrategyChangeCommand();
        return null;
    }
}
