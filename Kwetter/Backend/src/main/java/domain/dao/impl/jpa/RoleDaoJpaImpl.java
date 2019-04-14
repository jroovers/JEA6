package domain.dao.impl.jpa;

import java.util.List;
import javax.ejb.Stateless;
import domain.model.Role;
import domain.dao.qualifiers.JPA;
import domain.dao.RoleDao;

@Stateless
@JPA
public class RoleDaoJpaImpl extends BaseDaoJpa<Role> implements RoleDao {

    public RoleDaoJpaImpl() {
        super.entityClass = Role.class;
    }

    @Override
    public Role getByName(String name) {
        List<Role> list = getEntityManager().createNamedQuery("role.FindByName").setParameter("name", name).getResultList();
        if (list.isEmpty() || list.size() > 1) {
            return null;
        } else {
            return list.get(0);
        }
    }

}
