package fifthLab.commands;

import java.util.HashMap;

/**
 * при написании help {название команды} выводится только справка по команде
 * getDescription() объясняет что делает команда
 * addDescription вызывается при добавлении новой команды
 * при вызове getDescription() получаем описание, кторое позже прикрепляется к HashMap
 */

public class Help implements CommandBuilder {
    private StringBuilder defaultHelpString = new StringBuilder();
    private final HashMap<String, String> descriptions = new HashMap<>();

    public void addDescription(String commandName, String description) {
        descriptions.put(commandName, description);
        defaultHelpString.append(description).append("\n");
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            if (tokens.length > 1) {
                if (descriptions.containsKey(tokens[1])) {
                    System.out.println(descriptions.get(tokens[1]));
                } else {
                    System.out.println("По команде " + tokens[1] + " справки отсутствует.");
                }
            } else {
                System.out.println(defaultHelpString);
            }
        };
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}