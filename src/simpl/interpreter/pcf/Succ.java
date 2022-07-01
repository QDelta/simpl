package simpl.interpreter.pcf;

import simpl.interpreter.IntValue;
import simpl.interpreter.LibFunValue;
import simpl.interpreter.Value;

public class Succ extends LibFunValue {

    public static final Succ F = new Succ();

    private Succ() {
    }

    public Value apply(Value v) {
        return new IntValue(((IntValue) v).n + 1);
    }
}
