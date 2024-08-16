package util;

public class SudokuFactory {
    public Sudoku getSudoku(int [][] values){
        if (values.length == 9) return new Sudoku9x9(values);
        else return new Sudoku(values);
    }
}
