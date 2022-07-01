package simpl.interpreter;

import simpl.ast.Symbol;
import simpl.interpreter.lib.Fst;
import simpl.interpreter.lib.Hd;
import simpl.interpreter.lib.Snd;
import simpl.interpreter.lib.Tl;
import simpl.interpreter.pcf.IsZero;
import simpl.interpreter.pcf.Pred;
import simpl.interpreter.pcf.Succ;

public class State {

    public final Env E;
    public final Mem M;

    public State(Env E, Mem M) {
        this.E = E;
        this.M = M;
    }

    private static Env initialEnv() {
        return Env.empty
                .bind(Symbol.symbol("fst"), Fst.F)
                .bind(Symbol.symbol("snd"), Snd.F)
                .bind(Symbol.symbol("hd"), Hd.F)
                .bind(Symbol.symbol("tl"), Tl.F)
                .bind(Symbol.symbol("pred"), Pred.F)
                .bind(Symbol.symbol("succ"), Succ.F)
                .bind(Symbol.symbol("iszero"), IsZero.F);
    }

    public static State initialState() {
        return new State(initialEnv(), new Mem());
    }
}
