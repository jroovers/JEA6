package controller;

import java.util.List;
import model.Role;

/**
 *
 * @author Jeroen Roovers
 */
public interface RoleService {

    /**
     * Create a new role
     *
     * @param role role to add
     * @return the added role
     */
    public Role createNewRole(Role role);

    /**
     * Gets a list of all existing roles
     *
     * @return list of roles
     */
    public List<Role> getExistingRoles();

    /**
     * Deletes a role
     *
     * @param role
     * @return true if succesfully deleted
     */
    public boolean deleteRole(Role role);
}
