package simpl.interpreter;

import simpl.ast.Expr;

public class Thunk extends Value {

    public final Env E;
    public final Expr e;

    public Thunk(Env E, Expr e) {
        this.E = E;
        this.e = e;
    }

    public boolean equal(Value other) {
        return false;
    }

    public String show(State s) {
        return "thunk";
    }
}
