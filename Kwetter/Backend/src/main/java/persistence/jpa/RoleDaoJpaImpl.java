package persistence.jpa;

import java.util.List;
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
