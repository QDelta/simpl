package simpl.typing;

import java.util.Map;

public final class ListType extends Type {

    public final Type t;

    public ListType(Type t) {
        this.t = t;
    }

    @Override
    public boolean occurs(int varId, int varLevel) {
        return t.occurs(varId, varLevel);
    }

    @Override
    public Type generalize() {
        return new ListType(t.generalize());
    }

    @Override
    public Type instantiate(Map<Integer, Type> instMap) {
        return new ListType(t.instantiate(instMap));
    }

    @Override
    public boolean tryMakeEquality() {
        return t.tryMakeEquality();
    }

    @Override
    public void unify(Type t) throws TypeError {
        if (t instanceof TypeVar) {
            t.unify(this);
        } else if (t instanceof ListType) {
            this.t.unify(((ListType) t).t);
        } else {
            throw new TypeMismatchError();
        }
    }

    public String toString() {
        return t + " list";
    }
}
