package persistence.memory;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import model.Role;
import persistence.qualifiers.Memory;
import persistence.RoleDao;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
@Memory
public class RoleDaoCollectionImpl extends BaseDaoCollection<Role> implements RoleDao {

    private Long counter;

    public RoleDaoCollectionImpl() {
        super();
        counter = 0l;
    }

    @Override
    public void save(Role role) {
        if (getObjectById(role.getId()) != null) {
            throw new EntityExistsException();
        }
        counter++;
        role.setId(counter);
        getObjectStorage().put(role.getId(), role);
    }

    @Override
    public Role getById(Long id) {
        return getObjectById(id);
    }

    @Override
    public Role update(Role role) {
        if (getObjectById(role.getId()) == null) {
            throw new IllegalArgumentException();
        }
        getObjectStorage().put(role.getId(), role);
        return getObjectStorage().get(role.getId());
    }

    @Override
    public void delete(Role role) {
        getObjectStorage().remove(role.getId());
    }

    @Override
    public Role getByName(String name) {
        List<Role> roles = getObjectStorage().values().stream()
                .filter(role -> role.getName().toLowerCase().equals(name.toLowerCase()))
                .collect(Collectors.toList());
        if (roles.size() != 1) {
            return null;
        } else {
            return roles.get(0);
        }
    }

}
