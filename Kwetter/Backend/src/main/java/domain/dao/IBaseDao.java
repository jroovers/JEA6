package domain.dao;

import java.util.List;

/**
 *
 * @author Jeroen Roovers
 *
 * Base interface for CRUD operations and common queries
 */
public interface IBaseDao<T> {

    public void flush();

    public void clearCache();

    public List<T> getAll();

    public void save(T entity);

    public T update(T entity);

    public void delete(T entity);

    public T getById(Long id);
}
