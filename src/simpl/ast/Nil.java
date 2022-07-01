package simpl.ast;

import simpl.interpreter.NilValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Nil extends Expr {

    public String toString() {
        return "nil";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type v = new TypeVar(false);
        return new ListType(v);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        return NilValue.VAL;
    }
}
