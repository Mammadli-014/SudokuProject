package exception;

public class SudokuCanNotSolvedExpection extends RuntimeException{
    public SudokuCanNotSolvedExpection(String message) {
        super(message);
    }
}
