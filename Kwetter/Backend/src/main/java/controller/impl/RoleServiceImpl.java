package controller.impl;

import controller.RoleService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.PersonGroup;
import persistence.JPA;
import persistence.Memory;
import persistence.RoleDao;

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
    public PersonGroup createNewRole(PersonGroup role) {
        roleDao.save(role);
        return role;
    }

    @Override
    public List<PersonGroup> getExistingRoles() {
        return roleDao.getAll();
    }

    @Override
    public PersonGroup updateRole(PersonGroup role) {
        PersonGroup savedRole = roleDao.getById(role.getId());
        savedRole.setName(role.getName());
        savedRole.setPrivileges(role.getPrivileges());
        roleDao.update(savedRole);
        return savedRole;
    }

    @Override
    public boolean deleteRole(PersonGroup role) {
        roleDao.delete(role);
        return true;
    }

}
