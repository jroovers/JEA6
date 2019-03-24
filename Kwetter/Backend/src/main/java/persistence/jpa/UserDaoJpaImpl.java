package persistence.jpa;

import java.util.List;
import javax.ejb.Stateless;
import model.User;
import persistence.qualifiers.JPA;
import persistence.UserDao;

@Stateless
@JPA
public class UserDaoJpaImpl extends BaseDaoJpa<User> implements UserDao {

    public UserDaoJpaImpl() {
        super.entityClass = User.class;

    }

    @Override
    public User getByUsername(String username) {
        List<User> list = getEntityManager().createNamedQuery("user.FindByUsername").setParameter("username", username).getResultList();
        if (list.isEmpty() || list.size() > 1) {
            return null;
        } else {
            return list.get(0);
        }
    }

}
