package controller;

import java.util.List;
import model.PersonGroup;

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
    public PersonGroup createNewRole(PersonGroup role);

    /**
     * Gets a list of all existing roles
     *
     * @return list of roles
     */
    public List<PersonGroup> getExistingRoles();

    /**
     * updates an existing role
     *
     * @return the updated role
     */
    public PersonGroup updateRole(PersonGroup role);

    /**
     * Deletes a role
     *
     * @param role
     * @return true if succesfully deleted
     */
    public boolean deleteRole(PersonGroup role);
}
