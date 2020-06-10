package pl.componentprogramming.dao;

/**
 * Create instance of Dao for a given file name.
 *
 * @author Erwan
 */
public class SudokuBoardDaoFactory {

    /**
     * Create Dao instance for a given file name.
     *
     * @param fileName Name of the file
     * @return Dao object
     */
    public Dao getFileDao(String fileName) {
        Dao dao = new FileSudokuBoardDao(fileName);
        return dao;
    }
}
