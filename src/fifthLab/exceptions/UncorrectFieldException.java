package fifthLab.exceptions;

/**
 * Бросается при попытке ввести ввести
 * некорректное поле
 */

public class UncorrectFieldException extends InputException {
    public UncorrectFieldException(String fieldName) {
        super("Некорректно : " + fieldName + ".");
    }
}