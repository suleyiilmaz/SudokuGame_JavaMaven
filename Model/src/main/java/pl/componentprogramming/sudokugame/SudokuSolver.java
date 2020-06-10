package pl.componentprogramming.sudokugame;

/**
 * Solve the Sudoku.
 *
 * @author Asus
 */
public interface SudokuSolver {

    /**
     * Solve the Sudoku by filling blanks with correct digits.
     *
     * @param board SudokuBoard
     * @return
     */
    public boolean solve(SudokuBoard board);

}
