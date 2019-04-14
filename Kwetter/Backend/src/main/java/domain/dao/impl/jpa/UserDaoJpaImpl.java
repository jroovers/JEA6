package domain.dao.impl.jpa;

import java.util.List;
import javax.ejb.Stateless;
import domain.model.User;
import domain.dao.qualifiers.JPA;
import domain.dao.UserDao;

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
