package simpl.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.BoolType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;

public class BooleanLiteral extends Expr {

    public final boolean b;

    public BooleanLiteral(boolean b) {
        this.b = b;
    }

    public String toString() {
        return String.valueOf(b);
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        return BoolType.T;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        return new BoolValue(b);
    }
}
