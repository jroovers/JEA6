/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.util.HashSet;
import java.util.Set;
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

    private Role defaultRole;

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
        defaultRole = new Role("TESTrole");
        Set<Permission> permissions = new HashSet<>();
        permissions.add(new Permission("Remove Kweets"));
        permissions.add(new Permission("Manage Roles"));
        permissions.add(new Permission("Modify Users"));
        defaultRole.setPermissions(permissions);
    }

    @After
    public void tearDown() {
        defaultRole = null;
    }

    /**
     * Test of getId and setId methods, of class Role.
     */
    @Test
    public void testId() {
        System.out.println("Id");
        Long id = 1L;
        defaultRole.setId(id);
        assertEquals(id, defaultRole.getId());
    }

}
