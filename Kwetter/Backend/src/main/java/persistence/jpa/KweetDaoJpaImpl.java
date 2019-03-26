package persistence.jpa;

import javax.ejb.Stateless;
import model.Kweet;
import persistence.qualifiers.JPA;
import persistence.KweetDao;

@Stateless
@JPA
public class KweetDaoJpaImpl extends BaseDaoJpa<Kweet> implements KweetDao {
    
    public KweetDaoJpaImpl(){
        super.entityClass = Kweet.class;
    }
}
