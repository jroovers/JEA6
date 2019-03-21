package persistence.memory;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import model.PersonGroup;
import persistence.Memory;
import persistence.RoleDao;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
@Memory
public class RoleDaoCollectionImpl extends BaseDaoCollection<PersonGroup> implements RoleDao {

    private Long counter;

    public RoleDaoCollectionImpl() {
        super();
        counter = 0l;
    }

    @Override
    public void save(PersonGroup role) {
        if (getObjectById(role.getId()) != null) {
            throw new EntityExistsException();
        }
        counter++;
        role.setId(counter);
        getObjectStorage().put(role.getId(), role);
    }

    @Override
    public PersonGroup getById(Long id) {
        return getObjectById(id);
    }

    @Override
    public void update(PersonGroup role) {
        if (getObjectById(role.getId()) == null) {
            throw new IllegalArgumentException();
        }
        getObjectStorage().put(role.getId(), role);
    }

    @Override
    public void delete(PersonGroup role) {
        getObjectStorage().remove(role.getId());
    }

}
