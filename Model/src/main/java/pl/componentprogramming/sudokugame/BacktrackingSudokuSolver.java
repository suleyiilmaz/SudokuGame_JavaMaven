package pl.componentprogramming.sudokugame;

public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public boolean solve(SudokuBoard board) {
        return solveBacktracking(board, 9);
    }

    /**
     * Solve the sudoku by filling blanks with correct digits.
     *
     * @param board SudokuBoard to solve
     * @param n Value
     * @return true if solved
     */
    private boolean solveBacktracking(SudokuBoard board, int n) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        int i = 0;
        while (i < n && isEmpty) {
            int j = 0;
            while (j < n && isEmpty) {
                if (board.get(i, j) == 0) {
                    row = i;
                    col = j;

                    isEmpty = false;
                }
                j++;
            }
            i++;
        }

        if (isEmpty) {
            return true;
        }

        for (int num = 1; num <= n; num++) {
            if (board.set(row, col, num)) {
                if (solveBacktracking(board, n)) {
                    return true;
                } else {
                    board.set(row, col, 0);
                }
            }
        }
        return false;
    }
}
