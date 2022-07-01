package simpl.ast;

import simpl.interpreter.*;
import simpl.typing.*;

public class Loop extends Expr {

    public final Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type t1 = e1.typeCheck(E);
        e2.typeCheck(E);
        t1.unify(BoolType.T);
        return UnitType.T;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        BoolValue cond = (BoolValue) e1.eval(s);
        if (cond.b) {
            e2.eval(s);
            return this.eval(s);
        } else {
            return UnitValue.VAL;
        }
    }
}
