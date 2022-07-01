package simpl.typing;

import java.util.Map;

public final class BoolType extends Type {

    public static final BoolType T = new BoolType();

    private BoolType() {
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
        return this;
    }

    @Override
    public boolean tryMakeEquality() {
        return true;
    }

    @Override
    public void unify(Type t) throws TypeError {
        if (t instanceof TypeVar) {
            t.unify(this);
        } else if (!(t instanceof BoolType)) {
            throw new TypeMismatchError();
        }
    }

    public String toString() {
        return "bool";
    }
}
