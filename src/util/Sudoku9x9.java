package util;

import java.util.ArrayList;

// Class for handling 9x9 Sudoku puzzles, extending the base Sudoku class
public class Sudoku9x9 extends Sudoku {
    private int[][] values; // array to store Sudoku grid values

    public Sudoku9x9(int[][] values) {
        super(values);
        this.values = values;
    }

    // Method to change logical options based on Sudoku rules
    @Override
    protected void applyLogicalMove() {
        findSuitableNumbersForSpecialArea(); // Find the best suitable numbers for empty cells
        for (int row = 0; row < sudokuSize; row++) {
            for (int column = 0; column < sudokuSize; column++) {

                if (values[row][column] == 0) // If the cell is empty
                    LOOP1:
                            for (int k : suitables[row][column]) { // Iterate through possible values
                                // Check if value k is already present in the same row
                                for (int z = 0; z < sudokuSize; z++) {
                                    if (z != column)
                                        for (int a : suitables[row][z]) {
                                            if (k == a) continue LOOP1;
                                        }
                                    // Check if value k is already present in the same column
                                    if (z != row)
                                        for (int a : suitables[z][column]) {
                                            if (k == a) continue LOOP1;
                                        }
                                }
                                // Check if value k is already present in the 3x3 block
                                for (int x = (row / 3) * 3; x < (row / 3) * 3 + 3; x++) {
                                    for (int y = (column / 3) * 3; y < (column / 3) * 3 + 3; y++) {
                                        for (int a : suitables[x][y]) if (k == a) continue LOOP1;
                                    }
                                }
                                change(row, column, k); // Update cell with the valid value k
                            }
            }
        }
    }

    // Method to find suitable numbers for a specific cell
    @Override
    protected int[] findSuitableNumbersForSpecialArea(int row, int column) {
        ArrayList<Integer> counts = new ArrayList<>();
        // Check row and column for existing numbers
        for (int i = 0; i < values.length; i++) {
            if ((!counts.contains(values[row][i])) && values[row][i] != 0) counts.add(values[row][i]);
            if ((!counts.contains(values[i][column])) && values[i][column] != 0) counts.add(values[i][column]);
        }

        // Check 3x3 block for existing numbers
        for (int k = (row / 3) * 3; k < (row / 3) * 3 + 3; k++) {
            for (int y = (column / 3) * 3; y < (column / 3) * 3 + 3; y++) {
                if ((!counts.contains(values[k][y])) && values[k][y] != 0)
                    counts.add(values[k][y]);
            }
        }

        // Determine the suitable numbers that are not already present
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

    // Method to find suitable numbers for all empty cells
    @Override
    protected void findSuitableNumbersForSpecialArea() {
        suitables = new int[sudokuSize][sudokuSize][0];
        for (int i = 0; i < sudokuSize; i++)
            for (int j = 0; j < sudokuSize; j++)
                if (values[i][j] == 0) suitables[i][j] = findSuitableNumbersForSpecialArea(i, j);
    }

    // Method to check if the Sudoku grid is valid
    @Override
    protected boolean isTrue(int[][] values) {
        return super.isTrue(values) && are3x3BlocksValid(values);
    }

    // Method to validate the 3x3 blocks in the Sudoku grid
    private boolean are3x3BlocksValid(int[][] values) {
        for (int k = 0; k < 3; k++) {
            for (int y = 0; y < 3; y++) {
                int[][] detail = new int[3][3];
                // Extract the 3x3 block
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        detail[i][j] = values[k * 3 + i][y * 3 + j];
                    }
                }
                // Check for duplicate numbers within the 3x3 block
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
