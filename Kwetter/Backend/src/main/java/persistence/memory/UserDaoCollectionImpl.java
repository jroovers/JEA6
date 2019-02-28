package persistence.memory;

import javax.persistence.EntityExistsException;
import model.User;
import persistence.UserDao;

/**
 *
 * @author Jeroen Roovers
 */
public class UserDaoCollectionImpl extends BaseDaoCollection<User> implements UserDao {

    @Override
    public void save(User user) {
        if (getObjectById(user.getId()) != null) {
            throw new EntityExistsException();
        }
        getObjectStorage().put(user.getId(), user);
    }

    @Override
    public User get(Integer id) {
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

}
