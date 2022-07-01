package simpl.ast;

import simpl.interpreter.InRValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class InR extends UnaryExpr {

    public InR(Expr e) {
        super(e);
    }

    public String toString() {
        return "(inr " + e + ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type other = new TypeVar(false);
        Type t = e.typeCheck(E);
        return new SumType(other, t);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v = e.eval(s);
        return new InRValue(v);
    }
}
