package simpl.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;

public abstract class Expr {

    public abstract Type typeCheck(TypeEnv E) throws TypeError;

    public abstract Value eval(State s) throws RuntimeError;
}
