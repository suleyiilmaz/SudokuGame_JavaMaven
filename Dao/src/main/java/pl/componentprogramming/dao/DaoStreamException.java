package pl.componentprogramming.dao;

/**
 * Thrown when exception during reading or writing in the FileSudokuBoardDao.
 *
 * @author Erwan
 */
public class DaoStreamException extends Exception {

    public DaoStreamException(String message) {
        super(message);
    }
}
