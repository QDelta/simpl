package simpl.interpreter;

import java.util.HashMap;
import java.util.HashSet;

public class Mem {

    private final HashMap<Integer, Value> mem;
    private int pointerGen;
    private int gcThreshold;

    public Mem() {
        mem = new HashMap<>();
        pointerGen = 0;
        gcThreshold = 2;
    }

    private HashSet<Integer> collectEnvRef(Env e) {
        HashSet<Integer> ref = new HashSet<>();
        while (e != null) {
            Value v = e.v;
            if (v != null) {
                ref.addAll(collectValRef(v));
            }
            e = e.E;
        }
        return ref;
    }

    private HashSet<Integer> collectValRef(Value v) {
        HashSet<Integer> ref = new HashSet<>();
        if (v instanceof RefValue) {
            RefValue rv = (RefValue) v;
            ref.add(rv.p);
            ref.addAll(collectValRef(mem.get(rv.p)));
        } else if (v instanceof FnClosure) {
            ref.addAll(collectEnvRef(((FnClosure) v).E));
        } else if (v instanceof RecClosure) {
            ref.addAll(collectEnvRef(((RecClosure) v).E));
        } else if (v instanceof ConsValue) {
            ConsValue cons = (ConsValue) v;
            ref.addAll(collectValRef(cons.v1));
            ref.addAll(collectValRef(cons.v2));
        } else if (v instanceof PairValue) {
            PairValue p = (PairValue) v;
            ref.addAll(collectValRef(p.v1));
            ref.addAll(collectValRef(p.v2));
        } else if (v instanceof InLValue) {
            ref.addAll(collectValRef(((InLValue) v).v));
        } else if (v instanceof InRValue) {
            ref.addAll(collectValRef(((InRValue) v).v));
        }
        return ref;
    }

    private void gc(Env e) {
        HashSet<Integer> alive = collectEnvRef(e);
        HashSet<Integer> inMem = new HashSet<>(mem.keySet());
        inMem.removeAll(alive);
        for (Integer p : inMem) {
            mem.remove(p);
        }
    }

    public int allocate(Env e, Value v) {
        if (mem.size() >= gcThreshold) {
            gc(e);
            gcThreshold = 2 * mem.size();
        }
        int p = pointerGen;
        pointerGen += 1;
        mem.put(p, v);
        return p;
    }

    public Value get(int p) {
        if (mem.containsKey(p)) {
            return mem.get(p);
        } else {
            // indicates buggy gc if well-typed
            throw new RuntimeException("deref unmapped address");
        }
    }

    public void set(int p, Value v) {
        mem.replace(p, v);
    }
}
