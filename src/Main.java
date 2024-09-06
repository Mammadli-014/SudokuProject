import util.Sudoku;
import util.SudokuFactory;

public class Main {
    public static void main(String[] args) {

        int[][] values3 = {
                {0, 0, 6, 3, 0, 7, 0, 0, 0},
                {0, 0, 4, 0, 0, 0, 0, 0, 5},
                {1, 0, 0, 0, 0, 6, 0, 8, 2},
                {2, 0, 5, 0, 3, 0, 1, 0, 6},
                {0, 0, 0, 2, 0, 0, 3, 0, 0},
                {9, 0, 0, 0, 7, 0, 0, 0, 4},
                {0, 5, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 8, 1, 0, 9, 0, 4, 0}

        };
        SudokuFactory sudokuFactory = new SudokuFactory();

        Sudoku sudoku3 = sudokuFactory.getSudoku(values3);
        sudoku3.writeSuitables();
        sudoku3.solve();
        //sudoku3.write();
    }
}