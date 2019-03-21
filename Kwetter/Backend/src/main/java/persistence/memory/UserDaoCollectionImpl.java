package persistence.memory;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import model.Person;
import persistence.Memory;
import persistence.UserDao;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
@Memory
public class UserDaoCollectionImpl extends BaseDaoCollection<Person> implements UserDao {

    private Long counter;

    public UserDaoCollectionImpl() {
        super();
        counter = 0l;
    }

    @Override
    public void save(Person user) {
        if (getObjectById(user.getId()) != null) {
            throw new EntityExistsException();
        }
        counter++;
        user.setId(counter);
        getObjectStorage().put(user.getId(), user);
    }

    @Override
    public Person getById(Long id) {
        return getObjectById(id);
    }

    @Override
    public void update(Person user) {
        if (getObjectById(user.getId()) == null) {
            throw new IllegalArgumentException();
        }
        getObjectStorage().put(user.getId(), user);
    }

    @Override
    public void delete(Person user) {
        getObjectStorage().remove(user.getId());
    }

    @Override
    public Person getByUsername(String username) {
        List<Person> users = getObjectStorage().values().stream()
                .filter(user -> user.getUsername().toLowerCase().equals(username.toLowerCase()))
                .collect(Collectors.toList());
        if (users.size() != 1) {
            return null;
        } else {
            return users.get(0);
        }
    }

}
