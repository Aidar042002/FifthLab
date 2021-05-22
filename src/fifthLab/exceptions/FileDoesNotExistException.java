package fifthLab.exceptions;

/**
 * Бросается при попытке ввести несуществующий файл
 */

public class FileDoesNotExistException extends UncorrectFieldException {
    public FileDoesNotExistException(String filePath) {
        super("Файл (" + filePath + ") не существует");
    }
}