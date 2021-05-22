package fifthLab;

import fifthLab.exceptions.ExitException;

/**
 * Интерфейс в который CommandManagement упаковывает команду
 */

public interface CommandPacking {
    void execute() throws ExitException;
}
