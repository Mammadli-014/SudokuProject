package util;

// Factory class for creating Sudoku instances
public class SudokuFactory {
    private static SudokuFactory sudokuFactory=null;

    private SudokuFactory(){}

    public static SudokuFactory getSudokuFactory(){
        if (sudokuFactory == null) return new SudokuFactory();
        return sudokuFactory;
    }

    // Method to return a Sudoku instance based on the size of the input values
    public Sudoku getSudoku(int[][] values) {
        // Check if the Sudoku is a 9x9 grid
        if (values.length == 9) {
            // Return a 9x9 Sudoku instance
            return new Sudoku9x9(values);
        } else {
            // Return a general Sudoku instance for other grid sizes
            return new Sudoku(values);
        }
    }
}
