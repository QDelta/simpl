package simpl.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type t = e.typeCheck(E);
        return new RefType(t);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v = e.eval(s);
        int p = s.M.allocate(s.E, v);
        return new RefValue(p);
    }
}
