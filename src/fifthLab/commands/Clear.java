package fifthLab.commands;

import fifthLab.CollectionManager;

/**
 * Использует метод clear() у класса CollectionManager
 *
 * @see CollectionManager
 */

public class Clear implements CommandBuilder {
    private final CollectionManager manager;

    public Clear(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            manager.clear();
            System.out.println("Коллекция пуста");
        };
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
