package simpl.ast;

import simpl.interpreter.*;
import simpl.typing.*;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type ret = new TypeVar(false);
        Type lt = l.typeCheck(E);
        Type rt = r.typeCheck(E);
        lt.unify(new ArrowType(rt, ret));
        return ret;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        FunValue fun = (FunValue) l.eval(s);
        Value arg;

        if (fun instanceof LibFunValue) {
            arg = r.eval(s);
            return ((LibFunValue) fun).apply(arg);
        } else {
            FnClosure c = (FnClosure) fun;
            if (c.lazy) {
                arg = new Thunk(s.E, r);
            } else {
                arg = r.eval(s);
            }
            Env newE = c.E.bind(c.x, arg);
            return c.e.eval(new State(newE, s.M));
        }
    }
}
