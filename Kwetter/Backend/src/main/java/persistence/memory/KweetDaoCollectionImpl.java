package persistence.memory;

import javax.persistence.EntityExistsException;
import model.Kweet;
import persistence.KweetDao;

/**
 *
 * @author Jeroen Roovers
 */
public class KweetDaoCollectionImpl extends BaseDaoCollectionImpl<Kweet> implements KweetDao {

    public KweetDaoCollectionImpl() {
        super();
    }

    @Override
    public void save(Kweet kweet) {
        if (getObjectById(kweet.getId()) != null) {
            throw new EntityExistsException();
        }
        getObjectStorage().put(kweet.getId(), kweet);
    }

    @Override
    public Kweet get(Integer id) {
        return getObjectById(id);
    }

    @Override
    public void update(Kweet kweet) {
        if (getObjectById(kweet.getId()) == null) {
            throw new IllegalArgumentException();
        }
        getObjectStorage().put(kweet.getId(), kweet);
    }

    @Override
    public void delete(Kweet kweet) {
        getObjectStorage().remove(kweet.getId());
    }

}
