package fifthLab.commands;

import fifthLab.exceptions.ExitException;

/**
 * Исключение, бросаемое данной командой обрабатывается в классе Main
 */

public class Exit implements CommandBuilder {
    @Override
    public Command build(String[] tokens) {
        return () -> {
            System.out.println("Выход");
            throw new ExitException();
        };
    }

    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
