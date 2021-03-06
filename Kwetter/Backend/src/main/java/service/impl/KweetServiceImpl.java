package service.impl;

import service.KweetService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import domain.model.Kweet;
import domain.model.User;
import domain.dao.qualifiers.JPA;
import domain.dao.KweetDao;
import domain.dao.UserDao;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
public class KweetServiceImpl implements KweetService {

    @Inject
    @JPA
    private KweetDao kweetDao;

    @Inject
    @JPA
    private UserDao userDao;

    @Override
    public List<Kweet> getKweetsByUser(User user) {
        List<Kweet> returnlist = kweetDao.getKweetsByUserId(user.getId());
        return returnlist;
    }

    @Override
    public List<Kweet> getKweetsBySearch(String query) {
        if (query.length() < 3) {
            throw new IllegalArgumentException("Query length too short");
        }
        List<Kweet> returnlist = new ArrayList<>();
        List<Kweet> allKweets = kweetDao.getAll();
        for (Kweet k : allKweets) {
            // If authorname or body contains query or matches a tag. add it.
            if (k.getAuthor().getUsername().contains(query)
                    || k.getBody().contains(query)) {
                returnlist.add(k);
            }
        }
        return returnlist;
    }

    @Override
    public Kweet createKweet(User author, String body) {
        Kweet kweet = new Kweet(author, body);
        kweetDao.save(kweet);
        return kweet;
    }

    @Override
    public Kweet createKweet(String username, String body) {
        User u = userDao.getByUsername(username);
        if (u != null) {
            Kweet kweet = new Kweet(u, body);
            kweetDao.save(kweet);
            return kweet;
        } else {
            return null;
        }
    }

    @Override
    public List<Kweet> getKweetOverviewForUser(User u) {
        List<Kweet> timeline = new LinkedList<>();
        return kweetDao.getKweetsForUserByUsername(u.getUsername());
    }

    @Override
    public List<Kweet> getKweetOverview() {
        List<Kweet> everything = kweetDao.getAll();
        return everything;
    }

    public boolean deleteKweet(Kweet kweet) {
        kweetDao.delete(kweet);
        return true;
    }

    @Override
    public Kweet getKweetById(Long id) {
        return kweetDao.getById(id);
    }

}
