package simpl.interpreter;

public abstract class Value {

    public abstract boolean equal(Value other);

    public abstract String show(State s);
}
