package service.impl;

import java.util.List;
import domain.model.Role;
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
public class RoleServiceImplTest {
    
    public RoleServiceImplTest() {
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
     * Test of createNewRole method, of class RoleServiceImpl.
     */
    @Test
    public void testCreateNewRole() throws Exception {
        System.out.println("createNewRole");
        Role role = null;
        RoleServiceImpl instance = new RoleServiceImpl();
        Role expResult = null;
        Role result = instance.createNewRole(role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExistingRoles method, of class RoleServiceImpl.
     */
    @Test
    public void testGetExistingRoles() throws Exception {
        System.out.println("getExistingRoles");
        RoleServiceImpl instance = new RoleServiceImpl();
        List<Role> expResult = null;
        List<Role> result = instance.getExistingRoles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRole method, of class RoleServiceImpl.
     */
    @Test
    public void testDeleteRole() throws Exception {
        System.out.println("deleteRole");
        Role role = null;
        RoleServiceImpl instance = new RoleServiceImpl();
        boolean expResult = false;
        boolean result = instance.deleteRole(role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
