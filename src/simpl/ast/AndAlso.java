package simpl.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.BoolType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type lt = l.typeCheck(E);
        Type rt = r.typeCheck(E);
        lt.unify(BoolType.T);
        rt.unify(BoolType.T);
        return BoolType.T;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // short circuit
        BoolValue lv = (BoolValue) l.eval(s);
        if (lv.b) {
            return r.eval(s);
        } else {
            return new BoolValue(false);
        }
    }
}
