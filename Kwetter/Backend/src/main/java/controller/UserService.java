package controller;

import java.awt.image.BufferedImage;
import java.util.List;
import model.Role;
import model.User;

/**
 *
 * @author Jeroen Roovers
 */
public interface UserService {

    /**
     * Registers a new user
     *
     * @param username new user's username
     * @param password new user's password
     * @return the created user
     * @throws IllegalArgumentException if username already taken
     */
    public User registerUser(String username, String password) throws IllegalArgumentException;

    /**
     * authenticate using username and password and get an user.
     *
     * @param username username to use
     * @param password password to use
     * @return used if succesful, null if invalid credentials.
     */
    public User login(String username, String password);

    /**
     * logs out a user and destroys the session
     *
     * @param user user to logout
     * @return true if succesful
     */
    public boolean logout(User user);

    /**
     *
     * @param user
     * @param newName
     * @return
     */
    public User changeUserName(User user, String newName);

    /**
     *
     * @param user
     * @param image
     * @return
     */
    public User changeProfilePhoto(User user, BufferedImage image);

    /**
     *
     * @param user
     * @return
     */
    public User updateProfileDetails(User user);

    /**
     *
     * @param user
     * @return
     */
    public List<User> getFollowersByUser(User user);

    /**
     *
     * @param user
     * @return
     */
    public List<User> getUsersFollowedByUser(User user);

    /**
     *
     * @return
     */
    public List<User> getAllUsers();

    /**
     *
     * @param user
     * @param role
     * @return
     */
    public User setUserRole(User user, Role role);

}
