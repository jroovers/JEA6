package persistence.jpa;

import javax.ejb.Stateless;
import model.Permission;
import persistence.qualifiers.JPA;
import persistence.PermissionDao;

@Stateless
@JPA
public class PermissionDaoJpaImpl extends BaseDaoJpa<Permission> implements PermissionDao {

    public PermissionDaoJpaImpl() {
        super.entityClass = Permission.class;
    }

}
