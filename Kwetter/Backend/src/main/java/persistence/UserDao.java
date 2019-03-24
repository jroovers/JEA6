package persistence;

import model.User;

/**
 *
 * @author Jeroen Roovers
 */
public interface UserDao extends IBaseDao<User> {

    /**
     * Gets a single user by username
     *
     * @param username to search by
     * @return the user if found, otherwise null
     */
    public User getByUsername(String username);

}
