package fifthLab.commands;

import java.util.LinkedList;

/**
 * У каждой команды есть строитель, возвращающий лямбда-выражение,
 * которое потом CommandManagement запаковывает в Request
 * и метод addHistoryLine вызывается при каждом вызвое команды и соответственно
 * лямбад-выражения
 *
 * Коллекция LinkedList запоминает команды и убирает прошлые так,
 * чтобы осталось только 14 последних
 */

public class History implements CommandBuilder {
    private final LinkedList<String> commandHistory = new LinkedList<>();

    public void addHistoryLine(String[] tokens) {
        commandHistory.add(tokens[0]);
        if (commandHistory.size() > 14)
            commandHistory.remove();
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            for (String commandName : commandHistory) {
                System.out.println(commandName);
            }
            System.out.println();
        };
    }

    @Override
    public String getDescription() {
        return "history : вывести последние 14 команд (без их аргументов)";
    }
}
