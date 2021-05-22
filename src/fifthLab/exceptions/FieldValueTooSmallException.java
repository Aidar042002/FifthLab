package fifthLab.exceptions;

public class FieldValueTooSmallException extends InputException {
    public FieldValueTooSmallException(String minValue) {
        super("Значение должно быть больше  " + minValue + ".");
    }
}