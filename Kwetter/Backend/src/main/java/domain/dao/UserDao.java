package domain.dao;

import domain.model.MutualFriendDTO;
import domain.model.User;
import java.util.List;

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
    
    public List<MutualFriendDTO> getMutualFriends(String username);

}
