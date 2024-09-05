package util;


class SudokuClone extends Sudoku {
    int [][] values;
    SudokuClone(int [][] values) {
        super(values);
        this.values=values;
    }

    int [][] tryToSolve() {
        findBestSuitableNumber();
        LOOP:for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {

                if (this.values[i][j] == 0) {

                    int[] suitable = super.suitables[i][j];

                    for (int k = 0; k < suitable.length; k++) {
                        this.values[i][j] = suitable[k];

                        if (isTrue()) {
                            if (isSolved(this.values)) return this.values;

                            this.values = tryToSolve(); // Rekürsif çözüm denemesi

                            // Eğer çözüm bulunduyse geri dön
                            if (isSolved(this.values)) break LOOP;

                        /*
                            if (isTrue()) {
                                values[i][j] = 0;
                            } else  {
                            values=tryToSolve(values);
                            if (isSolved(values)) return values;
                            }
                        }

                         */
                        }
                    }
                }
            }
        }
        return this.values;
    }
    public void writeSuitables() { // a function writing suitables numbers for empty areas
        findBestSuitableNumber();
        for (int[][] suitable : suitables) {
            for (int[] ints : suitable) {
                System.out.print("[");
                int count = 0;
                for (int anInt : ints) {
                    ++count;
                    System.out.print(anInt);
                }

                System.out.print("]");
                for (; count < 7; count++)
                    System.out.print(" ");

            }
            System.out.println();
        }
        System.out.println();
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