package controller;

import java.awt.image.BufferedImage;
import java.util.List;
import model.Role;
import model.User;

/**
 *
 * @author Jeroen Roovers
 */
public class UserService {

    public User registerUser(String username, String password) {
        throw new UnsupportedOperationException();
    }

    public User login(String username, String password) {
        throw new UnsupportedOperationException();
    }

    public boolean logout(User user) {
        throw new UnsupportedOperationException();
    }

    public User changeUserName(User user, String newName) {
        throw new UnsupportedOperationException();
    }

    public User changeProfilePhoto(User user, BufferedImage image) {
        throw new UnsupportedOperationException();
    }

    public User updateProfileDetails(User user) {
        throw new UnsupportedOperationException();
    }

    public List<User> getFollowersByUser(User user) {
        throw new UnsupportedOperationException();
    }

    public List<User> getUsersFollowedByUser(User user) {
        throw new UnsupportedOperationException();
    }

    public List<User> getAllUsers() {
        throw new UnsupportedOperationException();
    }

    public User setUserRole(User user, Role role) {
        throw new UnsupportedOperationException();
    }

}
