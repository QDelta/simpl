package simpl.typing;

import simpl.ast.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private final TypeEnv Globals;

    public DefaultTypeEnv() {
        Type tv1 = new QuantVar(1, false);
        Type tv2 = new QuantVar(2, false);
        Type pt = new PairType(tv1, tv2);
        Type lt = new ListType(tv1);
        Globals = TypeEnv.empty
                .bind(Symbol.symbol("fst"), new ArrowType(pt, tv1))
                .bind(Symbol.symbol("snd"), new ArrowType(pt, tv2))
                .bind(Symbol.symbol("hd"), new ArrowType(lt, tv1))
                .bind(Symbol.symbol("tl"), new ArrowType(lt, lt))
                .bind(Symbol.symbol("iszero"), new ArrowType(IntType.T, BoolType.T))
                .bind(Symbol.symbol("pred"), new ArrowType(IntType.T, IntType.T))
                .bind(Symbol.symbol("succ"), new ArrowType(IntType.T, IntType.T));
    }

    @Override
    public Type get(Symbol x) {
        return Globals.get(x);
    }
}
