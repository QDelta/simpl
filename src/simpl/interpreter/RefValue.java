package simpl.interpreter;

public class RefValue extends Value {

    public final int p;

    public RefValue(int p) {
        this.p = p;
    }

    public boolean equal(Value other) {
        if (other instanceof RefValue) {
            return p == ((RefValue) other).p;
        } else {
            return false;
        }
    }

    public String show(State s) {
        return "ref@" + s.M.get(p).show(s);
    }
}
