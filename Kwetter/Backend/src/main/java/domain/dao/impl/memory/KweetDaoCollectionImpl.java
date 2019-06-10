package domain.dao.impl.memory;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import domain.model.Kweet;
import domain.dao.KweetDao;
import domain.dao.qualifiers.Memory;
import java.util.List;

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
    public Kweet update(Kweet kweet) {
        if (getObjectById(kweet.getId()) == null) {
            throw new IllegalArgumentException();
        }
        getObjectStorage().put(kweet.getId(), kweet);
        return getObjectStorage().get(kweet.getId());
    }

    @Override
    public void delete(Kweet kweet) {
        getObjectStorage().remove(kweet.getId());
    }

    @Override
    public Kweet getById(Long id) {
        return getObjectById(id);
    }

    @Override
    public List<Kweet> getKweetsByUserId(Long id) {
        throw new UnsupportedOperationException("Memory DAO does not support this method: getKweetsByUserId(Long id)");
    }

    @Override
    public List<Kweet> getKweetsForUserByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
