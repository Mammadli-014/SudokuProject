package sudokuException;

// Custom runtime exception for handling cases where Sudoku cannot be solved
public class SudokuCanNotSolvedException extends RuntimeException{

    // Constructor that accepts a message and passes it to the superclass (RuntimeException)
    public SudokuCanNotSolvedException(String message) {
        super(message);
    }
}
