package domain.dao.impl.jpa;

import javax.ejb.Stateless;
import domain.model.Kweet;
import domain.dao.qualifiers.JPA;
import domain.dao.KweetDao;

@Stateless
@JPA
public class KweetDaoJpaImpl extends BaseDaoJpa<Kweet> implements KweetDao {
    
    public KweetDaoJpaImpl(){
        super.entityClass = Kweet.class;
    }
}
