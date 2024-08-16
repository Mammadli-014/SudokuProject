package exception;


import java.util.zip.CheckedInputStream;

public class NotValidSudokuException extends Exception {
    public NotValidSudokuException(String message) {
        super(message);

    }
}
