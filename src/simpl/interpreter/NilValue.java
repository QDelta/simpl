package simpl.interpreter;

public final class NilValue extends Value {

    public static final NilValue VAL = new NilValue();

    private NilValue() {
    }

    public boolean equal(Value other) {
        return other instanceof NilValue;
    }

    public String show(State s) {
        return "nil";
    }
}
