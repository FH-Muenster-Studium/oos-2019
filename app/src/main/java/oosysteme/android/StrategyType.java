package oosysteme.android;

public enum StrategyType {
    CALIBRATE((byte) 0), ZICKZACK((byte) 1), ZICKZACK_HALT((byte) 2),
    MANUAL_CONTROL((byte) 3), PID((byte) 4), PID_HALT((byte) 5), FIND_LINE((byte) 6),
    OVERTAKE_LEFT((byte) 7), OVERTAKE_RIGHT((byte) 8), OVERTAKE_CIRCLE_RIGHT((byte) 9), ZICKZACK2((byte) 10);

    private final byte index;

    StrategyType(byte index) {
        this.index = index;
    }

    public byte getIndex() {
        return index;
    }
}
