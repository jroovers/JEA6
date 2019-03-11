package controller.impl;

import controller.UserService;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.Role;
import model.User;
import persistence.Memory;
import persistence.UserDao;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    @Memory
    private UserDao userDao;

    @Override
    public User registerUser(String username, String password) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean logout(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User changeUserName(User user, String newName) throws IllegalArgumentException {
        List<User> allUsers = userDao.getAll();
        for (User u : allUsers) {
            if (u.getUsername().equals(newName)) {
                throw new IllegalArgumentException("Username already taken by other user or same as existing username");
            }
        }
        User savedUser = userDao.get(user.getId());
        savedUser.setUsername(newName);
        userDao.save(savedUser);
        return savedUser;
    }

    @Override
    public User changeProfilePhoto(User user, BufferedImage image) {
        User u = userDao.get(user.getId());
        u.setImage(image);
        userDao.save(u);
        return u;
    }

    @Override
    public User updateProfileDetails(User user) {
        userDao.save(user);
        return user;
    }

    @Override
    public List<User> getFollowersByUser(User user) {
        User savedUser = userDao.get(user.getId());
        return savedUser.getFollowedByUsers();
    }

    @Override
    public List<User> getUsersFollowedByUser(User user) {
        User savedUser = userDao.get(user.getId());
        return savedUser.getFollowingOtherUsers();
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public User setUserRole(User user, Role role) {
        User savedUser = userDao.get(user.getId());
        savedUser.setRole(role);
        userDao.save(savedUser);
        return savedUser;
    }

}
