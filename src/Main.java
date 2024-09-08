import util.Sudoku;
import util.SudokuFactory;

public class Main {
    public static void main(String[] args) {

        // Define a hard-level Sudoku puzzle
        int[][] values1 = {
                {0, 0, 0, 0, 6, 2, 0, 8, 0},
                {0, 2, 7, 3, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 0},
                {1, 0, 0, 0, 0, 0, 0, 6, 0},
                {0, 3, 0, 0, 0, 0, 0, 0, 4},
                {0, 0, 8, 2, 5, 0, 7, 0, 0},
                {0, 4, 6, 0, 0, 0, 0, 9, 0},
                {0, 1, 0, 0, 0, 3, 4, 0, 0},
                {0, 0, 0, 0, 0, 8, 0, 3, 0}
        };
        /*
        Example solution for the hard-level Sudoku puzzle:
        4 9 3 7 6 2 5 8 1
        5 2 7 3 8 1 6 4 9
        6 8 1 5 4 9 3 7 2
        1 5 4 9 3 7 2 6 8
        7 3 2 8 1 6 9 5 4
        9 6 8 2 5 4 7 1 3
        3 4 6 1 2 5 8 9 7
        8 1 9 6 7 3 4 2 5
        2 7 5 4 9 8 1 3 6
        */

        // Define an easy-level Sudoku puzzle
        int[][] values2 = {
                {0, 0, 3, 0, 0, 7, 5, 0, 0},
                {2, 5, 9, 6, 0, 0, 0, 0, 3},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {9, 0, 2, 0, 0, 5, 0, 0, 8},
                {7, 8, 0, 0, 0, 0, 4, 2, 0},
                {0, 0, 0, 0, 0, 0, 9, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 4},
                {0, 0, 4, 0, 6, 8, 2, 3, 7},
                {0, 2, 0, 4, 1, 0, 6, 0, 0}
        };
        /*
        Example solution for the easy-level Sudoku puzzle:
        8 6 3 1 9 7 5 4 2
        2 5 9 6 8 4 7 1 3
        4 7 1 3 5 2 8 9 6
        9 1 2 7 4 5 3 6 8
        7 8 5 9 3 6 4 2 1
        3 4 6 8 2 1 9 7 5
        6 3 8 2 7 9 1 5 4
        1 9 4 5 6 8 2 3 7
        5 2 7 4 1 3 6 8 9
        */

        // Define another hard-level Sudoku puzzle
        int[][] values3 = {
                {5, 0, 6, 3, 0, 7, 0, 0, 0},
                {0, 0, 4, 0, 0, 0, 0, 0, 5},
                {1, 0, 0, 0, 0, 6, 0, 8, 2},
                {2, 0, 5, 0, 3, 0, 1, 0, 6},
                {0, 0, 0, 2, 0, 0, 3, 0, 0},
                {9, 0, 0, 0, 7, 0, 0, 0, 4},
                {0, 5, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 8, 1, 0, 9, 0, 4, 0}
        };
        /*
        Example solution for the second hard-level Sudoku puzzle:
        5 8 6 3 2 7 4 9 1
        7 2 4 8 9 1 6 3 5
        1 9 3 5 4 6 7 8 2
        2 4 5 9 3 8 1 7 6
        8 6 7 2 1 4 3 5 9
        9 3 1 6 7 5 8 2 4
        4 5 2 7 6 3 9 1 8
        3 1 9 4 8 2 5 6 7
        6 7 8 1 5 9 2 4 3
        */

        // Create a SudokuFactory instance

        // Solve and display the first Sudoku puzzle
        Sudoku sudoku1 = SudokuFactory.getSudokuFactory().getSudoku(values1);
        sudoku1.solve();
        sudoku1.write();

        // Solve and display the second Sudoku puzzle
        Sudoku sudoku2 = SudokuFactory.getSudokuFactory().getSudoku(values2);
        sudoku2.solve();
        sudoku2.write();

        // Solve and display the third Sudoku puzzle
        Sudoku sudoku3 = SudokuFactory.getSudokuFactory().getSudoku(values3);
        sudoku3.solve();
        sudoku3.write();
    }
}