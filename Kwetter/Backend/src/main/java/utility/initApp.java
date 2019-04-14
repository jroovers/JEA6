package utility;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import domain.model.Kweet;
import domain.model.Permission;
import domain.model.Role;
import domain.model.User;
import domain.dao.KweetDao;
import domain.dao.PermissionDao;
import domain.dao.RoleDao;
import domain.dao.UserDao;
import domain.dao.qualifiers.JPA;
import java.util.ArrayList;

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
        long start = System.currentTimeMillis();
        createPermissionsAndRoles();
        createUsers();
        createKweets();
        long end = System.currentTimeMillis();
        System.out.println("INITAPP.JAVA:: Done! Operation took " + (end - start) + " ms");
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
        User u = new User("Antonio", safePasswordHash("password"));
        u.setBiography("Really excited for the next MLP friendship is magic!");
        u.setWebsite("https://github.com/Tonii123");
        u.setLocation("Rachelsmolen");
        u.setName("A Mijic");
        uDao.save(u);

        User adminUser = uDao.getByUsername("Admin");
        User moderatorUser = uDao.getByUsername("Jeroen");

        User ant = uDao.getByUsername("Antonio");
        if (ant.getFollowers() == null) {
            ant.setFollowing(new ArrayList<>());
        }
        ant.getFollowing().add(adminUser);
        ant.getFollowing().add(moderatorUser);
        uDao.save(u);

        Role adminRole = rDao.getByName("SUPERADMIN");
        Role moderatorRole = rDao.getByName("MODERATOR");

        adminUser.getRoles().add(adminRole);
        moderatorUser.getRoles().add(moderatorRole);
        uDao.update(adminUser);
        uDao.update(moderatorUser);
    }

    public void createKweets() {
        List<User> allUsers = uDao.getAll();
        for (User u : allUsers) {
            kDao.save(randomKweet(u));
            kDao.save(randomKweet(u));
        }

    }

    private Kweet randomKweet(User author) {
        String[] greetings = {
            "Hoi",
            "Hi",
            "Jongens,",
            "Hey"};
        String[] crazyBody = {
            "REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE",
            "java.lang.StackOverflowError ;)",
            "(╯°□°）╯︵ ┻━┻)",
            "┬─┬ ノ( ゜-゜ノ)",
            "Curse you Perry the Platypus. Curse you Perry the Platypus. CURSE YOU!",
            "Own a musket for home defense, since that's what the founding fathers intended.",
            "Ik ben gehard by den Katergeuzen en ben den beste schutter onder den Nederlandsche vlag",
            "JAAS zit bomvol met goede features Kappa123"
        };
        String[] normalBody = {
            "Zin om even lekker te ontspannen, wie heeft tips?",
            "Voel me goed vandaag!",
            "Iemand zin om iets te doen?",
            "Work hard, play hard",
            "Ik heb echt mega veel zin in een broodje van hizmet. Alles erop maatje?!"};
        String[] lookAtThisBody = {
            "Interessant artikel op NU.nl: https://www.nu.nl/algemeen",
            "Mooi item op Tweakers: https://tweakers.net/nieuws/",
            "Boeiende uiteg in de Oracle docs: https://www.oracle.com/technical-resources/",
            "Check NOS voor dat laatste nieuws :X"};
        String[] goodbye = {
            "oke doei",
            "bye",
            "challaz",
            "hasta la vista",
            "houdoe",
            "doei",
            "groetjes"};

        Random r = new Random();
        StringBuilder nonsense = new StringBuilder();
        if (r.nextBoolean()) {
            nonsense.append(greetings[r.nextInt(greetings.length)]);
            nonsense.append(' ');
        }
        switch (r.nextInt(3)) {
            case 0:
                nonsense.append(crazyBody[r.nextInt(crazyBody.length)]);
                nonsense.append(' ');
                break;
            case 1:
                nonsense.append(normalBody[r.nextInt(normalBody.length)]);
                nonsense.append(' ');
                break;
            case 2:
                nonsense.append(lookAtThisBody[r.nextInt(lookAtThisBody.length)]);
                nonsense.append(' ');
                break;
            default:
                break;
        }
        if (r.nextBoolean()) {
            nonsense.append(goodbye[r.ints(0, goodbye.length).findFirst().getAsInt()]);
        }

        String randomNonsense = nonsense.toString();

        return new Kweet(author, randomNonsense);
    }

}
