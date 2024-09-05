package util;

import java.util.ArrayList;
import java.util.Arrays;

import sudokuException.NotValidSudokuException;
import sudokuException.SudokuCanNotSolvedExpection;

public class Sudoku {
    private int[][] values;
    protected int sudokuSize;
    protected int[][][] suitables = new int[9][9][0];
    private static final int MAX_ITERATIONS = 2000; // Iteration limit

    Sudoku(int[][] values) {
        this.values = values;
        try {
            check();
        } catch (NotValidSudokuException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        sudokuSize = values.length;
    }

    public void solve() {
        int iterations = 0;

        while (!isSolved(values) && isTrue()) {
            changeOptional();
            changeLogicalOption();
            changeLogicalOption();


            int[][] tryed = values.clone();

            if (Arrays.deepEquals(tryed, values)) {
                SudokuClone s = new SudokuClone(this.values);
                this.values = s.tryToSolve();
            }
            iterations++;
            if (iterations == MAX_ITERATIONS) {
                write();
                throw new SudokuCanNotSolvedExpection("Sudoku is not solvable");
            }


        }


    }

    int[] findSuitableNumbers(int line, int column) {//3,2
        ArrayList<Integer> counts = new ArrayList<>();
        for (int i = 0; i < sudokuSize; i++) {
            if (!counts.contains(values[line][i]) && values[line][i] != 0) counts.add(values[line][i]);
            if (!counts.contains(values[i][column]) && values[i][column] != 0) counts.add(values[i][column]);
        }

        int[] suitable = new int[0];
        for (int i = 1; i <= sudokuSize; i++) {
            if (!counts.contains(i)) {
                int[] s2 = suitable.clone();
                suitable = new int[s2.length + 1];
                suitable[suitable.length - 1] = i;
                for (int j = 0; j < s2.length; j++)
                    suitable[j] = s2[j];
            }
        }
        return suitable;
    }

    void findBestSuitableNumber() {
        suitables = new int[sudokuSize][sudokuSize][0];
        for (int i = 0; i < sudokuSize; i++)
            for (int b = 0; b < sudokuSize; b++)
                if (values[i][b] == 0)
                    suitables[i][b] = findSuitableNumbers(i, b);
    }

    void changeOptional() {
        findBestSuitableNumber();
        boolean a = false;
        for (int i = 0; i < sudokuSize; i++)
            for (int j = 0; j < sudokuSize; j++)
                if (values[i][j] == 0) {
                    if (suitables[i][j].length == 1) {
                        change(i, j, suitables[i][j][0]);
                        a = true;
                    }
                    if (a) changeOptional();
                }

    }

    void changeLogicalOption() {
        findBestSuitableNumber();
        for (int row = 0; row < sudokuSize; row++) {
            for (int column = 0; column < sudokuSize; column++) {

                if (values[row][column] == 0)
                    LOOP1:
                            for (int k : suitables[row][column]) {
                                boolean changeMade = true;
                                for (int z = 0; z < sudokuSize; z++) {
                                    if (z != column)
                                        for (int a : suitables[row][z]) {
                                            if (k == a) {
                                                changeMade = false;
                                                continue LOOP1;
                                            }
                                        }
                                    if (z != row)
                                        for (int a : suitables[z][column]) {
                                            if (k == a) {
                                                changeMade = false;
                                                continue LOOP1;
                                            }
                                        }
                                }
                                if (changeMade) {
                                    change(row, column, k);
                                    return;
                                }
                            }
            }
        }
    }

    protected void change(int line, int column, int num) {
        if (values[line][column] == 0) values[line][column] = num;
    }

    boolean isSolved(int [][] values) {
        for (int[] i : values)
            for (int j : i)
                if (j == 0) return false;

        return isTrue();
    }

    boolean isTrue() {
        return areColumnsValid() && areRowsValid();
    }

    private boolean areRowsValid() {
        for (int i = 0; i < sudokuSize; i++) {
            ArrayList<Integer> a = new ArrayList<>();
            for (int j = 0; j < sudokuSize; j++) {
                if ((values[i][j] != 0) && a.contains(values[i][j])) return false;
                else a.add(values[i][j]);
            }
        }
        return true;
    }

    private boolean areColumnsValid() {
        for (int i = 0; i < sudokuSize; i++) {
            ArrayList<Integer> a = new ArrayList<>();
            for (int j = 0; j < sudokuSize; j++) {
                if ((values[j][i] != 0) && a.contains(values[j][i])) return false;
                else a.add(values[j][i]);
            }
        }
        return true;
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

    public void write() {  // a method writing the full sudoku table
        for (int[] a : values) {
            for (int b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void check() throws NotValidSudokuException {
        for (int i = 0; i < values.length; i++) {
            if (values.length != values[i].length) throw new NotValidSudokuException("Sudokunun olculeri dogru deyil!");
        }
    }

}

