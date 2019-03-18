package persistence;

import java.util.List;

/**
 *
 * @author Jeroen Roovers
 *
 * Base interface for CRUD operations and common queries
 */
public interface IBaseDao<T> {

    public List<T> getAll();

    public void save(T entity);

    public void update(T entity);

    public void delete(T entity);

    public T getById(Long id);
}
