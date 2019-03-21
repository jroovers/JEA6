package controller.impl;

import controller.UserService;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.PersonGroup;
import model.Person;
import persistence.JPA;
import persistence.Memory;
import persistence.UserDao;
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

    @Override
    public Person registerUser(String username, String password) throws IllegalArgumentException {
        try {
            if (checkIfUserExistsByUsername(username)) {
                throw new IllegalArgumentException("An User already exists with the given username");
            } else {
                Person u = new Person();
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
    public Person login(String username, String password) {
        try {
            if (checkIfUserExistsByUsername(username)) {
                Person find = userDao.getByUsername(username);
                if (PasswordStorage.verifyPassword(password, find.getPasswordHash())) {
                    return find;
                } else {
                    throw new IllegalArgumentException("Wrong password");
                }
            } else {
                throw new IllegalArgumentException("There is no User with this username");
            }
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, "Failed not verify hash", ex);
            return null;
        }
    }

    @Override
    public boolean logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person changeUserName(Person user, String newName) throws IllegalArgumentException {
        if (checkIfUserExistsByUsername(newName)) {
            throw new IllegalArgumentException("Username already taken by other user or same as existing username");
        } else {
            Person savedUser = userDao.getById(user.getId());
            savedUser.setUsername(newName);
            userDao.update(savedUser);
            return savedUser;
        }
    }

    @Override
    public Person changeProfilePhoto(Person user, BufferedImage image) {
        Person savedUser = userDao.getById(user.getId());
        savedUser.setImage(getBytesFromImage(image));
        userDao.update(savedUser);
        return savedUser;
    }

    @Override
    public Person updateProfileDetails(Person user) {
        Person savedUser = userDao.getById(user.getId());
        savedUser.setName(user.getName());
        savedUser.setLocation(user.getLocation());
        savedUser.setWebsite(user.getWebsite());
        savedUser.setBiography(user.getBiography());
        userDao.update(savedUser);
        return savedUser;
    }

    @Override
    public List<Person> getFollowersByUser(Person user) {
        Person savedUser = userDao.getById(user.getId());
        return savedUser.getFollowers();
    }

    @Override
    public List<Person> getUsersFollowedByUser(Person user) {
        Person savedUser = userDao.getById(user.getId());
        return savedUser.getFollowing();
    }

    @Override
    public List<Person> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public Person setUserRoles(Person user, List<PersonGroup> roles) {
        Person savedUser = userDao.getById(user.getId());
        savedUser.getRoles().addAll(roles);
        userDao.save(savedUser);
        return savedUser;
    }

    private boolean checkIfUserExistsByUsername(String username) {
        Person find = userDao.getByUsername(username);
        if (find == null) {
            return false;
        } else {
            return true;
        }
    }

}
