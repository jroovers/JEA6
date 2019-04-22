package domain.dao.impl.jpa;

import java.util.List;
import javax.ejb.Stateless;
import domain.model.User;
import domain.dao.qualifiers.JPA;
import domain.dao.UserDao;
import domain.model.MutualFriendDTO;
import java.util.ArrayList;

@Stateless
@JPA
public class UserDaoJpaImpl extends BaseDaoJpa<User> implements UserDao {

    private final String SQL_GET_MUTUAL_FRIENDS = "select u.id, u.biography, u.location, u.name, u.username, u.website, count(f.follower) as nr "
            + "from followers f, user u where f.follower != ? and f.following in "
            + "(select f.follower from followers f where f.following = ?) "
            + "and f.follower not in "
            + "(select f.follower from followers f where f.following = ?) "
            + "and u.id = f.follower "
            + "GROUP By f.follower "
            + "ORDER BY nr DESC";

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

    @Override
    public List<MutualFriendDTO> getMutualFriends(String username) {
        User user = this.getByUsername(username);
        if (user == null) {
            return new ArrayList<>();
        }
        Long id = user.getId();
        List<MutualFriendDTO> result = getEntityManager().createNativeQuery(SQL_GET_MUTUAL_FRIENDS, "MutualFriendMapper")
                .setParameter(1, id)
                .setParameter(2, id)
                .setParameter(3, id)
                .getResultList();
        return result;
    }

}
