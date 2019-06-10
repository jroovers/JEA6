/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeroen Roovers <jroovers>
 */
public class UserTest {

    public UserTest() {
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
     * Test of followThisUser method, of class User.
     */
    @Test
    public void testFollowThisUser() {
        System.out.println("unfollowThisUser");
        User follower = new User("Henk", "Henk");
        User instance = new User();
        follower.setFollowing(new ArrayList<>());
        instance.setFollowers(new ArrayList<>());
        boolean expResult = true;
        boolean result = instance.followThisUser(follower);
        assertEquals(expResult, result);
        boolean expResult2 = false;
        boolean result2 = instance.followThisUser(follower);
        assertEquals(expResult2, result2);
    }
}
