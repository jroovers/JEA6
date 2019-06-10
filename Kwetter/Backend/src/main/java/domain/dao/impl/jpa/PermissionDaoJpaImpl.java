package domain.dao.impl.jpa;

import javax.ejb.Stateless;
import domain.model.Permission;
import domain.dao.qualifiers.JPA;
import domain.dao.PermissionDao;

@Stateless
@JPA
public class PermissionDaoJpaImpl extends BaseDaoJpa<Permission> implements PermissionDao {

    public PermissionDaoJpaImpl() {
        super.entityClass = Permission.class;
    }

}
