package simpl.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.IntType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;

public class IntegerLiteral extends Expr {

    public final int n;

    public IntegerLiteral(int n) {
        this.n = n;
    }

    public String toString() {
        return String.valueOf(n);
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        return IntType.T;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        return new IntValue(n);
    }
}
