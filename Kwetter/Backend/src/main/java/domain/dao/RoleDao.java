package domain.dao;

import domain.model.Role;

/**
 *
 * @author Jeroen Roovers
 */
public interface RoleDao extends IBaseDao<Role> {

    /**
     * Gets a single user by username
     *
     * @param name to search by
     * @return the user if found, otherwise null
     */
    public Role getByName(String name);
}
