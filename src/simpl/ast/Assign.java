package simpl.ast;

import simpl.interpreter.*;
import simpl.typing.*;

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type v = new TypeVar(false);
        Type lt = l.typeCheck(E);
        Type rt = r.typeCheck(E);
        lt.unify(new RefType(v));
        v.unify(rt);
        return UnitType.T;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        RefValue ref = (RefValue) l.eval(s);
        Value v = r.eval(s);
        s.M.set(ref.p, v);
        return UnitValue.VAL;
    }
}
