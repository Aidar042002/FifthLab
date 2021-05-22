package fifthLab.exceptions;

/**
 * Бросается при неправильном вводе поля
 * Используется для того чтобы
 * при неправильном вводе была
 * возможность повторить ввод
 */

public class InputException extends Exception{
    public InputException(String errorMessage) {
        super(errorMessage);
    }
}
