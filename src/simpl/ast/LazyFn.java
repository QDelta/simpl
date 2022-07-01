package simpl.ast;

import simpl.interpreter.FnClosure;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class LazyFn extends Expr {

    public final Symbol x;
    public final Expr e;

    public LazyFn(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(lazyfn " + x + "." + e + ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type v = new TypeVar(false);
        TypeEnv newE = E.bind(x, v);
        Type t = e.typeCheck(newE);
        return new ArrowType(v, t);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        return new FnClosure(s.E, x, e, true);
    }
}
