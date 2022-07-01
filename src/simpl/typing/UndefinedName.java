package simpl.typing;

import simpl.ast.Symbol;

public class UndefinedName extends TypeError {

    public UndefinedName(Symbol x) {
        super("Undefined name " + x);
    }
}
