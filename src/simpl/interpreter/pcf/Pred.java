package simpl.interpreter.pcf;

import simpl.interpreter.IntValue;
import simpl.interpreter.LibFunValue;
import simpl.interpreter.Value;

public class Pred extends LibFunValue {

    public static final Pred F = new Pred();

    private Pred() {
    }

    public Value apply(Value v) {
        return new IntValue(((IntValue) v).n - 1);
    }
}
