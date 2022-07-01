package simpl.ast;

import simpl.interpreter.InLValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class InL extends UnaryExpr {

    public InL(Expr e) {
        super(e);
    }

    public String toString() {
        return "(inl " + e + ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type other = new TypeVar(false);
        Type t = e.typeCheck(E);
        return new SumType(t, other);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v = e.eval(s);
        return new InLValue(v);
    }
}
