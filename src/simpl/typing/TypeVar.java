package simpl.typing;

import java.util.Map;

public class TypeVar extends Type {

    private static int tvcnt = 0;
    private static int currentLevel = 1;
    private final VarRef v;

    public TypeVar(boolean equality) {
        v = new VarRef(new FreeVar(++tvcnt, currentLevel, equality));
    }

    public static void enterLevel() {
        currentLevel += 1;
    }

    public static void exitLevel() {
        currentLevel -= 1;
    }

    @Override
    public boolean occurs(int varId, int varLevel) {
        if (v.content instanceof BoundVar) {
            BoundVar bv = (BoundVar) v.content;
            return bv.t.occurs(varId, varLevel);
        } else {
            FreeVar fv = (FreeVar) v.content;
            int level = Math.min(varLevel, fv.level);
            int id = fv.ident;
            v.content = new FreeVar(id, level, fv.equality);
            return varId == id;
        }
    }

    @Override
    public Type generalize() {
        if (v.content instanceof BoundVar) {
            BoundVar bv = (BoundVar) v.content;
            return bv.t.generalize();
        } else {
            FreeVar fv = (FreeVar) v.content;
            if (fv.level > currentLevel) {
                return new QuantVar(fv.ident, fv.equality);
            } else {
                return this;
            }
        }
    }

    @Override
    public Type instantiate(Map<Integer, Type> instMap) {
        if (v.content instanceof BoundVar) {
            BoundVar bv = (BoundVar) v.content;
            return bv.t.instantiate(instMap);
        } else {
            return this;
        }
    }

    @Override
    public boolean tryMakeEquality() {
        if (v.content instanceof BoundVar) {
            BoundVar bv = (BoundVar) v.content;
            return bv.t.tryMakeEquality();
        } else {
            FreeVar fv = (FreeVar) v.content;
            v.content = new FreeVar(fv.ident, fv.level, true);
            return true;
        }
    }

    @Override
    public void unify(Type t) throws TypeError {
        if (v.content instanceof BoundVar) {
            BoundVar bv = (BoundVar) v.content;
            bv.t.unify(t);
        } else if (t instanceof TypeVar &&
                ((TypeVar) t).v.content instanceof BoundVar) {
            BoundVar bv = (BoundVar) ((TypeVar) t).v.content;
            bv.t.unify(this);
        } else {
            FreeVar fv = (FreeVar) v.content;

            // equality unify
            if (fv.equality) {
                if (!t.tryMakeEquality()) {
                    throw new TypeEqualityError();
                }
            }

            // if t is also a type var, which means t is a free var
            // since bounded var is checked before
            if (t instanceof TypeVar) {
                VarRef v2 = ((TypeVar) t).v;
                FreeVar fv2 = (FreeVar) v2.content;

                // unify same free var returns nothing
                if (fv2.ident == fv.ident &&
                        fv2.level == fv.level &&
                        fv2.equality == fv.equality) {
                    return;
                }
            }

            if (t.occurs(fv.ident, fv.level)) {
                throw new TypeCircularityError();
            } else {
                v.content = new BoundVar(t);
            }
        }
    }

    public String toString() {
        if (v.content instanceof BoundVar) {
            BoundVar bv = (BoundVar) v.content;
            return bv.t.toString();
        } else {
            FreeVar fv = (FreeVar) v.content;
            if (fv.equality) {
                return "eqt" + fv.ident;
            } else {
                return "t" + fv.ident;
            }
        }
    }

    private static abstract class Var {
    }

    private static class FreeVar extends Var {
        public final int ident;
        public final int level;
        public final boolean equality;

        public FreeVar(int ident, int level, boolean equality) {
            this.ident = ident;
            this.level = level;
            this.equality = equality;
        }
    }

    private static class BoundVar extends Var {
        public final Type t;

        public BoundVar(Type t) {
            this.t = t;
        }
    }

    private static class VarRef {
        public Var content;

        public VarRef(Var v) {
            content = v;
        }
    }
}
