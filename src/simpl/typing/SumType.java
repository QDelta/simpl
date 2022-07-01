package simpl.typing;

import java.util.Map;

public class SumType extends Type {

    public final Type t1;
    public final Type t2;

    public SumType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean occurs(int varId, int varLevel) {
        return t1.occurs(varId, varLevel) || t2.occurs(varId, varLevel);
    }

    @Override
    public Type generalize() {
        return new SumType(t1.generalize(), t2.generalize());
    }

    @Override
    public Type instantiate(Map<Integer, Type> instMap) {
        Type instT1 = t1.instantiate(instMap);
        Type instT2 = t2.instantiate(instMap);
        return new SumType(instT1, instT2);
    }

    @Override
    public boolean tryMakeEquality() {
        return t1.tryMakeEquality() && t2.tryMakeEquality();
    }

    @Override
    public void unify(Type t) throws TypeError {
        if (t instanceof TypeVar) {
            t.unify(this);
        } else if (t instanceof SumType) {
            SumType sumT = (SumType) t;
            t1.unify(sumT.t1);
            t2.unify(sumT.t2);
        } else {
            throw new TypeMismatchError();
        }
    }

    public String toString() {
        return "(" + t1 + " + " + t2 + ")";
    }
}
