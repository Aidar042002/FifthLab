package fifthLab.commands;

import fifthLab.*;
import fifthLab.parameters.Product;

/**
 * За критерий взята стоимость(price)
 * Значение нового элемента должно превышать значение с наибольшей стоимостью
 * Лямбда-выражение добавляет элемент и сравнивает с уже добавленными.
 * Если находится элемент с такой же или больше стоимостью, текущий добавленный удаляется.
 *
 * @see CollectionManager
 */

public class AddIfMax implements CommandBuilder {
    private final CollectionManager manager;

    public AddIfMax(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return () -> {
            long ID = manager.ElementAdding();
            int price = (int) manager.productsByID.get(ID).getPrice();
            for (Product product : manager.elements) {
                if (price <= product.getPrice() && product.getId() != ID) {
                    System.out.println("У производителя " + product.getId() + " стоимость меньше ");
                    manager.removeElement(ID);
                    return;
                }
            }
            System.out.printf("Элементо добавлен в коллекцию (ID: %d).%n", ID);
        };
    }

    @Override
    public String getDescription() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}