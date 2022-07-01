package simpl.interpreter;

import simpl.ast.Expr;
import simpl.ast.Symbol;

import java.util.Map;

public class RecClosure extends Value {

    public final Env E;
    public final Symbol x;
    public final Map<Symbol, Expr> recs;

    public RecClosure(Env E, Symbol x, Map<Symbol, Expr> recs) {
        this.E = E;
        this.x = x;
        this.recs = recs;
    }

    public boolean equal(Value other) {
        return false;
    }

    public String show(State s) {
        return "rec";
    }
}
