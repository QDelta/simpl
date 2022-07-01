package simpl.ast;

import simpl.interpreter.*;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeVar;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

public class Rec extends Expr {

    public final Symbol x;
    public final Map<Symbol, Expr> recs;

    public Rec(Bind f, Vector<Bind> helpers) {
        this.x = f.x;
        HashMap<Symbol, Expr> recMap = new HashMap<>();
        recMap.put(f.x, f.e);
        for (Bind b : helpers) {
            recMap.put(b.x, b.e);
        }
        recs = recMap;
    }

    public Rec(Symbol x, Map<Symbol, Expr> recs) {
        this.x = x;
        this.recs = recs;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("(rec " + x + " => " + recs.get(x));
        for (Entry<Symbol, Expr> b : recs.entrySet()) {
            if (b.getKey() != x) {
                s.append(" with ")
                        .append(b.getKey())
                        .append(" => ")
                        .append(b.getValue());
            }
        }
        s.append(")");
        return s.toString();
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        HashMap<Symbol, Type> vars = new HashMap<>();
        TypeEnv newE = E;
        for (Symbol x : recs.keySet()) {
            Type v = new TypeVar(false);
            vars.put(x, v);
            newE = newE.bind(x, v);
        }
        for (Entry<Symbol, Expr> b : recs.entrySet()) {
            Symbol x = b.getKey();
            vars.get(x).unify(b.getValue().typeCheck(newE));
        }
        return vars.get(x);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Expr e = recs.get(x);
        Env rEnv = s.E;
        for (Entry<Symbol, Expr> b : recs.entrySet()) {
            Symbol rName = b.getKey();
            RecClosure rVal = new RecClosure(s.E, rName, recs);
            rEnv = rEnv.bind(rName, rVal);
        }
        return e.eval(new State(rEnv, s.M));
    }

    public static class Bind {

        public final Symbol x;
        public final Expr e;

        public Bind(Symbol x, Expr e) {
            this.x = x;
            this.e = e;
        }

        public String toString() {
            return "(" + x + " => " + e + ")";
        }
    }
}
