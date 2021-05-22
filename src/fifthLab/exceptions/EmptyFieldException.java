package fifthLab.exceptions;

/**
 * Бросается при попытке пустого значения поля
 */

public class EmptyFieldException extends InputException {
    public EmptyFieldException(String fieldName) {
        super("Поле пусто: " + fieldName );
    }
}