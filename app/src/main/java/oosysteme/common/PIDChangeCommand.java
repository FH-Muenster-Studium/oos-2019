package oosysteme.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PIDChangeCommand implements Command {

    private float p;

    private float i;

    private float d;

    public PIDChangeCommand() {
    }

    public void setP(float p) {
        this.p = p;
    }

    public void setI(float i) {
        this.i = i;
    }

    public void setD(float d) {
        this.d = d;
    }

    public float getP() {
        return p;
    }

    public float getI() {
        return i;
    }

    public float getD() {
        return d;
    }

    @Override
    public CommandType getType() {
        return CommandType.PID;
    }

    @Override
    public void execute() {
    }

    @Override
    public void read(DataInputStream stream) throws IOException {
    }

    @Override
    public void write(DataOutputStream stream) throws IOException {
        stream.writeFloat(p);
        stream.writeFloat(i);
        stream.writeFloat(d);
    }
}
