import util.Sudoku;
import util.SudokuFactory;

public class Main {
    public static void main(String[] args) {
       int[][] values = {
               {0, 3, 0, 7, 0, 0, 0, 2, 0},
               {6, 0, 0, 0, 0, 0, 0, 5, 0},
               {5, 0, 0, 0, 4, 9, 0, 0, 7},
               {1, 0, 0, 0, 5, 8, 0, 0, 4},
               {0, 0, 0, 0, 0, 2, 0, 0, 6},
               {0, 9, 0, 1, 0, 0, 2, 0, 0},
               {3, 0, 0, 5, 0, 1, 0, 0, 0},
               {0, 0, 7, 0, 3, 0, 0, 0, 2},
               {0, 4, 0, 0, 6, 0, 5, 0, 9}

       };
       SudokuFactory s=new SudokuFactory();
        Sudoku s2=s.getSudoku(values);
        s2.changeOptional();
        s2.writeSuitables();
        //s2.solve();
    }
}