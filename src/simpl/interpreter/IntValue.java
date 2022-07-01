package simpl.interpreter;

public class IntValue extends Value {

    public final int n;

    public IntValue(int n) {
        this.n = n;
    }

    public String show(State s) {
        return String.valueOf(n);
    }

    public boolean equal(Value other) {
        if (other instanceof IntValue) {
            return n == ((IntValue) other).n;
        } else {
            return false;
        }
    }
}
