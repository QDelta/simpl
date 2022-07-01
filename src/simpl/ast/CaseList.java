package simpl.ast;

import simpl.interpreter.*;
import simpl.typing.*;

public class CaseList extends Expr {

    public final Symbol h, t;
    public final Expr e1, e2, e3;

    public CaseList(Expr e1, Expr e2, Symbol h, Symbol t, Expr e3) {
        this.h = h;
        this.t = t;
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(case " + e1 +
                " of nil => " + e2 +
                " | " + h + " :: " + t + " => " + e3 +
                ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type v = new TypeVar(false);
        Type r = new TypeVar(false);
        Type l = new ListType(v);

        TypeEnv consE = E.bind(h, v).bind(t, l);

        Type t1 = e1.typeCheck(E);
        Type t2 = e2.typeCheck(E);
        Type t3 = e3.typeCheck(consE);

        t1.unify(l);
        r.unify(t2);
        r.unify(t3);

        return r;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v = e1.eval(s);
        if (v instanceof NilValue) {
            return e2.eval(s);
        } else {
            ConsValue consV = (ConsValue) v;
            Env newE = s.E.bind(h, consV.v1).bind(t, consV.v2);
            return e3.eval(new State(newE, s.M));
        }
    }
}
