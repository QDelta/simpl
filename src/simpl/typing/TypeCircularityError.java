package simpl.typing;

public class TypeCircularityError extends TypeError {

    public TypeCircularityError() {
        super("Circularity");
    }
}
