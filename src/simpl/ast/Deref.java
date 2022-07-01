package simpl.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type v = new TypeVar(false);
        Type t = e.typeCheck(E);
        t.unify(new RefType(v));
        return v;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        RefValue ref = (RefValue) e.eval(s);
        return s.M.get(ref.p);
    }
}
