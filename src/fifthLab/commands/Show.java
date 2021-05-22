package fifthLab.commands;

import fifthLab.exceptions.*;
import fifthLab.CollectionManager;
import fifthLab.parameters.Product;

/**
 * Можно использовать для просмотра всей коллекции
 * или элемента с конкртеным ID
 *
 * @see Product
 */

public class Show implements CommandBuilder {
    private final CollectionManager manager;

    public Show(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) throws InputException {
        if (tokens.length < 2) {
            return () -> {
                for (Product product : manager.elements)
                    System.out.println(product.getInfoString());
            };
        }
        try {
            long ID = Long.parseLong(tokens[1]);
            return () -> {
                Product product = manager.productsByID.get(ID);
                if (product == null) {
                    System.out.println("Нет элемента с таким ID.");
                } else {
                    System.out.println(product.getInfoString());
                }
            };
        } catch (NumberFormatException exception) {
            throw new UncorrectFieldException("ID");
        }
    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
