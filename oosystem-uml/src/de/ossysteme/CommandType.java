package de.ossysteme;

public enum CommandType {
    STRATEGY_CHANGE_COMMAND((byte) 0);

    private final byte value;

    CommandType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    private final static CommandType[] commandTypes;

    static {
        CommandType[] values = CommandType.values();
        commandTypes = new CommandType[values.length];
        for (CommandType commandType : values) {
            commandTypes[commandType.value] = commandType;
        }
    }

    public static CommandType fromByte(byte value) {
        return commandTypes[value];
    }
}
