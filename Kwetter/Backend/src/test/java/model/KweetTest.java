/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.ZonedDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jeroe
 */
public class KweetTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of likePost method, of class Kweet.
     */
    @Test
    public void testLikePost() {
        System.out.println("likePost");
        User user = null;
        Kweet instance = new Kweet();
        instance.likePost(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Kweet.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Kweet instance = new Kweet();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Kweet.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Kweet instance = new Kweet();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuthor method, of class Kweet.
     */
    @Test
    public void testGetAuthor() {
        System.out.println("getAuthor");
        Kweet instance = new Kweet();
        User expResult = null;
        User result = instance.getAuthor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAuthor method, of class Kweet.
     */
    @Test
    public void testSetAuthor() {
        System.out.println("setAuthor");
        User author = null;
        Kweet instance = new Kweet();
        instance.setAuthor(author);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBody method, of class Kweet.
     */
    @Test
    public void testGetBody() {
        System.out.println("getBody");
        Kweet instance = new Kweet();
        String expResult = "";
        String result = instance.getBody();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBody method, of class Kweet.
     */
    @Test
    public void testSetBody() {
        System.out.println("setBody");
        String body = "";
        Kweet instance = new Kweet();
        instance.setBody(body);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTime method, of class Kweet.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        Kweet instance = new Kweet();
        ZonedDateTime expResult = null;
        ZonedDateTime result = instance.getTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTime method, of class Kweet.
     */
    @Test
    public void testSetTime() {
        System.out.println("setTime");
        ZonedDateTime time = null;
        Kweet instance = new Kweet();
        instance.setTime(time);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMentions method, of class Kweet.
     */
    @Test
    public void testGetMentions() {
        System.out.println("getMentions");
        Kweet instance = new Kweet();
        List<User> expResult = null;
        List<User> result = instance.getMentions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMentions method, of class Kweet.
     */
    @Test
    public void testSetMentions() {
        System.out.println("setMentions");
        List<User> mentions = null;
        Kweet instance = new Kweet();
        instance.setMentions(mentions);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTags method, of class Kweet.
     */
    @Test
    public void testGetTags() {
        System.out.println("getTags");
        Kweet instance = new Kweet();
        List<String> expResult = null;
        List<String> result = instance.getTags();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTags method, of class Kweet.
     */
    @Test
    public void testSetTags() {
        System.out.println("setTags");
        List<String> tags = null;
        Kweet instance = new Kweet();
        instance.setTags(tags);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLikedBy method, of class Kweet.
     */
    @Test
    public void testGetLikedBy() {
        System.out.println("getLikedBy");
        Kweet instance = new Kweet();
        List<User> expResult = null;
        List<User> result = instance.getLikedBy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLikedBy method, of class Kweet.
     */
    @Test
    public void testSetLikedBy() {
        System.out.println("setLikedBy");
        List<User> likedBy = null;
        Kweet instance = new Kweet();
        instance.setLikedBy(likedBy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
