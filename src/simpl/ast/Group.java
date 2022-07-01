package simpl.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;

public class Group extends UnaryExpr {

    public Group(Expr e) {
        super(e);
    }

    public String toString() {
        return "(" + e + ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        return e.typeCheck(E);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        return e.eval(s);
    }
}
