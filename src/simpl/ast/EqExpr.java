package simpl.ast;

import simpl.typing.*;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type v = new TypeVar(true);
        Type lt = l.typeCheck(E);
        Type rt = r.typeCheck(E);
        v.unify(lt);
        v.unify(rt);
        return BoolType.T;
    }
}
