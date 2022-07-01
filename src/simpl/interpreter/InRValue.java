package simpl.interpreter;

public class InRValue extends Value {

    public final Value v;

    public InRValue(Value v) {
        this.v = v;
    }

    public boolean equal(Value other) {
        if (other instanceof InRValue) {
            return v.equals(((InRValue) other).v);
        } else {
            return false;
        }
    }

    public String show(State s) {
        return "inr@" + v.show(s);
    }
}
