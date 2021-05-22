package fifthLab.commands;

import fifthLab.CollectionManager;
import fifthLab.exceptions.EmptyFieldException;
import fifthLab.exceptions.InputException;
import fifthLab.exceptions.UncorrectFieldException;

/**
 * Обновление значения по ID
 */

public class Update implements CommandBuilder {
    private final CollectionManager manager;

    public Update(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) throws InputException {
        if (tokens.length > 1) {
            try {
                long ID = Long.parseLong(tokens[1]);
                return () -> {
                    if (manager.initiateElementUpdatingProcedure(ID)) {
                        System.out.printf("Элемент (ID: %d) успешно обновлён.%n", ID);
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
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
