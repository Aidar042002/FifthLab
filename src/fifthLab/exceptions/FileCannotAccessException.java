package fifthLab.exceptions;

/**
 * При отсутствии доступа к файлу
 */

public class FileCannotAccessException extends UncorrectFieldException {
    public FileCannotAccessException(String filePath) {
        super("нет доступа к(" + filePath + ") ");
    }
}