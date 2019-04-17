package domain.dao.impl.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import domain.dao.IBaseDao;

/**
 *
 * @author Jeroen Roovers
 */
public abstract class BaseDaoCollection<T> implements IBaseDao<T> {

    private HashMap<Long, T> objects;

    public BaseDaoCollection() {
        objects = new HashMap<>();
    }

    @Override
    public void flush() {
        System.out.println("Flush called on memory DAO. Ignoring.");
    }

    @Override
    public void clearCache() {
        System.out.println("Flush called on memory DAO. Ignoring.");
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<T>(objects.values());
    }

    protected T getObjectById(Long id) {
        return objects.get(id);
    }

    protected Map<Long, T> getObjectStorage() {
        return objects;
    }
}
