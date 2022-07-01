package simpl.typing;

import simpl.ast.Symbol;

public abstract class TypeEnv {

    public static final TypeEnv empty = new TypeEnv() {
        @Override
        public Type get(Symbol x) {
            return null;
        }

        public String toString() {
            return ".";
        }
    };

    public abstract Type get(Symbol x);

    public TypeEnv bind(Symbol x, Type t) {
        TypeEnv E = this;
        return new TypeEnv() {
            public Type get(Symbol x1) {
                if (x == x1) return t;
                return E.get(x1);
            }

            public String toString() {
                return E + "," + x + ":" + t;
            }
        };
    }
}
