package pl.componentprogramming.sudokugame;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Erwan
 */
public class SudokuBoardTest {

    public SudokuBoardTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of set method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSet() {
        SudokuBoard sb1 = new SudokuBoard();
        assertEquals(true, sb1.set(1, 1, 2));
        assertEquals(false, sb1.set(1, 2, 2));
    }

    /**
     * Test of get method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGet() {
        SudokuBoard sb1 = new SudokuBoard();
        sb1.set(1, 1, 2);
        assertEquals(2, sb1.get(1, 1));
    }

    /**
     * Test of initialiseRandomRow method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testInitialiseRandomRow() {
        SudokuBoard sb1 = new SudokuBoard();
        sb1.initialiseRandomRow();
        SudokuBoard sb2 = new SudokuBoard();
        sb2.initialiseRandomRow();
        assertEquals(false, sb1.equals(sb2));
        boolean res = false;
        int i = 0;
        while (i < 9 && !res) {
            int j = 0;
            while (j < 9 && !res) {
                if (sb1.get(i, j) != 0) {
                    res = true;
                }
                j++;
            }
            i++;
        }
        assertEquals(true, res);
    }

    /**
     * Test of toString method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testToString() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.initialiseRandomRow();
        String res = "";
        for (int i = 0; i < 9; i++) {
            String row = "";
            for (int j = 0; j < 9; j++) {
                row += " " + sudokuBoard.get(i, j) + " ";
            }
            res += "\n" + row;
        }
        assertEquals(true, sudokuBoard.toString().equals(res));
    }

    /**
     * Test of getRow method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetRow() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        List<SudokuField> group = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            SudokuField sudokuField = new SudokuField();
            sudokuField.setFieldValue(9 - i);
            group.set(i, sudokuField);
            sudokuBoard.set(0, i, 9 - i);
        }
        SudokuRow row = sudokuBoard.getRow(0);
        SudokuRow row2 = new SudokuRow(group);
        assertEquals(true, row.equals(row2));
    }

    /**
     * Test of getColumn method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetColumn() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        List<SudokuField> group = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            SudokuField sudokuField = new SudokuField();
            sudokuField.setFieldValue(9 - i);
            group.set(i, sudokuField);
            sudokuBoard.set(i, 0, 9 - i);
        }
        SudokuColumn col = sudokuBoard.getColumn(0);
        SudokuColumn col2 = new SudokuColumn(group);
        assertEquals(true, col.equals(col2));
    }

    /**
     * Test of getBox method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetBox() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        List<SudokuField> group = Arrays.asList(new SudokuField[9]);
        int value = 9;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SudokuField sudokuField = new SudokuField();
                sudokuField.setFieldValue(value);
                group.set(9 - value, sudokuField);
                sudokuBoard.set(i, j, value);
                value--;
            }
        }
        SudokuBox box = sudokuBoard.getBox(0, 0);
        SudokuBox box2 = new SudokuBox(group);
        assertEquals(true, box.equals(box2));
    }

    /**
     * Test of equals method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testEquals() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.initialiseRandomRow();
        SudokuBoard sudokuBoard2 = null;
        SudokuField sudokuField = new SudokuField();
        assertEquals(false, sudokuBoard.equals(sudokuBoard2));
        assertEquals(true, sudokuBoard.equals(sudokuBoard));
        assertEquals(false, sudokuBoard.equals(sudokuField));
        sudokuBoard2 = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard2.set(i, j, sudokuBoard.get(i, j));
            }
        }
        assertEquals(true, sudokuBoard.equals(sudokuBoard2));
    }

    /**
     * Test of hashCode method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testHasCode() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.initialiseRandomRow();
        SudokuBoard sudokuBoard2 = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard2.set(i, j, sudokuBoard.get(i, j));
            }
        }
        assertEquals(true, sudokuBoard.hashCode() == sudokuBoard2.hashCode());
    }
}
