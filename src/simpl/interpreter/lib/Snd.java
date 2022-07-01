package simpl.interpreter.lib;

import simpl.interpreter.LibFunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.Value;

public class Snd extends LibFunValue {

    public static final Snd F = new Snd();

    private Snd() {
    }

    public Value apply(Value v) {
        return ((PairValue) v).v2;
    }
}
