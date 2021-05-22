package fifthLab;

import java.io.*;
import fifthLab.exceptions.*;

/**
 * Чтение и запись в файл.
 */

public class FileControl {
    /**
     * Чтение файла. Реализовано с помощью класса InputStreamReader.
     *
     * @param filePath
     * @return
     * @throws InputException
     */
    public static String readFromFile(String filePath) throws InputException {
        File file = new File(filePath);
        if (!file.exists())
            throw new FileDoesNotExistException(filePath);
        if (file.isDirectory())
            throw new NotFileButDirectoryException(filePath);
        if (!file.canRead() && !file.canWrite())
            throw new FileCannotAccessException(filePath);
        if (!file.canRead())
            throw new FileCannotBeReadException(filePath);
        try {
            Reader in = new InputStreamReader(new FileInputStream(filePath));
            char[] fileContent = new char[(int) file.length()];
            in.read(fileContent);
            in.close();
            return String.valueOf(fileContent);
        } catch (FileNotFoundException exception) {
            throw new InputException("Ошибка ввода");
        } catch (IOException exception) {
            throw new UncorrectFieldException("файл не прочитался\n" + exception.getMessage());
        }
    }

    /**
     * Писать в файл. Реализовано с помощью класса OutputStreamWriter.
     *
     * @param filePath
     * @param content
     * @throws InputException
     */

    public static void saveToFile(String filePath, String content) throws InputException {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filePath));
            outputStreamWriter.write(String.valueOf(content.getBytes()));
            outputStreamWriter.close();
        } catch (Exception exception) {
            throw new InputException(exception.getMessage());
        }
    }
}