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
public class BacktrackingSudokuSolverTest {

    public BacktrackingSudokuSolverTest() {
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
     * Test of solve method, of class BacktrackingSudokuSolver.
     */
    @Test
    public void testSolve() {
        SudokuBoard sb = new SudokuBoard();
        sb.initialiseRandomRow();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sb);
        int i = 0;
        boolean ok = true;
        while (i < 9 && ok) {
            int j = 0;
            while (j < 9 && ok) {
                int digit = sb.get(i, j);
                sb.set(i, j, 0);
                ok = sb.set(i, j, digit);
                j++;
            }
            i++;
        }
        assertEquals(true, ok);
        //fail("Digits are not corrects");
    }

    /**
     * Test of solve method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSolveDifferents() {
        SudokuBoard sb1 = new SudokuBoard();
        sb1.initialiseRandomRow();
        SudokuBoard sb2 = new SudokuBoard();
        sb2.initialiseRandomRow();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sb1);
        solver.solve(sb2);

        int i = 0;
        boolean different = false;
        while (i < 9 && !different) {
            int j = 0;
            while (j < 9 && !different) {
                if (sb1.get(i, j) != sb2.get(i, j)) {
                    different = true;
                }
                j++;
            }
            i++;
        }
        assertEquals(true, different);
        //fail("Two subsequent calls of fillBoard generates the same digits layout on the board");
    }

}
