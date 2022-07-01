package simpl.interpreter.lib;

import simpl.interpreter.ConsValue;
import simpl.interpreter.LibFunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.Value;

public class Tl extends LibFunValue {

    public static final Tl F = new Tl();

    private Tl() {
    }

    public Value apply(Value v) throws RuntimeError {
        if (v instanceof ConsValue) {
            return ((ConsValue) v).v2;
        } else {
            throw new RuntimeError("tl: nil");
        }
    }
}

