package sudokuException;

// Custom exception class for handling invalid Sudoku puzzles
public class NotValidSudokuException extends Exception {

    // Constructor that accepts a message and passes it to the superclass (Exception)
    public NotValidSudokuException(String message) {
        super(message);
    }
}
