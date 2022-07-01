package simpl.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeVar;

public class Let extends Expr {

    public final Symbol x;
    public final Expr e1, e2;

    public Let(Symbol x, Expr e1, Expr e2) {
        this.x = x;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(let " + x + " = " + e1 + " in " + e2 + ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        TypeVar.enterLevel();
        Type t1 = e1.typeCheck(E);
        TypeVar.exitLevel();
        TypeEnv newE = E.bind(x, t1.generalize());
        return e2.typeCheck(newE);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value bindV = e1.eval(s);
        Env newE = s.E.bind(x, bindV);
        return e2.eval(new State(newE, s.M));
    }
}
