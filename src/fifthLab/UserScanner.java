package fifthLab;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Класс осуществляет пользовательский ввод
 * Позволяет поставить в очередь на обработку
 * другие строки в обход пользовательчкого ввода
 *
 */

public class UserScanner {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayDeque<String> interlines = new ArrayDeque<>();

    public static int countInterLines() {
        return interlines.size();
    }

    public static void interfere(String interline) {
        interlines.addFirst(interline);
    }

    public static String nextLine() {
        return nextLine(true);
    }

    public static String nextLine(boolean needToPrint) {
        if (interlines.isEmpty()) {
            if (!scanner.hasNextLine()) {
                System.out.println("Нажатие Ctrl+D");
                scanner = new Scanner(System.in);
                return null;
            }
            return scanner.nextLine();
        }
        String interline = interlines.removeFirst();
        if (needToPrint)
            System.out.println(interline);
        return interline;
    }
}
