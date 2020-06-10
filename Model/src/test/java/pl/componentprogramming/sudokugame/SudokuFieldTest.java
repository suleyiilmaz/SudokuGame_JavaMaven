/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.componentprogramming.sudokugame;

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
public class SudokuFieldTest {

    public SudokuFieldTest() {
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
     * Test of getFieldValue method, of class SudokuField.
     */
    @Test
    public void testSetGetFieldValue() {
        SudokuField instance = new SudokuField();
        instance.setFieldValue(5);
        assertEquals(true, instance.getFieldValue() == 5);
    }

    /**
     * Test of toString method, of class SudokuField.
     */
    @Test
    public void testToString() {
        SudokuField instance = new SudokuField();
        instance.setFieldValue(5);
        assertEquals(true, instance.toString().equals("5"));
    }

    /**
     * Test of equals method, of class SudokuField.
     */
    @Test
    public void testEquals() {
        SudokuField instance = new SudokuField();
        instance.setFieldValue(5);
        SudokuField instance2 = null;
        SudokuBoard sudokuBoard = new SudokuBoard();
        assertEquals(false, instance.equals(instance2));
        assertEquals(false, instance.equals(sudokuBoard));
        assertEquals(true, instance.equals(instance));
        instance2 = new SudokuField();
        instance2.setFieldValue(5);
        assertEquals(true, instance.equals(instance2));
    }

    /**
     * Test of hashCode method, of class SudokuField.
     */
    @Test
    public void testHashCode() {
        SudokuField instance = new SudokuField();
        instance.setFieldValue(5);
        SudokuField instance2 = new SudokuField();
        instance2.setFieldValue(5);
        assertEquals(true, instance.hashCode()==instance2.hashCode());
    }

    /**
     * Test of compareTo method, of class SudokuField.
     */
    @Test
    public void testCompareTo() {
       SudokuField instance = new SudokuField();
       instance.setFieldValue(5);
       SudokuField instance2 = new SudokuField();
       instance2.setFieldValue(6);
       SudokuField instance3 = new SudokuField();
       instance3.setFieldValue(4);
       SudokuField instance4 = new SudokuField();
       instance4.setFieldValue(5);
       assertEquals(0,instance.compareTo(instance4));
       assertEquals(1,instance.compareTo(instance3));
       assertEquals(-1,instance.compareTo(instance2));
    }

}
