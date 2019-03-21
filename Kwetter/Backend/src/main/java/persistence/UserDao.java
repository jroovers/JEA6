package persistence;

import model.Person;

/**
 *
 * @author Jeroen Roovers
 */
public interface UserDao extends IBaseDao<Person> {

    /**
     * Gets a single user by username
     *
     * @param username to search by
     * @return the user if found, otherwise null
     */
    public Person getByUsername(String username);

}
