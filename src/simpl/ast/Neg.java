package simpl.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.IntType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;

public class Neg extends UnaryExpr {

    public Neg(Expr e) {
        super(e);
    }

    public String toString() {
        return "~" + e;
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type t = e.typeCheck(E);
        t.unify(IntType.T);
        return IntType.T;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        IntValue v = (IntValue) e.eval(s);
        return new IntValue(-v.n);
    }
}
