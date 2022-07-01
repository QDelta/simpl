package simpl.interpreter;

import simpl.ast.Expr;
import simpl.ast.Symbol;

public class FnClosure extends FunValue {

    public final Env E;
    public final Symbol x;
    public final Expr e;
    public final boolean lazy;

    public FnClosure(Env E, Symbol x, Expr e, boolean lazy) {
        this.E = E;
        this.x = x;
        this.e = e;
        this.lazy = lazy;
    }
}
