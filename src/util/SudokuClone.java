package util;


class SudokuClone extends Sudoku {
    private int values [][];
    SudokuClone(int[][] values) {
        super(values);
        this.values =cloneSudoku(values);
    }

    int [][] tryToSolve(int[][] values) {

            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < values[i].length; j++) {

                    if (values[i][j] == 0) {

                        int[] suitable = findSuitableNumbers(i, j);
                        for (int k = 0; k < suitable.length; k++) {

                            values[i][j] = suitable[k];
                            if (!isTrue()) {
                                values[i][j] = 0;
                            } else  {
                            int[][] solved = tryToSolve(cloneSudoku(values));
                            if (isSolved()) return solved;}
                        }
                    }

                }
            }
            return values;
    }

    private int[][] cloneSudoku(int [][] values) {
        int[][] clone = new int[values.length][values.length];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                clone[i][j] = values[i][j];
            }
        }
        return clone;
    }


}