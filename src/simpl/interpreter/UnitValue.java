package simpl.interpreter;

public final class UnitValue extends Value {

    public static final UnitValue VAL = new UnitValue();

    private UnitValue() {
    }

    public boolean equal(Value other) {
        return other instanceof UnitValue;
    }

    public String show(State s) {
        return "unit";
    }
}
