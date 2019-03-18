package model;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jeroen Roovers
 */
public class User {

    // non optionals
    private Long id;
    private String username;
    private String passwordHash;
    private Set<Role> roles;

    // optionals
    private BufferedImage image;
    private String name;
    private String location;
    private String website;
    private String biography;
    private List<User> followedByUsers;
    private List<User> followingOtherUsers;
    private List<Kweet> kweets;

    public User() {
        this.roles = new HashSet<>();
        this.followedByUsers = new LinkedList<>();
        this.followingOtherUsers = new LinkedList<>();
        this.kweets = new LinkedList<>();
    }

    public void followUser(User user) {
        this.followingOtherUsers.add(user);
    }

    public void unfollowUser(User user) {
        this.followingOtherUsers.remove(user);
    }

    public void addFollower(User user) {
        this.followedByUsers.add(user);
    }

    public void removeFollower(User user) {
        this.followedByUsers.remove(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordhash) {
        this.passwordHash = passwordhash;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<User> getFollowedByUsers() {
        return followedByUsers;
    }

    public void setFollowedByUsers(List<User> followedByUsers) {
        this.followedByUsers = followedByUsers;
    }

    public List<User> getFollowingOtherUsers() {
        return followingOtherUsers;
    }

    public void setFollowingOtherUsers(List<User> followingOtherUsers) {
        this.followingOtherUsers = followingOtherUsers;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

}
