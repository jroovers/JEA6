package service.impl;

import service.KweetService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.Kweet;
import model.User;
import persistence.qualifiers.JPA;
import persistence.KweetDao;

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
    public List<Kweet> getKweetsByUser(User user) {
        Long userid = user.getId();
        List<Kweet> returnlist = new ArrayList<>();
        List<Kweet> allKweets = kweetDao.getAll();
        for (Kweet k : allKweets) {
            if (Objects.equals(k.getAuthor().getId(), userid)) {
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
                    || k.getBody().contains(query)) {
                returnlist.add(k);
            }
        }
        return returnlist;
    }

    @Override
    public Kweet createKweet(User author, Kweet kweet) {
        kweet.setAuthor(author);
        kweetDao.save(kweet);
        return kweet;
    }

    @Override
    public List<Kweet> getKweetOverviewForUser(User u) {
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
    
    
    public boolean deleteKweet(Kweet kweet) {
        kweetDao.delete(kweet);
        return true;
    }
}
