package fifthLab.exceptions;

/**
 * Бросается при несуществубщей команде в скрипте
 */

public class CommandNotFoundException extends InputException {
    public CommandNotFoundException(String commandName) {
        super("Нет такой команды: " + commandName);
    }
}
