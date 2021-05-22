package fifthLab;

import fifthLab.exceptions.InputException;

import java.util.function.BooleanSupplier;

/**
 * Классу предается начальная строка-приглашения
 * и далее идет проверка, которая бросает исключение
 * при некорректном вводе
 *
 * При неправльном вводе с помощью цикла будет
 * предложение ввести снова
 *
 * В конструктор также может передаваться стоп-лямбда, по которой
 * определяется, в какой момент прервать цикл.
 * @param <T>
 */

public class InputProvider<T> {
    private final String invitation;
    private final CheckInterface<T> validator;
    private final BooleanSupplier needToStop;

    public InputProvider(String invitation, CheckInterface<T> validator) {
        this(invitation, validator, () -> false);
    }

    public InputProvider(String invitation, CheckInterface<T> validator, BooleanSupplier needToStop) {
        this.invitation = invitation;
        this.validator = validator;
        this.needToStop = needToStop;
    }

    public T provide() {
        while (!needToStop.getAsBoolean()) {
            System.out.print(invitation);
            String input = UserScanner.nextLine();
            if (input == null)
                continue;
            input = input.trim().replaceAll("( )+", " ");
            T result;
            try {
                result = validator.get(input);
            } catch (InputException exception) {
                System.out.println(exception.getMessage());
                continue;
            }
            return result;
        }
        return null;
    }
}
