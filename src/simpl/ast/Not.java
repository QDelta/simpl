package simpl.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.BoolType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;

public class Not extends UnaryExpr {

    public Not(Expr e) {
        super(e);
    }

    public String toString() {
        return "(not " + e + ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type t = e.typeCheck(E);
        t.unify(BoolType.T);
        return BoolType.T;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        BoolValue v = (BoolValue) e.eval(s);
        return new BoolValue(!v.b);
    }
}
