package controller.impl;

import controller.KweetService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.Kweet;
import model.Person;
import persistence.JPA;
import persistence.KweetDao;
import persistence.Memory;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
public class KweetServiceImpl implements KweetService {

    @Inject
    @JPA
    private KweetDao kweetDao;

    @Override
    public List<Kweet> getKweetsByUser(Person user) {
        Long userid = user.getId();
        List<Kweet> returnlist = new ArrayList<>();
        List<Kweet> allKweets = kweetDao.getAll();
        for (Kweet k : allKweets) {
            if (k.getAuthor().getId() == userid) {
                returnlist.add(k);
            }
        }
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
                    || k.getBody().contains(query)
                    || k.getTags().contains(query)) {
                returnlist.add(k);
            }
        }
        return returnlist;
    }

    @Override
    public Kweet createKweet(Person author, Kweet kweet) {
        kweet.setAuthor(author);
        kweetDao.save(kweet);
        return kweet;
    }

    @Override
    public List<Kweet> getKweetOverviewForUser(Person u) {
        List<Kweet> everything = kweetDao.getAll();
        return everything;
//        List<Kweet> timeline = new LinkedList();
//        for(Kweet k : everything){
//            timeline.addAll(
//            u.getFollowingOtherUsers().stream()
//                    .filter(id -> id.getId() == k.getAuthor().getId())
//                    .collect(Collectors.toList())
//            );
//        }
    }

    @Override
    public boolean deleteKweet(Kweet kweet) {
        kweetDao.delete(kweet);
        return true;
    }

}
