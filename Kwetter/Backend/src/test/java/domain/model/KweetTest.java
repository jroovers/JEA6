/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import utility.PasswordStorage;

/**
 *
 * @author jeroe
 */
public class KweetTest {

    private Role defaultRole;
    private User defaultUser;
    private Kweet defaultKweet;
    private String defaultBody = "defaultKweet";
    private String defaultUsername = "testUser";

    public KweetTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        defaultRole = new Role("TEST");
        try {
            defaultUser = new User(defaultUsername, PasswordStorage.createHash("password"));
        } catch (PasswordStorage.CannotPerformOperationException ex) {
            Logger.getLogger(KweetTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        defaultKweet = new Kweet(defaultUser, defaultBody);
    }

    @After
    public void tearDown() {
        defaultRole = null;
        defaultUser = null;
        defaultKweet = null;
    }

    /**
     * Test of getId and setId methods, of class Kweet.
     */
    @Test
    public void testId() {
        System.out.println("Id");

        Long id = 1L;
        defaultKweet.setId(id);
        assertEquals(id, defaultKweet.getId());
    }

    /**
     * Test of getAuthor and setAuthor methods, of class Kweet.
     */
    @Test
    public void testAuthor() {
        System.out.println("Author");

        // defaultkweet already has an author. so lets just try unsetting it.
        defaultKweet.setAuthor(null);
        assertNull(defaultKweet.getAuthor());
        // now normal test
        defaultKweet.setAuthor(defaultUser);
        assertEquals(defaultUser, defaultKweet.getAuthor());
    }

    /**
     * Test of getBody and setBody methods, of class Kweet.
     */
    @Test
    public void testBody() {
        System.out.println("Body");

        String expResult = "body-test";
        defaultKweet.setBody(expResult);
        assertEquals(expResult, defaultKweet.getBody());
    }

    /**
     * Test of getTime and setTime methods, of class Kweet.
     */
    @Test
    public void testTime() {
        System.out.println("Time");

        ZonedDateTime expected = ZonedDateTime.now();
        defaultKweet.setCreatedTime(expected);
        assertEquals(expected, defaultKweet.getCreatedTime());
    }
}
