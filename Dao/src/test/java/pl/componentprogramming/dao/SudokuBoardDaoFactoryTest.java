/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.componentprogramming.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.componentprogramming.sudokugame.SudokuBoard;
import pl.componentprogramming.sudokugame.BacktrackingSudokuSolver;

/**
 *
 * @author Erwan
 */
public class SudokuBoardDaoFactoryTest {

    public SudokuBoardDaoFactoryTest() {
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
     * Test of getFileDao method, of class SudokuBoardDaoFactory.
     */
    @Test
    public void testGetFileDao() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.initialiseRandomRow();
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);
        SudokuBoardDaoFactory sBDF = new SudokuBoardDaoFactory();
        FileSudokuBoardDao dao = (FileSudokuBoardDao) sBDF.getFileDao("C:/Users/Public/Documents/testSudokuBoard.txt");
        dao.write(sudokuBoard);
        SudokuBoard sudokuBoard2 = new SudokuBoard();
        sudokuBoard2 = dao.read();
        assertEquals(true, sudokuBoard.equals(sudokuBoard2));
    }

}
