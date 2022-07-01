package simpl.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Cond extends Expr {

    public final Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type r = new TypeVar(false);

        Type t1 = e1.typeCheck(E);
        Type t2 = e2.typeCheck(E);
        Type t3 = e3.typeCheck(E);

        t1.unify(BoolType.T);
        r.unify(t2);
        r.unify(t3);

        return r;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        BoolValue cond = (BoolValue) e1.eval(s);
        if (cond.b) {
            return e2.eval(s);
        } else {
            return e3.eval(s);
        }
    }
}
