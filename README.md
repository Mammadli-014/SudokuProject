SUDOKU SOLVER PROJECT

* Overview

This project is a sophisticated Sudoku solver implemented in Java. 
It demonstrates the use of various design patterns and algorithms to efficiently 
solve Sudoku puzzles of different difficulty levels. The project incorporates:

• Factory Pattern : To create instances of different Sudoku classes based on the puzzle size.
• Backtracking Algorithm :To handle more complex Sudoku puzzles by exploring potential solutions.
• Logical Deduction Techniques:  To simplify the Sudoku grid and make logical moves for faster solutions.

* Features

• 9x9 Sudoku Solver: Specifically designed for 9x9 Sudoku puzzles, with advanced logical deduction techniques.
• Custom Exception Handling: Includes custom exceptions for handling invalid inputs and unsolvable puzzles.
• Dynamic Sudoku Class Creation: Utilizes the Factory Pattern to handle different Sudoku sizes.

* Algorithms and Techniques

• Backtracking Algorithm : A recursive approach used to explore all possible solutions for a Sudoku puzzle, ensuring all constraints are satisfied.
• Logical Moves : Deductive techniques applied to identify suitable numbers for empty cells, including:
        • Row, Column, and Block Validation: Ensures that each number appears exactly once per row, column, and 3x3 block.
        • Single Option Update: Identifies cells with only one possible value and updates them accordingly.

* Code Structure
•  Sudoku Class: The base class handling common Sudoku functionalities and solving methods.
•  Sudoku9x9 Class: Extends the Sudoku class with specific methods and validations for 9x9 Sudoku puzzles.
•  SudokuFactory Class: Implements the Factory Pattern to create appropriate Sudoku instances based on the input grid.
•  Custom Exceptions: SudokuCanNotSolvedException, NotValidSudokuException, and InvalidNumberException for robust error handling.


Feel free to contribute to this project by submitting issues, suggestions, or improvements.
