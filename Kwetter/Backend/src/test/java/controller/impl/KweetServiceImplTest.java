package controller.impl;

import java.util.List;
import model.Kweet;
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
public class KweetServiceImplTest {
    
    public KweetServiceImplTest() {
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
     * Test of getKweetsByUser method, of class KweetServiceImpl.
     */
    @Test
    public void testGetKweetsByUser() throws Exception {
        System.out.println("getKweetsByUser");
        User user = null;
        KweetServiceImpl instance = new KweetServiceImpl();
        List<Kweet> expResult = null;
        List<Kweet> result = instance.getKweetsByUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKweetsBySearch method, of class KweetServiceImpl.
     */
    @Test
    public void testGetKweetsBySearch() throws Exception {
        System.out.println("getKweetsBySearch");
        String query = "";
        KweetServiceImpl instance = new KweetServiceImpl();
        List<Kweet> expResult = null;
        List<Kweet> result = instance.getKweetsBySearch(query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createKweet method, of class KweetServiceImpl.
     */
    @Test
    public void testCreateKweet() throws Exception {
        System.out.println("createKweet");
        User author = null;
        Kweet kweet = null;
        KweetServiceImpl instance = new KweetServiceImpl();
        Kweet expResult = null;
        Kweet result = instance.createKweet(author, kweet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKweetOverviewForUser method, of class KweetServiceImpl.
     */
    @Test
    public void testGetKweetOverviewForUser() throws Exception {
        System.out.println("getKweetOverviewForUser");
        User u = null;
        KweetServiceImpl instance = new KweetServiceImpl();
        List<Kweet> expResult = null;
        List<Kweet> result = instance.getKweetOverviewForUser(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteKweet method, of class KweetServiceImpl.
     */
    @Test
    public void testDeleteKweet() throws Exception {
        System.out.println("deleteKweet");
        Kweet kweet = null;
        KweetServiceImpl instance = new KweetServiceImpl();
        boolean expResult = false;
        boolean result = instance.deleteKweet(kweet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
