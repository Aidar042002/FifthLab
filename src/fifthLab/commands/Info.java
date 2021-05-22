package fifthLab.commands;

import fifthLab.CollectionManager;

/**
 * С помощью лямбда-выражения отображает то,что возвращает CollectionManager.
 *
 * @see CollectionManager
 */

public class Info implements CommandBuilder {
    private final CollectionManager manager;

    public Info(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Command build(String[] tokens) {
        return () -> System.out.println(manager.getInfo());
    }

    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
