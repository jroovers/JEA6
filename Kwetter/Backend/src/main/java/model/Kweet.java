package model;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jeroen Roovers
 */
public class Kweet {

    private int id;
    private User author;
    private String body;
    private ZonedDateTime time;

    private List<User> mentions;
    private List<String> tags;

    private List<User> likedBy;

    public Kweet() {

    }

    public Kweet(User author, String body) {
        this.author = author;
        this.body = body;
        this.time = ZonedDateTime.now(ZoneOffset.UTC);
        this.mentions = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.likedBy = new LinkedList<>();
    }

    /// THIS PROBALY DOESNT BELONG HERE BUT IN SERVICE LAYER INSTEAD
    public void likePost(User user) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<User> likedBy) {
        this.likedBy = likedBy;
    }

}
