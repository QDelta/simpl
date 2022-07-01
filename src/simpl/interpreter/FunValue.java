package simpl.interpreter;

public abstract class FunValue extends Value {

    public boolean equal(Value other) {
        return false;
    }

    public String show(State s) {
        return "fun";
    }
}
