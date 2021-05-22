package fifthLab.exceptions;

/**
 * При попытке прочтения файла с закрытым доступом
 */

public class FileCannotBeReadException extends UncorrectFieldException {
    public FileCannotBeReadException(String filePath) {
        super("Файл (" + filePath + ") нельзя читать");
    }
}
