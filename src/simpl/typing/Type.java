package simpl.typing;

import java.util.Map;

public abstract class Type {

    public abstract boolean occurs(int varId, int varLevel);

    public abstract Type generalize();

    public abstract Type instantiate(Map<Integer, Type> instMap);

    public abstract boolean tryMakeEquality();

    public abstract void unify(Type t) throws TypeError;
}
