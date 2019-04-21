package domain.dao.impl.jpa;

import javax.ejb.Stateless;
import domain.model.Kweet;
import domain.dao.qualifiers.JPA;
import domain.dao.KweetDao;
import java.util.List;

@Stateless
@JPA
public class KweetDaoJpaImpl extends BaseDaoJpa<Kweet> implements KweetDao {

    private final String SQL_KWEETS_FOR_USER = "SELECT k.* FROM kweet k, followers f, user u WHERE k.author_id = f.follower AND f.following = u.id AND u.username = ? UNION SELECT  k.* FROM kweet k, user u WHERE k.author_id = u.id AND u.username = ? ORDER BY createdTime DESC";

    public KweetDaoJpaImpl() {
        super.entityClass = Kweet.class;
    }

    @Override
    public List<Kweet> getKweetsByUserId(Long id) {
        List<Kweet> result = getEntityManager().createNamedQuery("kweet.getByUserId", Kweet.class).setParameter("userId", id).getResultList();
        return result;
    }

    @Override
    public List<Kweet> getAll() {
        List<Kweet> result = getEntityManager().createNamedQuery("kweet.getAll", Kweet.class).getResultList();
        return result;
    }

    @Override
    public List<Kweet> getKweetsForUserByUsername(String username) {
        List<Kweet> result = getEntityManager().createNativeQuery(SQL_KWEETS_FOR_USER, Kweet.class)
                .setParameter(1, username)
                .setParameter(2, username)
                .getResultList();
        return result;
    }

}
