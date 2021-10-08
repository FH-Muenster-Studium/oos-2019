package oosysteme.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import oosysteme.android.StrategyType;

public class StrategyChangeCommand implements Command {

    private final StrategyType[] types;

    private StrategyType type;

    public StrategyChangeCommand() {
        this.types = StrategyType.values();
    }

    public StrategyChangeCommand(StrategyType type) {
        this();
        this.type = type;
    }

    @Override
    public CommandType getType() {
        return CommandType.STRATEGY_CHANGE;
    }

    public void setType(StrategyType type) {
        this.type = type;
    }

    @Override
    public void read(DataInputStream stream) throws IOException {
        type = types[stream.readByte()];
    }

    @Override
    public void write(DataOutputStream stream) throws IOException {
        stream.write((byte) type.getIndex());
    }

    @Override
    public void execute() {
    }
}