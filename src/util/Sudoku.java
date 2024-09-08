package util;

import java.util.ArrayList;
import java.util.Arrays;

import sudokuException.InvalidNumberException;
import sudokuException.NotValidSudokuException;
import sudokuException.SudokuCanNotSolvedException;

public class Sudoku {
    private int[][] values; // Sudoku grid
    protected int sudokuSize; // Size of the grid
    private int[][] solved; // Solved Sudoku
    protected int[][][] suitables = new int[9][9][0]; // Possible values for each cell
    private static final int MAX_ITERATIONS = 2; // Iteration limit


    Sudoku(int[][] values) {
        this.values = values;
        try {
            // Validate the initial Sudoku grid
            check();
        } catch (NotValidSudokuException | InvalidNumberException exception) {
            // Print the exception message and exit if validation fails
            System.err.println(exception.getMessage());
            System.exit(0);
        }
        sudokuSize = values.length;
    }


    public void solve() {
        int iterations = 0;

        // Continue solving the Sudoku until it is solved
        while (!isSolved(values)) {
            // Update the grid with single option cells
            updateWithSingleOptions();

            // Apply logical moves to reduce possibilities
            applyLogicalMove();

            // Cloning
            int[][] copiedArray = sudokuClone(values);

            // Apply logical moves again to refine the grid
            applyLogicalMove();

            // If no progress was made, attempt to solve the Sudoku using a backtracking approach
            if (Arrays.deepEquals(copiedArray, values) && !isSolved(values)) {
                tryToSolve(values);
                values = solved;
            }

            // Increment the iteration counter
            iterations++;

            // If the maximum number of iterations is reached, report that the Sudoku cannot be solved
            if (iterations == MAX_ITERATIONS) {
                write();
                throw new SudokuCanNotSolvedException("Sudoku is not solvable");
            }
        }

        // Print a success message if the Sudoku is solved
        System.out.println("\033[0;32m" + "Sudoku has been solved successfully!" + "\033[0m");
    }

    private boolean tryToSolve(int[][] array) {
        // Iterate through all cells in the Sudoku grid
        for (int i = 0; i < sudokuSize; i++) {
            for (int j = 0; j < sudokuSize; j++) {
                // If the cell is empty
                if (array[i][j] == 0) {

                    // Find suitable numbers for the current cell
                    int[] suitablesForSpecialLocation = findSuitableNumbersForSpecialArea(i, j);

                    // If no suitable numbers are found, the Sudoku cannot be solved with the current state
                    if (suitablesForSpecialLocation.length == 0) return false;

                    // Try each suitable number
                    for (int k : suitablesForSpecialLocation) {
                        array[i][j] = k;

                        // Check if the current state of the Sudoku is valid
                        if (isTrue(array)) {

                            // If the Sudoku is solved, store the solved state and return true
                            if (isSolved(array)) {
                                solved = sudokuClone(array);
                                return true;
                            }

                            // Recursively try to solve the Sudoku with the current state
                            if (tryToSolve(array)) {
                                return true;
                            }

                        }

                        // If placing the number doesn't lead to a solution, reset the cell and try the next number
                        array[i][j] = 0;
                    }

                    // If no number fits, return false
                    return false;
                }
            }
        }

        // If all cells are filled correctly, the Sudoku is solved
        return true;
    }


    protected int[] findSuitableNumbersForSpecialArea(int line, int column) {
        // Create a list to hold numbers already present in the same line and column
        ArrayList<Integer> counts = new ArrayList<>();

        // Check the current line for existing values
        for (int i = 0; i < sudokuSize; i++) {
            if (!counts.contains(values[line][i]) && values[line][i] != 0) {
                counts.add(values[line][i]);
            }
        }

        // Check the current column for existing values
        for (int i = 0; i < sudokuSize; i++) {
            if (!counts.contains(values[i][column]) && values[i][column] != 0) {
                counts.add(values[i][column]);
            }
        }

        // Determine the suitable numbers that are not present in the line and column
        int[] suitable = new int[0];
        for (int i = 1; i <= sudokuSize; i++) {
            if (!counts.contains(i)) {
                // Create a new array with the additional suitable number
                int[] s2 = suitable.clone();
                suitable = new int[s2.length + 1];
                suitable[suitable.length - 1] = i;
                for (int j = 0; j < s2.length; j++) {
                    suitable[j] = s2[j];
                }
            }
        }

        return suitable;
    }


    protected void findSuitableNumbersForSpecialArea() {
        // Initialize the 3D array to store suitable numbers for each cell
        suitables = new int[sudokuSize][sudokuSize][0];

        // Iterate through all cells in the Sudoku grid
        for (int i = 0; i < sudokuSize; i++) {
            for (int b = 0; b < sudokuSize; b++) {
                if (values[i][b] == 0) {
                    // Find suitable numbers for this cell
                    suitables[i][b] = findSuitableNumbersForSpecialArea(i, b);
                }
            }
        }
    }


    protected void updateWithSingleOptions() {
        // Find the best suitable number
        findSuitableNumbersForSpecialArea();
        boolean madeChange = false;

        // Iterate through all cells
        for (int i = 0; i < sudokuSize; i++) {
            for (int j = 0; j < sudokuSize; j++) {
                // If the cell is empty and has only one suitable option
                if (values[i][j] == 0) {
                    if (suitables[i][j].length == 1) {
                        // Update the cell with the suitable option
                        change(i, j, suitables[i][j][0]);
                        madeChange = true;
                    }
                }
            }
        }

        // If any change was made, call the function again
        if (madeChange) {
            updateWithSingleOptions();
        }
    }


    protected void applyLogicalMove() { // Apply a logical move to solve Sudoku
        findSuitableNumbersForSpecialArea();

        for (int row = 0; row < sudokuSize; row++) {
            for (int column = 0; column < sudokuSize; column++) {

                if (values[row][column] == 0) { // If the cell is empty
                    // Try each candidate number for this cell
                    LOOP1:
                    for (int k : suitables[row][column]) {
                        // Check if the candidate number is valid in the row
                        for (int z = 0; z < sudokuSize; z++) {
                            if (z != column) { // Skip the current column
                                for (int a : suitables[row][z]) {
                                    if (k == a) {
                                        continue LOOP1; // Skip if number already exists in the row
                                    }
                                }
                            }
                            // Check if the candidate number is valid in the column
                            if (z != row) { // Skip the current row
                                for (int a : suitables[z][column]) {
                                    if (k == a) {
                                        continue LOOP1; // Skip if number already exists in the column
                                    }
                                }
                            }
                        }
                        // If the number is valid, place it in the cell
                        change(row, column, k);
                        return; // Exit after making a move
                    }
                }
            }
        }
    }

    protected void change(int line, int column, int num) {
        if (this.values[line][column] == 0) this.values[line][column] = num; // Set the number if the cell is empty
    }

    protected boolean isSolved(int[][] values) {
        for (int[] row : values)
            for (int cell : row)
                if (cell == 0) return false; // Check if there are any empty cells
        return isTrue(values); // Check if the Sudoku is valid
    }

    protected boolean isTrue(int[][] values) {
        // Check rows and columns
        return areColumnsValid(values) && areRowsValid(values);
    }

    private boolean areRowsValid(int[][] values) {
        // Check each row for duplicates
        for (int i = 0; i < sudokuSize; i++) {
            ArrayList<Integer> seen = new ArrayList<>();
            for (int j = 0; j < sudokuSize; j++) {
                if (values[i][j] != 0 && seen.contains(values[i][j])) {
                    return false; // Duplicate found
                }
                seen.add(values[i][j]);
            }
        }
        return true;
    }

    private boolean areColumnsValid(int[][] values) {
        // Check each column for duplicates
        for (int i = 0; i < sudokuSize; i++) {
            ArrayList<Integer> seen = new ArrayList<>();
            for (int j = 0; j < sudokuSize; j++) {
                if (values[j][i] != 0 && seen.contains(values[j][i])) {
                    return false; // Duplicate found
                }
                seen.add(values[j][i]);
            }
        }
        return true;
    }

    public void writeSuitables() { // Print suitable numbers for empty cells
        findSuitableNumbersForSpecialArea(); // Find the best numbers for empty cells
        for (int[][] suitable : suitables) {
            for (int[] row : suitable) {
                System.out.print("["); // Start of row
                int count = 0;
                for (int num : row) {
                    ++count;
                    System.out.print(num);
                }
                System.out.print("]"); // End of row
                for (; count < 7; count++) // Align columns
                    System.out.print(" ");
            }
            System.out.println(); // New line after each row
        }
        System.out.println(); // Extra line for spacing
    }

    public void write() {  // Print the full Sudoku table
        for (int[] row : values) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println(); // New line after each row
        }
        System.out.println(); // Extra line for spacing
    }

    protected int[][] sudokuClone(int[][] values) {
        int[][] copy = new int[values.length][values[0].length]; // Create a copy of the array
        for (int i = 0; i < values.length; i++) {
            System.arraycopy(values[i], 0, copy[i], 0, values[i].length); // Copy each row
        }
        return copy;
    }


    //A method that checks the exceptions in Sudoku
    private void check() throws NotValidSudokuException, InvalidNumberException {
        // Ensure all rows have the same length
        for (int i = 0; i < values.length; i++) {
            if (values.length != values[i].length) {
                throw new NotValidSudokuException("Shape of Sudoku is not valid");
            }
        }
        // Check if all numbers are valid (0-9)
        ArrayList<Integer> legalNumbers = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            legalNumbers.add(i);
        }
        for (int[] row : values) {
            for (int num : row) {
                if (!legalNumbers.contains(num)) {
                    throw new InvalidNumberException("An invalid number has been entered into Sudoku.");
                }
            }
        }
    }

}

