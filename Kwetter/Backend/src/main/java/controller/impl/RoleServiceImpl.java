package controller.impl;

import controller.RoleService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.Role;
import persistence.Memory;
import persistence.RoleDao;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
public class RoleServiceImpl implements RoleService {

    @Inject
    @Memory
    RoleDao roleDao;

    @Override
    public Role createNewRole(Role role) {
        roleDao.save(role);
        return role;
    }

    @Override
    public List<Role> getExistingRoles() {
        return roleDao.getAll();
    }

    @Override
    public boolean deleteRole(Role role) {
        roleDao.delete(role);
        return true;
    }

}
