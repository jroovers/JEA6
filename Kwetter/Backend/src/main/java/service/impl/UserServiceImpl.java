package service.impl;

import service.UserService;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import domain.model.Role;
import domain.model.User;
import domain.dao.qualifiers.JPA;
import domain.dao.UserDao;
import utility.BufferedImageConverter;
import utility.PasswordStorage;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
public class UserServiceImpl extends BufferedImageConverter implements UserService {

    @Inject
    @JPA
    private UserDao userDao;

    @Inject
    private transient Logger logger;

    @Override
    public User registerUser(String username, String password) throws IllegalArgumentException {
        try {
            if (checkIfUserExistsByUsername(username)) {
                throw new IllegalArgumentException("An User already exists with the given username");
            } else {
                User u = new User();
                u.setUsername(username);
                u.setPasswordHash(PasswordStorage.createHash(password));
                userDao.save(u);
                return userDao.getByUsername(username);
            }
        } catch (PasswordStorage.CannotPerformOperationException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, "Could not create hash from password", ex);
            return null;
        }
    }

    @Override
    public User login(String username, String password) {
        try {
            if (checkIfUserExistsByUsername(username)) {
                User find = userDao.getByUsername(username);
                if (PasswordStorage.verifyPassword(password, find.getPasswordHash())) {
                    return find;
                } else {
                    throw new IllegalArgumentException("Wrong password");
                }
            } else {
                throw new IllegalArgumentException("There is no User with this username");
            }
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, "Failed to verify hash", ex);
            return null;
        }
    }

    @Override
    public boolean logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User changeUserName(User user, String newName) throws IllegalArgumentException {
        if (checkIfUserExistsByUsername(newName)) {
            throw new IllegalArgumentException("Username already taken by other user or same as existing username");
        } else {
            User savedUser = userDao.getById(user.getId());
            savedUser.setUsername(newName);
            userDao.update(savedUser);
            return savedUser;
        }
    }

    @Override
    public User changeProfilePhoto(User user, BufferedImage image) {
        User savedUser = userDao.getById(user.getId());
        savedUser.setImage(getBytesFromImage(image));
        userDao.update(savedUser);
        return savedUser;
    }

    @Override
    public User updateProfileDetails(User user) {
        User savedUser = userDao.getByUsername(user.getUsername());
        savedUser.setName(user.getName());
        savedUser.setLocation(user.getLocation());
        savedUser.setWebsite(user.getWebsite());
        savedUser.setBiography(user.getBiography());
        userDao.update(savedUser);
        return savedUser;
    }

    @Override
    public List<User> getFollowersByUser(User user) {
        User savedUser = userDao.getById(user.getId());
        return savedUser.getFollowers();
    }

    @Override
    public List<User> getUsersFollowedByUser(User user) {
        User savedUser = userDao.getById(user.getId());
        return savedUser.getFollowing();
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public User setUserRoles(User user, List<Role> roles) {
        User savedUser = userDao.getById(user.getId());
        savedUser.getRoles().addAll(roles);
        userDao.save(savedUser);
        return savedUser;
    }

    private boolean checkIfUserExistsByUsername(String username) {
        User find = userDao.getByUsername(username);
        return find != null;
    }

    @Override
    public User getUserbyUsername(String username) {
        return userDao.getByUsername(username);
    }
}
