package simpl.ast;

import simpl.interpreter.*;
import simpl.typing.*;

public class CaseSum extends Expr {

    public final Symbol x2, x3;
    public final Expr e1, e2, e3;

    public CaseSum(Expr e1, Symbol x2, Expr e2, Symbol x3, Expr e3) {
        this.x2 = x2;
        this.x3 = x3;
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(case " + e1 +
                " of inl " + x2 + " => " + e2 +
                " | inr " + x3 + " => " + e3 +
                ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type v1 = new TypeVar(false);
        Type v2 = new TypeVar(false);
        Type r = new TypeVar(false);

        TypeEnv le = E.bind(x2, v1);
        TypeEnv re = E.bind(x3, v2);

        Type t1 = e1.typeCheck(E);
        Type t2 = e2.typeCheck(le);
        Type t3 = e3.typeCheck(re);

        t1.unify(new SumType(v1, v2));
        r.unify(t2);
        r.unify(t3);

        return r;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v = e1.eval(s);
        Symbol x;
        Expr e;
        if (v instanceof InLValue) {
            v = ((InLValue) v).v;
            x = x2;
            e = e2;
        } else {
            v = ((InRValue) v).v;
            x = x3;
            e = e3;
        }
        Env newE = s.E.bind(x, v);
        return e.eval(new State(newE, s.M));
    }
}
