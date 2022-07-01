package simpl.interpreter;

public abstract class LibFunValue extends FunValue {

    public abstract Value apply(Value v) throws RuntimeError;
}
