package pl.componentprogramming.sudokugame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Store and access to data of a Sudoku Board.
 *
 * @author student
 */
public class SudokuBoard implements Serializable, Cloneable {

    private List<SudokuField> board = Arrays.asList(new SudokuField[81]);

    /**
     * Creating the board.
     */
    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board.set(i * 9 + j, new SudokuField());
            }
        }
    }

    /**
     * Initialise a random row in the board with random numbers.
     */
    public void initialiseRandomRow() {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            digits.add(i);
        }
        Collections.shuffle(digits);
        //System.out.println(digits);
        Random rand = new Random();
        int randomRow = rand.nextInt(9);
        //System.out.println(randomRow);
        for (int j = 0; j < 9; j++) {
            this.board.get(randomRow * 9 + j).setFieldValue(digits.get(j));
        }
    }

    /**
     * Setting a value to a desired row and col.
     *
     * @param row Number of the row
     * @param col Number of the column
     * @param digit Value
     * @return true if the number as been set.
     */
    public boolean set(int row, int col, int digit) {
        boolean res = false;
        if (canBePlacedHere(row, col, digit)) {
            System.out.println(digit);
            this.board.get(row * 9 + col).setFieldValue(digit);
            res = true;
        }
        return res;
    }

    /**
     * Getting a value from a desired row and col.
     *
     * @param row Number of the row
     * @param col Number of the column
     * @return the value at the row and column position.
     */
    public int get(int row, int col) {
        return this.board.get(row * 9 + col).getFieldValue();
    }

    /**
     * Return true if the digit is already on the specified row.
     *
     * @param row Number of the row
     * @param digit Value
     * @return
     */
    private boolean onRow(int row, int digit) {
        int i = 0;
        boolean res = false;
        while (i < 9 && !res) {
            if (this.get(row, i) == digit) {
                res = true;
            }
            i++;
        }
        return res;
    }

    /**
     * Return true if the digit is already on the specified column.
     *
     * @param col Number of the column
     * @param digit Value
     * @return
     */
    private boolean onColumn(int col, int digit) {
        int i = 0;
        boolean res = false;
        while (i < 9 && !res) {
            if (this.get(i, col) == digit) {
                res = true;
            }
            i++;
        }
        return res;
    }

    /**
     * Return true if the digit is already on the square with the specified row
     * and column.
     *
     * @param row Number of the row
     * @param col Number of the column
     * @param digit Value
     * @return
     */
    private boolean onSquare(int row, int col, int digit) {
        int squareRow = firstNumberOfSquare(row);
        int squareCol = firstNumberOfSquare(col);
        int i = squareRow;
        int j = squareCol;
        boolean res = false;
        while (i < squareRow + 3 && !res) {
            while (j < squareCol + 3 && !res) {
                if (this.get(i, j) == digit) {
                    res = true;
                }
                j++;
            }
            j = squareCol;
            i++;
        }
        return res;
    }

    /**
     * Return the upper left corner of a square.
     *
     * @param number It can be thr number of a row or a column
     * @return
     */
    private int firstNumberOfSquare(int number) {
        int res = 6;
        if (number < 3) {
            res = 0;
        } else if (number < 6) {
            res = 3;
        }
        return res;
    }

    /**
     * Return true if the digits is not on the row, the column and the square.
     *
     * @param row Number of row
     * @param col Number of column
     * @param digit Value
     * @return
     */
    private boolean canBePlacedHere(int row, int col, int digit) {
        boolean res = false;
        if (digit != 0) {
            if (!onRow(row, digit)) {
                if (!onColumn(col, digit)) {
                    if (!onSquare(row, col, digit)) {
                        res = true;
                    }
                }
            }
        } else {
            res = true;
        }
        return res;
    }

    /**
     * Return the board into a String.
     *
     * @return a string of the board
     */
    @Override
    public String toString() {
        String res = "";

        for (int i = 0; i < 9; i++) {
            String row = "";
            for (int j = 0; j < 9; j++) {
                row += " " + this.get(i, j) + " ";
            }
            res += "\n" + row;
        }
        return res;
    }

    /**
     * Check if the board is correct.
     *
     * @return true if it's correct
     */
    private boolean checkBoard() {
        int i = 0;
        boolean ok = true;
        while (i < 9 && ok) {
            int j = 0;
            while (j < 9 && ok) {
                int digit = get(i, j);
                set(i, j, 0);
                ok = set(i, j, digit);
                j++;
            }
            i++;
        }
        return ok;
    }

    /**
     * Return an object SudokuRow of the given row.
     *
     * @param row number
     * @return SudokuRow
     */
    public SudokuRow getRow(int row) {
        List<SudokuField> rowField = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            rowField.set(i, this.board.get(row * 9 + i));
        }
        SudokuRow sudokuRow = new SudokuRow(rowField);
        return sudokuRow;
    }

    /**
     * Return an object SudokuColumn of the given column.
     *
     * @param col number
     * @return SudokuColumn
     */
    public SudokuColumn getColumn(int col) {
        List<SudokuField> colField = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            colField.set(i, this.board.get(i * 9 + col));
        }
        SudokuColumn sudokuCol = new SudokuColumn(colField);
        return sudokuCol;
    }

    /**
     * Return an object SudokuBox of the given row and col.
     *
     * @param row number
     * @param col number
     * @return SudokuBox
     */
    public SudokuBox getBox(int row, int col) {
        List<SudokuField> boxField = Arrays.asList(new SudokuField[9]);
        int squareRow = firstNumberOfSquare(row);
        int squareCol = firstNumberOfSquare(col);
        int pos = 0;
        for (int i = squareRow; i < squareRow + 3; i++) {
            for (int j = squareCol; j < squareCol + 3; j++) {
                boxField.set(pos, this.board.get(i * 9 + j));
                pos++;
            }
        }
        SudokuBox sudokuBox = new SudokuBox(boxField);
        return sudokuBox;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof SudokuBoard)) {
            return false;
        }

        SudokuBoard sudokuBoard = (SudokuBoard) o;

        return new EqualsBuilder()
                .append(board, sudokuBoard.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 45)
                .append(board)
                .toHashCode();
    }
}
