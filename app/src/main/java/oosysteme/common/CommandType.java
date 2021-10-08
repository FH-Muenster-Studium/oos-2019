package oosysteme.common;

public enum CommandType {
    MANUAL_CONTROL((byte) 0), PID((byte) 1), STRATEGY_CHANGE((byte) 2),
    ZICKZACK_SPEED_OFFSET((byte) 3), ZICKZACK_OFFSET((byte) 4);

    private final byte index;

    private CommandType(byte index) {
        this.index = index;
    }

    public byte getIndex() {
        return this.index;
    }
}