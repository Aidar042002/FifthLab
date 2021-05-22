package fifthLab;

import fifthLab.exceptions.InputException;

public interface CheckInterface<T> {
    T get(String input) throws InputException;
}
