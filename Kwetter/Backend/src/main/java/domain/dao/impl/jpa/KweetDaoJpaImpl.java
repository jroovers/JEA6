package domain.dao.impl.jpa;

import javax.ejb.Stateless;
import domain.model.Kweet;
import domain.dao.qualifiers.JPA;
import domain.dao.KweetDao;
import java.util.List;

@Stateless
@JPA
public class KweetDaoJpaImpl extends BaseDaoJpa<Kweet> implements KweetDao {

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

}
