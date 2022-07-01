package simpl.typing;

import java.util.Map;

public final class RefType extends Type {

    public final Type t;

    public RefType(Type t) {
        this.t = t;
    }

    @Override
    public boolean occurs(int varId, int varLevel) {
        return t.occurs(varId, varLevel);
    }

    @Override
    public Type generalize() {
        return new RefType(t.generalize());
    }

    @Override
    public Type instantiate(Map<Integer, Type> instMap) {
        return new RefType(t.instantiate(instMap));
    }

    @Override
    public boolean tryMakeEquality() {
        return true;
    }

    @Override
    public void unify(Type t) throws TypeError {
        if (t instanceof TypeVar) {
            t.unify(this);
        } else if (t instanceof RefType) {
            this.t.unify(((RefType) t).t);
        } else {
            throw new TypeMismatchError();
        }
    }

    public String toString() {
        return t + " ref";
    }
}
