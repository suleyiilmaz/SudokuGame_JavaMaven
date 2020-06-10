package pl.componentprogramming.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.componentprogramming.sudokugame.SudokuBoard;

/**
 * Read and write a SudokuBoard in a file.
 *
 * @author Erwan
 */
public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private File file;

    public FileSudokuBoardDao(String fileName) {
        this.file = new File(fileName);
    }

    /**
     * Read a file and return a SudokuBoard.
     *
     * @return SudokuBoard
     */
    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        try (
                FileInputStream fis = new FileInputStream(this.file);
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            sudokuBoard = (SudokuBoard) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileSudokuBoardDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FileSudokuBoardDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sudokuBoard;
    }

    /**
     * Write a file and return a SudokuBoard.
     *
     * @param obj SudokuBoard
     */
    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fos = new FileOutputStream(this.file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(obj);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileSudokuBoardDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileSudokuBoardDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() throws DaoStreamException {
        throw new DaoStreamException("Error during uses of the ressource.");
    }

}
