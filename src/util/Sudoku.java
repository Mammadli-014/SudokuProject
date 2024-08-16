package util;

import java.util.ArrayList;
import java.util.Arrays;

import exception.NotValidSudokuException;
import exception.SudokuCanNotSolvedExpection;

public class Sudoku {
    private int[][] values;
    int sudokuSize;
    private int[][][] suitables = new int[5][5][0];
    private static final int MAX_ITERATIONS = 20000; // Iteration limit

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

        while (!isSolved() && isTrue()) {
            changeOptional();
            changeLogicalOption();

            int[][] tryed = values.clone();
            changeLogicalOption();
            changeLogicalOption();

            if (Arrays.deepEquals(tryed, values)) {
                SudokuClone s = new SudokuClone(this.values);
                this.values = s.tryToSolve(this.values);
            }

            iterations++;
            if (iterations >= MAX_ITERATIONS) throw new SudokuCanNotSolvedExpection("Sudoku is not solvable");
        }

    }

    int[] findSuitableNumbers(int line, int column) {//3,2
        ArrayList<Integer> counts = new ArrayList<>();
        for (int i = 0; i < sudokuSize; i++) {
            if ((!counts.contains(values[line][i])) && values[line][i] != 0) counts.add(values[line][i]);
            if ((!counts.contains(values[i][column])) && values[i][column] != 0) counts.add(values[i][column]);
        }

        int[] suitable = new int[0];
        for (int i = 1; i <= sudokuSize; i++) {
            if (!counts.contains(i)) {
                int[] s2 = suitable.clone();
                suitable = new int[s2.length + 1];
                suitable[suitable.length - 1] = i;
                System.arraycopy(s2, 0, suitable, 0, s2.length);
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

    public void changeOptional() {
        findBestSuitableNumber();
        boolean a = false;
        for (int i = 0; i < sudokuSize; i++)
            for (int j = 0; j < sudokuSize; j++)
                if (values[i][j] == 0)
                    if (suitables[i][j].length == 1) {
                        change(i, j, suitables[i][j][0]);
                        a = true;
                    }
        if (a) {
            write();
            changeOptional();
            findBestSuitableNumber();
        }

    }

    /* void changeLogicalOption() {
        findBestSuitableNumber();
        boolean changeMade = false;
        ArrayList<int[]> arrays = new ArrayList<>();

        for (int i = 0; i < sudokuSize; i++) {
            for (int j = 0; j < sudokuSize; j++) {
                if (suitables[i][j].length > 0) {
                    arrays.add(new int[]{i, j});
                }
            }
        }

        for (int[] cell : arrays) {
            int row = cell[0];
            int col = cell[1];
            int[] suitablesNumbers = suitables[row][col];

            for (int num : suitablesNumbers) {

                boolean uniqueInRow = true;
                boolean uniqueInCol = true;

                LOOP1:
                for (int i = 0; i < values[row].length; i++) {
                    if (i != col && values[row][i] == 0)
                        for (int n : suitables[row][i])
                            if (n == num) {
                                uniqueInRow = false;
                                break LOOP1;
                            }
                }
                LOOP2:
                for (int i = 0; i < values[col].length; i++) {
                    if (i != row && values[i][col] == 0)
                        for (int n : suitables[i][col])
                            if (n == num) {
                                uniqueInCol = false;
                                break LOOP2;
                            }
                }
                if (uniqueInRow && uniqueInCol) {
                    change(row, col, num);
                    changeMade = true;
                    break;
                }
            }
        }
        if (changeMade) {
            write();
            changeOptional();
        }

        findBestSuitableNumber();
    }

     */

    void changeLogicalOption() {
        findBestSuitableNumber();

    }

    private void change(int line, int column, int num) {
        values[line][column] = num;
    }

    boolean isSolved() {
        for (int[] i : values)
            for (int j : i)
                if (j == 0) return false;

        return true;
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
                if ((values[j][i] != 0) && a.contains(values[i][j])) return false;
                else a.add(values[i][j]);
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
                for (; count < 5; count++)
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

