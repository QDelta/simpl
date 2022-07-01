package simpl.typing;

import java.util.Map;

public class QuantVar extends Type {

    public final int ident;
    public final boolean equality;

    public QuantVar(int ident, boolean equality) {
        this.ident = ident;
        this.equality = equality;
    }

    @Override
    public boolean occurs(int varId, int varLevel) {
        return false;
    }

    @Override
    public Type generalize() {
        return this;
    }

    @Override
    public Type instantiate(Map<Integer, Type> instMap) {
        Type replace = instMap.getOrDefault(ident, null);
        if (replace == null) {
            Type v = new TypeVar(equality);
            instMap.put(ident, v);
            return v;
        } else {
            return replace;
        }
    }

    @Override
    public boolean tryMakeEquality() {
        return equality;
    }

    @Override
    public void unify(Type t) throws TypeError {
        throw new TypeMismatchError();
    }

    public String toString() {
        if (equality) {
            return "eqUt" + ident;
        } else {
            return "ut" + ident;
        }
    }
}
