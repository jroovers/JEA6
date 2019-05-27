package service;

import java.util.List;
import domain.model.Kweet;
import domain.model.User;

/**
 *
 * @author Jeroen Roovers
 */
public interface KweetService {

    /**
     * Gets the kweets for a particular user
     *
     * @param user user to get kweets of
     * @return List of kweets sorted from most recent to last
     */
    public List<Kweet> getKweetsByUser(User user);

    /**
     * Get kweets by search query. query should be at least 3 characters long
     * and can contain (partial) usernames, tags and kweet body content
     *
     * @param query text to query
     * @return List of kweets sorted from most recent to last
     */
    public List<Kweet> getKweetsBySearch(String query);

    /**
     * Creates a new kweet
     *
     * @param author user that posts the kweet
     * @param body text to be contained in kweet
     * @return the created kweet
     */
    public Kweet createKweet(User author, String body);

    /**
     * Creates a new kweet
     *
     * @param username username of user
     * @param body text to be contained in kweet
     * @return created kweet
     */
    public Kweet createKweet(String username, String body);

    /**
     * Get a personal selection of kweets for an user. This method should return
     * relevant kweets for example ones that mention the user or that are placed
     * by followed users
     *
     * @param u user
     * @return list of kweets
     */
    public List<Kweet> getKweetOverviewForUser(User u);

    /**
     * Get a generic selection of kweets. This method returns any kweets placed
     * by anyone and are not personalised to the logged in user.
     *
     * @return list of kweets
     */
    public List<Kweet> getKweetOverview();

    /**
     * Get a single kweet by its ID value.
     *
     * @param id id to filter by
     * @return kweet with given ID
     */
    public Kweet getKweetById(Long id);

}
