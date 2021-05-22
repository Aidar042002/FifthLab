package fifthLab.commands;

import fifthLab.CollectionManager;

/**
 * Команда Add.
 * Метод build возвращает лямбда-выражение, используя метод initiateElementAddingProcedure()
 *
 * @see CollectionManager
 */

public class Add implements CommandBuilder {
    private final CollectionManager manager;

    public Add(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            long ID = manager.ElementAdding();
            System.out.printf("Элемент добавлен в коллекцию (ID: %d).%n", ID);
        };
    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}