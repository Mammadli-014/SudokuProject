import util.Sudoku;
import util.SudokuFactory;

public class Main {
    public static void main(String[] args) {
        int[][] values1 = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        int [][] values2={
                {0, 0, 3, 0, 2, 0, 6, 0, 0},
                {9, 0, 0, 3, 0, 5, 0, 0, 1},
                {0, 0, 1, 8, 0, 6, 4, 0, 0},
                {0, 0, 8, 1, 0, 2, 9, 0, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 6, 7, 0, 8, 2, 0, 0},
                {0, 0, 2, 6, 0, 9, 5, 0, 0},
                {8, 0, 0, 2, 0, 3, 0, 0, 9},
                {0, 0, 5, 0, 1, 0, 3, 0, 0}
        };
        int [][] values3={
                {0, 0, 0, 0, 0, 0, 0, 0, 5},
                {0, 0, 0, 0, 6, 0, 0, 4, 0},
                {0, 5, 0, 0, 0, 3, 0, 0, 0},
                {0, 0, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0}
        };

       /*



        */
        SudokuFactory sudokuFactory = new SudokuFactory();

        //Sudoku sudoku1 = sudokuFactory.getSudoku(values1);
        //sudoku1.solve();
        //sudoku1.write();

        Sudoku sudoku2=sudokuFactory.getSudoku(values2);
        sudoku2.writeSuitables();
        sudoku2.solve();
        sudoku2.write();

        Sudoku sudoku3=sudokuFactory.getSudoku(values3);
        sudoku3.solve();
        sudoku3.write();
    }
}