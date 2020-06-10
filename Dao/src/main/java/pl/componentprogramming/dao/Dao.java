package pl.componentprogramming.dao;

/**
 * Read and write in a file.
 *
 * @author Erwan
 * @param <T> Object
 */
public interface Dao<T> {

    public T read();

    public void write(T obj);

}
