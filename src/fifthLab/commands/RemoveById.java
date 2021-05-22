package fifthLab.commands;


import fifthLab.CollectionManager;
import fifthLab.exceptions.EmptyFieldException;
import fifthLab.exceptions.InputException;
import fifthLab.exceptions.UncorrectFieldException;

/**
 * Есть проверка, чтобы команда не вызвалась без аргументов
 *
 */

public class RemoveById implements CommandBuilder {
    private final CollectionManager manager;

    public RemoveById(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) throws InputException {
        if (tokens.length > 1) {
            try {
                long ID = Long.parseLong(tokens[1]);
                return () -> {
                    if (manager.removeElement(ID)) {
                        System.out.printf("Элемент (ID: %d) успешно удалён.%n", ID);
                    } else {
                        System.out.println("Нет элемента с таким ID.");
                    }
                };
            } catch (NumberFormatException exception) {
                throw new UncorrectFieldException("ID");
            }
        } else {
            throw new EmptyFieldException("ID");
        }
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
