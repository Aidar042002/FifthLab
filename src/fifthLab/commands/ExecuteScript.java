package fifthLab.commands;

import fifthLab.exceptions.*;
import fifthLab.*;

import java.util.HashSet;

/**
 * Сначал загружается содержимое,
 * Далее идет исполнение команды
 * Команда исполняет содежимое скрипта
 * Нет возможности сделать скрипту рекурсивный вызов через другие скрипты
 */

public class ExecuteScript implements CommandBuilder {
    private final CollectionManager manager;
    private final HashSet<String> superCoreScripts;

    public ExecuteScript(HashSet<String> superCoreScripts, CollectionManager manager) {
        this.manager = manager;
        this.superCoreScripts = superCoreScripts;
    }

    @Override
    public Command build(String[] tokens) throws InputException {
        if (tokens.length < 2)
            throw new EmptyFieldException("путь к файлу");
        String script = FileControl.readFromFile(tokens[1]);
        if (superCoreScripts.contains(script))
            throw new RecursiveScriptExecutionException(tokens[1]);
        HashSet<String> subCoreScripts = new HashSet<>(superCoreScripts);
        subCoreScripts.add(script);
        CommandManagement subCore = new CommandManagement(subCoreScripts, manager);
        return () -> {
            System.out.println("Исполнение скрипта " + tokens[1] + "...\n");
            String[] lines = script.split("\\r?\\n");
            int previousSize = UserScanner.countInterLines();
            for (int i = lines.length-1; i >= 0; i--)
                UserScanner.interfere(lines[i]);
            while (true) {
                InputProvider<CommandPacking> inputProvider = new InputProvider<>("Введите команду: ",
                        subCore::buildRequest, () -> UserScanner.countInterLines() == previousSize);
                CommandPacking request = inputProvider.provide();
                if (request == null)
                    break;
                try {
                    request.execute();
                } catch (ExitException exception) {
                    while (UserScanner.countInterLines() != previousSize)
                        UserScanner.nextLine(false);
                }
            }
            System.out.println("\nСкрипт выполенения" + tokens[1] + " закончился, выход");
        };
    }

    @Override
    public String getDescription() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}