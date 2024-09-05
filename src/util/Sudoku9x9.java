package util;


import java.util.ArrayList;

public class Sudoku9x9 extends Sudoku {
    private int[][] values;

    public Sudoku9x9(int[][] values) {
        super(values);
        this.values = values;
    }

    @Override
    void changeLogicalOption() {
        findBestSuitableNumber();
        for (int row = 0; row < sudokuSize; row++) {
            for (int column = 0; column < sudokuSize; column++) {

                if (values[row][column] == 0)
                    LOOP1:
                            for (int k : suitables[row][column]) {
                                for (int z = 0; z < sudokuSize; z++) {
                                    if (z != column)
                                        for (int a : suitables[row][z]) {
                                            if (k == a) continue LOOP1;

                                        }
                                    if (z != row)
                                        for (int a : suitables[z][column]) {
                                            if (k == a) continue LOOP1;

                                        }
                                }
                                for (int x = (row / 3) * 3; x < (row / 3) * 3 + 3; x++) {
                                    for (int y = (column / 3) * 3; y < (column / 3) * 3 + 3; y++) {
                                        for (int a:suitables[x][y]) if (k==a) continue LOOP1;
                                    }
                                }
                                change(row, column, k);
                            }
            }
        }
    }

    @Override
    int[] findSuitableNumbers(int row, int column) {//3,2
        ArrayList<Integer> counts = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            if ((!counts.contains(values[row][i])) && values[row][i] != 0) counts.add(values[row][i]);
            if ((!counts.contains(values[i][column])) && values[i][column] != 0) counts.add(values[i][column]);
        }

        for (int k = (row / 3) * 3; k < (row / 3) * 3 + 3; k++) {
            for (int y = (column / 3) * 3; y < (column / 3) * 3 + 3; y++) {

                if ((!counts.contains(values[k][y])) && values[k][y] != 0)
                    counts.add(values[k][y]);
            }
        }

        int[] suitable = new int[0];
        for (int i = 1; i <= values.length; i++) {
            if (!counts.contains(i)) {
                int[] s2 = suitable.clone();
                suitable = new int[s2.length + 1];
                suitable[suitable.length - 1] = i;
                System.arraycopy(s2, 0, suitable, 0, s2.length);
            }
        }


        return suitable;
    }

    @Override
    void findBestSuitableNumber() {
        suitables = new int[sudokuSize][sudokuSize][0];
        for (int i = 0; i < sudokuSize; i++)
            for (int j = 0; j < sudokuSize; j++)
                if (values[i][j] == 0) suitables[i][j] = findSuitableNumbers(i, j);
    }

    @Override
    boolean isTrue() {
        return super.isTrue() && are3x3BlocksValid();
    }

    private boolean are3x3BlocksValid() {
        for (int k = 0; k < 3; k++) {
            for (int y = 0; y < 3; y++) {
                int[][] detail = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        detail[i][j] = values[k * 3 + i][y * 3 + j];
                    }
                }
                for (int i = 0; i < detail.length; i++) {
                    ArrayList<Integer> a = new ArrayList<>();
                    for (int j = 0; j < detail[i].length; j++) {
                        if ((values[i][j] != 0) && a.contains(values[i][j])) return false;
                        else a.add(values[i][j]);
                    }
                }

            }
        }
        return true;
    }
}
