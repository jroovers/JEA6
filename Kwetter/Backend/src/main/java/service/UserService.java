package service;

import java.awt.image.BufferedImage;
import java.util.List;
import domain.model.Role;
import domain.model.User;

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
     * @return true if succesful
     */
    public boolean logout();

    /**
     * Changes the username for given user
     *
     * @param user user to update
     * @param newName username to update user with
     * @return the updated user
     * @throws IllegalArgumentException when username already taken
     */
    public User changeUserName(User user, String newName) throws IllegalArgumentException;

    /**
     * Changes the profile picture for a given user
     *
     * @param user user to update
     * @param image image to set for user
     * @return the updated user
     */
    public User changeProfilePhoto(User user, BufferedImage image);

    /**
     * Saves the users profile details including name, location, website,
     * biography
     *
     * @param user user with updated details
     * @return the (same) user
     */
    public User updateProfileDetails(User user);

    /**
     * gets all the users who are following a given user
     *
     * @param user user to get followers of
     * @return list of users
     */
    public List<User> getFollowersByUser(User user);

    /**
     * gets all the users who are being followed by given user
     *
     * @param user user to get followed users of
     * @return list of users
     */
    public List<User> getUsersFollowedByUser(User user);

    /**
     * gets all the users!
     *
     * @return list of users
     */
    public List<User> getAllUsers();

    /**
     * Sets the role for a particular user
     *
     * @param user user to set role of
     * @param role role to set on user
     * @return the updated user
     */
    public User setUserRoles(User user, List<Role> role);

    /**
     * Gets an user by known username
     *
     * @param username
     * @return single user
     */
    public User getUserbyUsername(String username);
}
