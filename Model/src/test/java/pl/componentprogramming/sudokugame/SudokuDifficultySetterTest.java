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
public class SudokuDifficultySetterTest {

    public SudokuDifficultySetterTest() {
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
     * Test of setDifficulty method, of class SudokuDifficultySetter.
     */
    @Test
    public void testSetDifficulty() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.initialiseRandomRow();
        SudokuBoard sudokuBoard2 = new SudokuBoard();
        sudokuBoard2.initialiseRandomRow();
        SudokuBoard sudokuBoard3 = new SudokuBoard();
        sudokuBoard3.initialiseRandomRow();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        solver.solve(sudokuBoard2);
        solver.solve(sudokuBoard3);
        SudokuDifficultySetter difficulty = new SudokuDifficultySetter();
        difficulty.setDifficulty(sudokuBoard, SudokuDifficulty.EASY);
        difficulty.setDifficulty(sudokuBoard2, SudokuDifficulty.MEDIUM);
        difficulty.setDifficulty(sudokuBoard3, SudokuDifficulty.HARD);
        int numberOfZero = 0;
        int numberOfZero2 = 0;
        int numberOfZero3 = 0;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(sudokuBoard.get(i,j)==0){
                    numberOfZero++;
                }
                if(sudokuBoard2.get(i,j)==0){
                    numberOfZero2++;
                }
                if(sudokuBoard3.get(i,j)==0){
                    numberOfZero3++;
                }
            }
        }
        assertEquals(true, numberOfZero>=25 && numberOfZero<=30);
        assertEquals(true, numberOfZero2>=45 && numberOfZero2<=50);
        assertEquals(true, numberOfZero3>=55 && numberOfZero3<=64);
    }

}
