package service.impl;

import service.RoleService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import domain.model.Role;
import domain.dao.qualifiers.JPA;
import domain.dao.RoleDao;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
public class RoleServiceImpl implements RoleService {

    @Inject
    @JPA
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
    public Role updateRole(Role role) {
        Role savedRole = roleDao.getById(role.getId());
        savedRole.setName(role.getName());
        savedRole.setPermissions(role.getPermissions());
        roleDao.update(savedRole);
        return savedRole;
    }

    @Override
    public boolean deleteRole(Role role) {
        roleDao.delete(role);
        return true;
    }

}
