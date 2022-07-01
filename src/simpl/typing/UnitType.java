package simpl.typing;

import java.util.Map;

public final class UnitType extends Type {

    public static final UnitType T = new UnitType();

    private UnitType() {
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
        return false;
    }

    @Override
    public void unify(Type t) throws TypeError {
        if (t instanceof TypeVar) {
            t.unify(this);
        } else if (!(t instanceof UnitType)) {
            throw new TypeMismatchError();
        }
    }

    public String toString() {
        return "unit";
    }
}
