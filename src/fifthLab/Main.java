package fifthLab;

import fifthLab.exceptions.ExitException;

public class Main {
    public static void main(String[] args){
        CommandManagement commandManagement;
        commandManagement = new CommandManagement(args[0]);
        InputProvider<CommandPacking> inputProvider = new InputProvider<>("Введите команду: ", commandManagement::buildRequest);
        while (true) {
            CommandPacking commandPacking = inputProvider.provide();
            try {
                commandPacking.execute();
            } catch (ExitException exception) {
                return;
            }
        }
    }
}
