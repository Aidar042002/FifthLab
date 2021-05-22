package fifthLab.commands;

import fifthLab.commands.Command;
import fifthLab.exceptions.InputException;

/**
 * Интерфейс для строителей команд, который
 * позволяет объединить их и раюотать как содним типом,
 * используя коллекция HashMap CommandManagement
 *
 * @see fifthLab.CommandManagement
 */

public interface CommandBuilder {
    Command build(String[] tokens) throws InputException;
    String getDescription();
}