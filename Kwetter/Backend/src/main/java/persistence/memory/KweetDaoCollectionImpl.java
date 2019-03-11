package persistence.memory;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import model.Kweet;
import persistence.KweetDao;
import persistence.Memory;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
@Memory
public class KweetDaoCollectionImpl extends BaseDaoCollection<Kweet> implements KweetDao {

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
