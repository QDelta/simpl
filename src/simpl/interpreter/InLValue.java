package simpl.interpreter;

public class InLValue extends Value {

    public final Value v;

    public InLValue(Value v) {
        this.v = v;
    }

    public boolean equal(Value other) {
        if (other instanceof InLValue) {
            return v.equals(((InLValue) other).v);
        } else {
            return false;
        }
    }

    public String show(State s) {
        return "inl@" + v.show(s);
    }
}
