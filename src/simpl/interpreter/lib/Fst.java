package simpl.interpreter.lib;

import simpl.interpreter.LibFunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.Value;

public class Fst extends LibFunValue {

    public static final Fst F = new Fst();

    private Fst() {
    }

    public Value apply(Value v) {
        return ((PairValue) v).v1;
    }
}
