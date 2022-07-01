package simpl.interpreter;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String show(State s) {
        int len = 1;
        Value v = v2;
        while (v instanceof ConsValue) {
            v = ((ConsValue) v).v2;
            len += 1;
        }
        return "list@" + len;
    }

    public boolean equal(Value other) {
        if (other instanceof ConsValue) {
            ConsValue o = (ConsValue) other;
            return v1.equals(o.v1) && v2.equals(o.v2);
        } else {
            return false;
        }
    }
}
