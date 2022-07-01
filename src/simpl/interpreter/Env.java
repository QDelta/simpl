package simpl.interpreter;

import simpl.ast.Symbol;

public class Env {

    public static final Env empty = new Env() {
        public Value get(Symbol y) {
            // should not happen in well-typed program
            throw new RuntimeException("search in empty env");
        }
    };
    public final Env E;
    public final Symbol x;
    public Value v;

    private Env() {
        E = null;
        x = null;
        v = null;
    }

    private Env(Env E, Symbol x, Value v) {
        this.E = E;
        this.x = x;
        this.v = v;
    }

    public Env bind(Symbol x, Value v) {
        return new Env(this, x, v);
    }

    public Value get(Symbol s) {
        if (s == x) {
            return v;
        } else {
            assert E != null;
            return E.get(s);
        }
    }

    public void update(Symbol s, Value v) {
        if (s == x) {
            this.v = v;
        } else {
            assert E != null;
            E.update(s, v);
        }
    }
}
