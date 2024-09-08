package sudokuException;

// Custom exception class for handling invalid numbers in Sudoku
public class InvalidNumberException extends Exception {

    // Constructor that accepts a message and passes it to the superclass (Exception)
    public InvalidNumberException(String message) {
        super(message);
    }
}