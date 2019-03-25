package utility;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import model.Permission;
import model.Role;
import model.User;
import persistence.KweetDao;
import persistence.PermissionDao;
import persistence.RoleDao;
import persistence.UserDao;
import persistence.qualifiers.JPA;

/**
 *
 * @author Jeroen Roovers
 */
@Startup
@Singleton
public class initApp implements Serializable {

    @Inject
    @JPA
    PermissionDao pDao;
    @Inject
    @JPA
    UserDao uDao;
    @Inject
    @JPA
    RoleDao rDao;
    @Inject
    @JPA
    KweetDao kDao;

    @PostConstruct
    public void init() {
        System.out.println("INITAPP.JAVA:: Executing app initialization.....");
        createPermissionsAndRoles();
        createUsers();
        createKweets();
    }

    public void createPermissionsAndRoles() {
        Permission manageKweets, manageUsers, manageModerators, manageAdmins;

        manageKweets = new Permission("MANAGE_KWEETS");
        manageUsers = new Permission("MANAGE_USERS");
        manageModerators = new Permission("MANAGE_MODERATORS");
        manageAdmins = new Permission("MANAGE_ADMINS");

        manageKweets = pDao.update(manageKweets);
        manageUsers = pDao.update(manageUsers);
        manageModerators = pDao.update(manageModerators);
        manageAdmins = pDao.update(manageAdmins);

        Role superadmin, admin, moderator, user;

        superadmin = new Role("SUPERADMIN");
        admin = new Role("ADMIN");
        moderator = new Role("MODERATOR");
        user = new Role("USER");

        superadmin.getPermissions().add(manageKweets);
        superadmin.getPermissions().add(manageUsers);
        superadmin.getPermissions().add(manageModerators);
        superadmin.getPermissions().add(manageAdmins);

        admin.getPermissions().add(manageKweets);
        admin.getPermissions().add(manageUsers);
        admin.getPermissions().add(manageModerators);

        moderator.getPermissions().add(manageKweets);
        moderator.getPermissions().add(manageUsers);

        rDao.save(user);
        rDao.save(moderator);
        rDao.save(admin);
        rDao.save(superadmin);
    }

    private String safePasswordHash(String password) {
        try {
            return PasswordStorage.createHash(password);
        } catch (PasswordStorage.CannotPerformOperationException ex) {
            Logger.getLogger(initApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void createUsers() {
        uDao.save(new User("Admin", safePasswordHash("password")));
        uDao.save(new User("Alice", safePasswordHash("password")));
        uDao.save(new User("Bob", safePasswordHash("password")));
        uDao.save(new User("Charlie", safePasswordHash("password")));
        uDao.save(new User("David", safePasswordHash("password")));
        uDao.save(new User("Eva", safePasswordHash("password")));
        uDao.save(new User("Frank", safePasswordHash("password")));
        uDao.save(new User("Geert", safePasswordHash("password")));
        uDao.save(new User("Henk", safePasswordHash("password")));
        uDao.save(new User("Ivo", safePasswordHash("password")));
        uDao.save(new User("Jeroen", safePasswordHash("password")));
        uDao.save(new User("Kim", safePasswordHash("password")));
        uDao.save(new User("Lucas", safePasswordHash("password")));
        uDao.save(new User("Martijn", safePasswordHash("password")));
        uDao.save(new User("Nico", safePasswordHash("password")));
        uDao.save(new User("Oscar", safePasswordHash("password")));
        uDao.save(new User("Pim", safePasswordHash("password")));
        uDao.save(new User("Richard", safePasswordHash("password")));
        uDao.save(new User("Stijn", safePasswordHash("password")));
        uDao.save(new User("Tim", safePasswordHash("password")));
        uDao.save(new User("Victor", safePasswordHash("password")));
        uDao.save(new User("Willem", safePasswordHash("password")));
        uDao.save(new User("Youssef", safePasswordHash("password")));

        User adminUser = uDao.getByUsername("Admin");
        User moderatorUser = uDao.getByUsername("Jeroen");

        Role adminRole = rDao.getByName("SUPERADMIN");
        Role moderatorRole = rDao.getByName("MODERATOR");
        
        adminUser.getRoles().add(adminRole);
        moderatorUser.getRoles().add(moderatorRole);
        uDao.update(adminUser);
        uDao.update(moderatorUser);
    }

    public void createKweets() {

    }

}
