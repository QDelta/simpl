package simpl.interpreter.lib;

import simpl.interpreter.ConsValue;
import simpl.interpreter.LibFunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.Value;

public class Hd extends LibFunValue {

    public static final Hd F = new Hd();

    private Hd() {
    }

    public Value apply(Value v) throws RuntimeError {
        if (v instanceof ConsValue) {
            return ((ConsValue) v).v1;
        } else {
            throw new RuntimeError("hd: nil");
        }
    }
}
