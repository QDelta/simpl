package simpl.ast;

import simpl.typing.IntType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;

public abstract class ArithExpr extends BinaryExpr {

    public ArithExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public Type typeCheck(TypeEnv E) throws TypeError {
        Type lt = l.typeCheck(E);
        Type rt = r.typeCheck(E);
        lt.unify(IntType.T);
        rt.unify(IntType.T);
        return IntType.T;
    }
}
