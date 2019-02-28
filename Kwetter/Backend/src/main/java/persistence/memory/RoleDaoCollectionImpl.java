package persistence.memory;

import javax.persistence.EntityExistsException;
import model.Role;
import persistence.RoleDao;

/**
 *
 * @author Jeroen Roovers
 */
public class RoleDaoCollectionImpl extends BaseDaoCollection<Role> implements RoleDao {

    @Override
    public void save(Role role) {
        if (getObjectById(role.getId()) != null) {
            throw new EntityExistsException();
        }
        getObjectStorage().put(role.getId(), role);
    }

    @Override
    public Role get(Integer id) {
        return getObjectById(id);
    }

    @Override
    public void update(Role role) {
        if (getObjectById(role.getId()) == null) {
            throw new IllegalArgumentException();
        }
        getObjectStorage().put(role.getId(), role);
    }

    @Override
    public void delete(Role role) {
        getObjectStorage().remove(role.getId());
    }

}
