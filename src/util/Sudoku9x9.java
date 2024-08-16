package util;


import java.util.ArrayList;

public class Sudoku9x9 extends Sudoku {
    private int[][] values;
    private int[][] clone;

    public Sudoku9x9(int[][] values) {
        super(values);
        this.values = values;
        clone = values.clone();
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
                    ArrayList <Integer> a=new ArrayList<>();
                    for (int j = 0; j < detail[i].length; j++) {
                       if ((values[i][j] !=0) && a.contains(values[i][j])) return false;
                       else a.add(values[i][j]);
                    }
                }

            }
        }
        return true;
    }

    @Override
    int[] findSuitableNumbers(int line, int column) {//3,2
        ArrayList<Integer> counts = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            if ((!counts.contains(values[line][i])) && values[line][i] != 0) counts.add(values[line][i]);
            if ((!counts.contains(values[i][column])) && values[i][column] != 0) counts.add(values[i][column]);
        }

        for (int k = (line / 3) * 3; k <(line / 3)*3+3 ; k++) {
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
    boolean isTrue() {
        return super.isTrue() && are3x3BlocksValid();
    }
}
