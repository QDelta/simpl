package simpl.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.UnitValue;
import simpl.interpreter.Value;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.UnitType;

public class Unit extends Expr {

    public String toString() {
        return "()";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        return UnitType.T;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        return UnitValue.VAL;
    }
}
