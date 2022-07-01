package simpl.typing;

import java.util.Map;

public final class ArrowType extends Type {

    public final Type t1;
    public final Type t2;

    public ArrowType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean occurs(int varId, int varLevel) {
        return t1.occurs(varId, varLevel) || t2.occurs(varId, varLevel);
    }

    @Override
    public Type generalize() {
        return new ArrowType(t1.generalize(), t2.generalize());
    }

    @Override
    public Type instantiate(Map<Integer, Type> instMap) {
        Type instT1 = t1.instantiate(instMap);
        Type instT2 = t2.instantiate(instMap);
        return new ArrowType(instT1, instT2);
    }

    @Override
    public boolean tryMakeEquality() {
        return false;
    }

    @Override
    public void unify(Type t) throws TypeError {
        if (t instanceof TypeVar) {
            t.unify(this);
        } else if (t instanceof ArrowType) {
            ArrowType arrT = (ArrowType) t;
            t1.unify(arrT.t1);
            t2.unify(arrT.t2);
        } else {
            throw new TypeMismatchError();
        }
    }

    public String toString() {
        return "(" + t1 + " -> " + t2 + ")";
    }
}
