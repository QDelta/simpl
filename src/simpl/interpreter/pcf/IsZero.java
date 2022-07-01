package simpl.interpreter.pcf;

import simpl.interpreter.BoolValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.LibFunValue;
import simpl.interpreter.Value;

public class IsZero extends LibFunValue {

    public static final IsZero F = new IsZero();

    private IsZero() {
    }

    public Value apply(Value v) {
        return new BoolValue(((IntValue) v).n == 0);
    }
}
