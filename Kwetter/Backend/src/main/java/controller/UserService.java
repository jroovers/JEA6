package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import model.PersonGroup;
import model.Person;

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
    public Person registerUser(String username, String password) throws IllegalArgumentException;

    /**
     * authenticate using username and password and get an user.
     *
     * @param username username to use
     * @param password password to use
     * @return used if succesful, null if invalid credentials.
     */
    public Person login(String username, String password);

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
    public Person changeUserName(Person user, String newName) throws IllegalArgumentException;

    /**
     * Changes the profile picture for a given user
     *
     * @param user user to update
     * @param image image to set for user
     * @return the updated user
     */
    public Person changeProfilePhoto(Person user, BufferedImage image);

    /**
     * Saves the users profile details including name, location, website,
     * biography
     *
     * @param user user with updated details
     * @return the (same) user
     */
    public Person updateProfileDetails(Person user);

    /**
     * gets all the users who are following a given user
     *
     * @param user user to get followers of
     * @return list of users
     */
    public List<Person> getFollowersByUser(Person user);

    /**
     * gets all the users who are being followed by given user
     *
     * @param user user to get followed users of
     * @return list of users
     */
    public List<Person> getUsersFollowedByUser(Person user);

    /**
     * gets all the users!
     *
     * @return list of users
     */
    public List<Person> getAllUsers();

    /**
     * Sets the role for a particular user
     *
     * @param user user to set role of
     * @param role role to set on user
     * @return the updated user
     */
    public Person setUserRoles(Person user, List<PersonGroup> role);
}
