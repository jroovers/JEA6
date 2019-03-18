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

    private Long counter;

    public KweetDaoCollectionImpl() {
        super();
        counter = 0l;
    }

    @Override
    public void save(Kweet kweet) {
        if (getObjectById(kweet.getId()) != null) {
            throw new EntityExistsException();
        }
        counter++;
        kweet.setId(counter);
        getObjectStorage().put(kweet.getId(), kweet);
    }

    @Override
    public Kweet getById(Long id) {
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
