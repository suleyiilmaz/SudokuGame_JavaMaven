/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class SudokuGroupTest {

    public SudokuGroupTest() {
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
     * Test of verify method, of class SudokuGroup.
     */
    @Test
    public void testVerify() {
        SudokuBoard sb1 = new SudokuBoard();
        sb1.set(1, 0, 1);
        sb1.set(1, 1, 2);
        sb1.set(1, 2, 3);
        sb1.set(1, 3, 4);
        sb1.set(1, 4, 5);
        sb1.set(1, 5, 6);
        sb1.set(1, 6, 7);
        sb1.set(1, 7, 8);
        sb1.set(1, 8, 9);
        assertEquals(true, sb1.getRow(1).verify());
        List<SudokuField> group = Arrays.asList(new SudokuField[9]);
        SudokuField field = new SudokuField();
        field.setFieldValue(1);
        for(int i =0;i<9;i++){
            group.set(0,field);
        }
        SudokuRow row = new SudokuRow(group);
        assertEquals(false,row.verify());
    }
    
    /**
     * Test of equals method, of class SudokuGroup.
     */
    @Test
    public void testEquals() {
        List<SudokuField> group = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            SudokuField sudokuField = new SudokuField();
            sudokuField.setFieldValue(9 - i);
            group.set(i, sudokuField);
        }
        SudokuRow sudokuRow = new SudokuRow(group);
        SudokuRow sudokuRow2 = null;
        assertEquals(false,sudokuRow.equals(sudokuRow2));
        assertEquals(true,sudokuRow.equals(sudokuRow));
        SudokuBoard sudoku = new SudokuBoard();
        assertEquals(false,sudokuRow.equals(sudoku));
        sudokuRow2 = new SudokuRow(group);
        assertEquals(true,sudokuRow.equals(sudokuRow2));
    }
    
        /**
     * Test of hashCode method, of class SudokuGroup.
     */
    @Test
    public void testHasCode() {
        List<SudokuField> group = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            SudokuField sudokuField = new SudokuField();
            sudokuField.setFieldValue(9 - i);
            group.set(i, sudokuField);
        }
        SudokuRow sudokuRow = new SudokuRow(group);
        SudokuRow sudokuRow2 = new SudokuRow(group);
        assertEquals(true,sudokuRow.hashCode()==sudokuRow2.hashCode());
    }
}
