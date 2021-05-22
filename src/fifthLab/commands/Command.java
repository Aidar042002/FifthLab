package fifthLab.commands;

import fifthLab.exceptions.ExitException;

/**
 * Интерфейс для лямбда-выражений команд.
 * Используется для работы команды Exit
 *
 * @see Exit
 */

public interface Command {
    void execute() throws ExitException;
}
