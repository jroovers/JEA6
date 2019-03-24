package persistence.jpa;

import javax.ejb.Stateless;
import model.Role;
import persistence.qualifiers.JPA;
import persistence.RoleDao;

@Stateless
@JPA
public class RoleDaoJpaImpl extends BaseDaoJpa<Role> implements RoleDao {

    public RoleDaoJpaImpl() {
        super.entityClass = Role.class;
    }

    
}
