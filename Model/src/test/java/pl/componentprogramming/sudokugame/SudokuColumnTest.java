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
public class SudokuColumnTest {
    
    public SudokuColumnTest() {
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
     * Test of toString method, of class SudokuColumn.
     */
    @Test
    public void testToString() {
        String res="";
        List<SudokuField> group = Arrays.asList(new SudokuField[9]);
        for(int i =0;i<9;i++){
            SudokuField field = new SudokuField();
            field.setFieldValue(i);
            group.set(i,field);
            res+=i+"\n";
        }
        SudokuColumn sudokuColumn = new SudokuColumn(group);
        assertEquals(res,sudokuColumn.toString());
    }
    
}
