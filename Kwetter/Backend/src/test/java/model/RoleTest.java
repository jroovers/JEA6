/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class RoleTest {

    private Role defaultrole;

    public RoleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        defaultrole = new Role(false, false, false, false);
    }

    @After
    public void tearDown() {
        defaultrole = null;
    }

    /**
     * Test of getId and setId methods, of class Role.
     */
    @Test
    public void testId() {
        System.out.println("Id");
        int id = 0;
        defaultrole.setId(id);
        assertEquals(id, defaultrole.getId());
    }

    /**
     * Test of isAllowedToViewUsers and setAllowedToViewUsers methods, of class
     * Role.
     */
    @Test
    public void testAllowedToViewUsers() {
        System.out.println("AllowedToViewUsers");

        boolean expResult = false;
        boolean result = defaultrole.isAllowedToViewUsers();
        assertEquals(expResult, result);
        expResult = true;
        defaultrole.setAllowedToViewUsers(true);
        result = defaultrole.isAllowedToViewUsers();
        assertEquals(expResult, result);
    }

    /**
     * Test of isAllowedToDeleteKweets and setAllowedToDeleteKweets methods, of
     * class Role.
     */
    @Test
    public void testAllowedToDeleteKweets() {
        System.out.println("AllowedToDeleteKweets");

        boolean expResult = false;
        boolean result = defaultrole.isAllowedToDeleteKweets();
        assertEquals(expResult, result);

        expResult = true;
        defaultrole.setAllowedToDeleteKweets(true);
        result = defaultrole.isAllowedToDeleteKweets();
        assertEquals(expResult, result);
    }

    /**
     * Test of isAllowedToManageRoles and testSetAllowedToManageRoles methods,
     * of class Role.
     */
    @Test
    public void testAllowedToManageRoles() {
        System.out.println("AllowedToManageRoles");

        boolean expResult = false;
        boolean result = defaultrole.isAllowedToManageRoles();
        assertEquals(expResult, result);

        expResult = true;
        defaultrole.setAllowedToManageRoles(true);
        result = defaultrole.isAllowedToManageRoles();
        assertEquals(expResult, result);
    }

    /**
     * Test of isAllowedToAssignRoles and setAllowedToAssignRoles method, of
     * class Role.
     */
    @Test
    public void testAllowedToAssignRoles() {
        System.out.println("isAllowedToAssignRoles");

        boolean expResult = false;
        boolean result = defaultrole.isAllowedToAssignRoles();
        assertEquals(expResult, result);

        expResult = true;
        defaultrole.setAllowedToAssignRoles(true);
        result = defaultrole.isAllowedToAssignRoles();
        assertEquals(expResult, result);
    }
}
