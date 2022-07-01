package simpl.interpreter;

public class BoolValue extends Value {

    public final boolean b;

    public BoolValue(boolean b) {
        this.b = b;
    }

    public boolean equal(Value other) {
        if (other instanceof BoolValue) {
            return b == ((BoolValue) other).b;
        } else {
            return false;
        }
    }

    public String show(State s) {
        return String.valueOf(b);
    }
}
