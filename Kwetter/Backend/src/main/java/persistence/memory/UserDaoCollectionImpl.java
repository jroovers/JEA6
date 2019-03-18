package persistence.memory;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import model.User;
import persistence.Memory;
import persistence.UserDao;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
@Memory
public class UserDaoCollectionImpl extends BaseDaoCollection<User> implements UserDao {

    private Long counter;

    public UserDaoCollectionImpl() {
        super();
        counter = 0l;
    }

    @Override
    public void save(User user) {
        if (getObjectById(user.getId()) != null) {
            throw new EntityExistsException();
        }
        counter++;
        user.setId(counter);
        getObjectStorage().put(user.getId(), user);
    }

    @Override
    public User getById(Long id) {
        return getObjectById(id);
    }

    @Override
    public void update(User user) {
        if (getObjectById(user.getId()) == null) {
            throw new IllegalArgumentException();
        }
        getObjectStorage().put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        getObjectStorage().remove(user.getId());
    }

    @Override
    public User getByUsername(String username) {
        List<User> users = getObjectStorage().values().stream()
                .filter(user -> user.getUsername().toLowerCase().equals(username.toLowerCase()))
                .collect(Collectors.toList());
        if (users.size() != 1) {
            return null;
        } else {
            return users.get(0);
        }
    }

}
