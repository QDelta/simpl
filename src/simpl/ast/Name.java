package simpl.ast;

import simpl.interpreter.*;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.UndefinedName;

import java.util.HashMap;

public class Name extends Expr {

    public final Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return x.toString();
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type t = E.get(x);
        if (t == null) {
            throw new UndefinedName(x);
        } else {
            return t.instantiate(new HashMap<>());
        }
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v = s.E.get(x);
        if (v instanceof RecClosure) {
            RecClosure rc = (RecClosure) v;
            return new Rec(rc.x, rc.recs).eval(new State(rc.E, s.M));
        } else if (v instanceof Thunk) {
            Thunk t = (Thunk) v;
            Value val = t.e.eval(new State(t.E, s.M));
            s.E.update(x, val);
            return val;
        } else {
            return v;
        }
    }
}
