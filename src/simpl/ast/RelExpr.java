package simpl.ast;

import simpl.typing.*;

public abstract class RelExpr extends BinaryExpr {

    public RelExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type lt = l.typeCheck(E);
        Type rt = r.typeCheck(E);
        lt.unify(IntType.T);
        rt.unify(IntType.T);
        return BoolType.T;
    }
}
