package persistence.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import persistence.IBaseDao;

/**
 *
 * @author Jeroen Roovers
 */
public abstract class BaseDaoCollection<T> implements IBaseDao<T> {

    private HashMap<Integer, T> objects;

    public BaseDaoCollection() {
        objects = new HashMap<>();
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<T>(objects.values());
    }

    protected T getObjectById(Integer id) {
        return objects.get(id);
    }

    protected Map<Integer, T> getObjectStorage() {
        return objects;
    }
}
