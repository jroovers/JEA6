///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package model;
//
//import java.time.ZonedDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.Ignore;
//
///**
// *
// * @author jeroe
// */
//public class KweetTest {
//
//    private Role defaultrole;
//    private User defaultUser;
//    private Kweet defaultKweet;
//    private String defaultbody = "defaultkweet";
//    private String defaultUsername = "defaultuser";
//
//    public KweetTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//        defaultrole = new Role(false, false, false, false);
//        defaultUser = new User(defaultUsername, defaultrole);
//        defaultKweet = new Kweet(defaultUser, defaultbody);
//    }
//
//    @After
//    public void tearDown() {
//        defaultrole = null;
//        defaultUser = null;
//        defaultKweet = null;
//    }
//
//    /**
//     * Test of likePost method, of class Kweet.
//     */
//    @Test
//    @Ignore
//    public void testLikePost() {
//        System.out.println("likePost");
//
//        defaultKweet.likePost(defaultUser);
//        assertEquals(defaultKweet.getLikedBy().size(), 1);
//        defaultKweet.likePost(defaultUser);
//        assertEquals(defaultKweet.getLikedBy().size(), 0);
//
//    }
//
//    /**
//     * Test of getId and setId methods, of class Kweet.
//     */
//    @Test
//    public void testId() {
//        System.out.println("Id");
//
//        int id = 1;
//        defaultKweet.setId(id);
//        assertEquals(id, defaultKweet.getId());
//    }
//
//    /**
//     * Test of getAuthor and setAuthor methods, of class Kweet.
//     */
//    @Test
//    public void testAuthor() {
//        System.out.println("Author");
//
//        // defaultkweet already has an author. so lets just try unsetting it.
//        defaultKweet.setAuthor(null);
//        assertNull(defaultKweet.getAuthor());
//        // now normal test
//        defaultKweet.setAuthor(defaultUser);
//        assertEquals(defaultUser, defaultKweet.getAuthor());
//    }
//
//    /**
//     * Test of getBody and setBody methods, of class Kweet.
//     */
//    @Test
//    public void testBody() {
//        System.out.println("Body");
//
//        String expResult = "body-test";
//        defaultKweet.setBody(expResult);
//        assertEquals(expResult, defaultKweet.getBody());
//    }
//
//    /**
//     * Test of getTime and setTime methods, of class Kweet.
//     */
//    @Test
//    public void testTime() {
//        System.out.println("Time");
//
//        ZonedDateTime expected = ZonedDateTime.now();
//        defaultKweet.setTime(expected);
//        assertEquals(expected, defaultKweet.getTime());
//    }
//
//    /**
//     * Test of getMentions and setMentions methods, of class Kweet.
//     */
//    @Test
//    public void testMentions() {
//        System.out.println("Mentions");
//
//        List<User> expResult = new ArrayList<>();
//        expResult.add(defaultUser);
//        defaultKweet.setMentions(expResult);
//        assertEquals(expResult, defaultKweet.getMentions());
//    }
//
//    /**
//     * Test of getTags and setTags methods, of class Kweet.
//     */
//    @Test
//    public void testTags() {
//        System.out.println("Tags");
//        
//        List<String> expResult = new ArrayList<>();
//        expResult.add("foo");
//        expResult.add("bar");
//        defaultKweet.setTags(expResult);
//        assertEquals(expResult, defaultKweet.getTags());
//    }
//
//    /**
//     * Test of getLikedBy and setLikedBy methods, of class Kweet.
//     */
//    @Test
//    public void testLikedBy() {
//        System.out.println("LikedBy");
//        
//        List<User> expResult = new ArrayList<>();
//        expResult.add(defaultUser);
//        defaultKweet.setLikedBy(expResult);
//        assertEquals(expResult, defaultKweet.getLikedBy());
//    }
//
//}
