package simpl.interpreter;

public class PairValue extends Value {

    public final Value v1, v2;

    public PairValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public boolean equal(Value other) {
        if (other instanceof PairValue) {
            PairValue o = (PairValue) other;
            return v1.equals(o.v1) && v2.equals(o.v2);
        } else {
            return false;
        }
    }

    public String show(State s) {
        return "pair@" + v1.show(s) + "@" + v2.show(s);
    }
}
