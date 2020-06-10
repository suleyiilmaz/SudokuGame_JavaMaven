package pl.componentprogramming.sudokugame;

import java.util.Random;

/**
 * Change the difficulty of a given Sudoku.
 *
 * @author Erwan
 */
public class SudokuDifficultySetter {

    /**
     * Deleting number in the sudokuboard depending of the difficulty.
     *
     * @param sudokuBoard SudokuBoard
     * @param difficulty SudokuDifficulty
     */
    public void setDifficulty(SudokuBoard sudokuBoard, SudokuDifficulty difficulty) {
        int minNumberOfValuesDeleted = 0;
        int maxNumberOfValuesDeleted = 0;
        switch (difficulty) {
            case EASY:
                minNumberOfValuesDeleted = 25;
                maxNumberOfValuesDeleted = 30;
                break;
            case MEDIUM:
                minNumberOfValuesDeleted = 45;
                maxNumberOfValuesDeleted = 50;
                break;
            case HARD:
                minNumberOfValuesDeleted = 55;
                maxNumberOfValuesDeleted = 64;
                break;
            default:
                minNumberOfValuesDeleted = 25;
                maxNumberOfValuesDeleted = 30;
        }
        int number = 0;
        while (maxNumberOfValuesDeleted >= 1 || number < minNumberOfValuesDeleted) {
            Random rand = new Random();
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (sudokuBoard.get(row, col) != 0) {
                number++;
            }
            sudokuBoard.set(row, col, 0);
            maxNumberOfValuesDeleted--;
        }
    }
}
