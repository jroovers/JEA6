package controller.impl;

import java.awt.image.BufferedImage;
import java.util.List;
import model.Role;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Jeroen Roovers
 */
@Ignore
public class UserServiceImplTest {

    public UserServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of registerUser method, of class UserServiceImpl.
     */
    @Test
    public void testRegisterUser() throws Exception {
        System.out.println("registerUser");
        String username = "";
        String password = "";
        UserServiceImpl instance = new UserServiceImpl();
        User expResult = null;
        User result = instance.registerUser(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class UserServiceImpl.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String username = "";
        String password = "";
        UserServiceImpl instance = new UserServiceImpl();
        User expResult = null;
        User result = instance.login(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logout method, of class UserServiceImpl.
     */
    @Test
    public void testLogout() throws Exception {
        System.out.println("logout");
        User user = null;
        UserServiceImpl instance = new UserServiceImpl();
        boolean expResult = false;
        boolean result = instance.logout();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeUserName method, of class UserServiceImpl.
     */
    @Test
    public void testChangeUserName() throws Exception {
        System.out.println("changeUserName");
        User user = null;
        String newName = "";
        UserServiceImpl instance = new UserServiceImpl();
        User expResult = null;
        User result = instance.changeUserName(user, newName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeProfilePhoto method, of class UserServiceImpl.
     */
    @Test
    public void testChangeProfilePhoto() throws Exception {
        System.out.println("changeProfilePhoto");
        User user = null;
        BufferedImage image = null;
        UserServiceImpl instance = new UserServiceImpl();
        User expResult = null;
        User result = instance.changeProfilePhoto(user, image);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProfileDetails method, of class UserServiceImpl.
     */
    @Test
    public void testUpdateProfileDetails() throws Exception {
        System.out.println("updateProfileDetails");
        User user = null;
        UserServiceImpl instance = new UserServiceImpl();
        User expResult = null;
        User result = instance.updateProfileDetails(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFollowersByUser method, of class UserServiceImpl.
     */
    @Test
    public void testGetFollowersByUser() throws Exception {
        System.out.println("getFollowersByUser");
        User user = null;
        UserServiceImpl instance = new UserServiceImpl();
        List<User> expResult = null;
        List<User> result = instance.getFollowersByUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsersFollowedByUser method, of class UserServiceImpl.
     */
    @Test
    public void testGetUsersFollowedByUser() throws Exception {
        System.out.println("getUsersFollowedByUser");
        User user = null;
        UserServiceImpl instance = new UserServiceImpl();
        List<User> expResult = null;
        List<User> result = instance.getUsersFollowedByUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUsers method, of class UserServiceImpl.
     */
    @Test
    public void testGetAllUsers() throws Exception {
        System.out.println("getAllUsers");
        UserServiceImpl instance = new UserServiceImpl();
        List<User> expResult = null;
        List<User> result = instance.getAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserRole method, of class UserServiceImpl.
     */
    @Test
    public void testSetUserRole() throws Exception {
        System.out.println("setUserRole");
        User user = null;
        Role role = null;
        UserServiceImpl instance = new UserServiceImpl();
        User expResult = null;
        User result = instance.setUserRole(user, role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
