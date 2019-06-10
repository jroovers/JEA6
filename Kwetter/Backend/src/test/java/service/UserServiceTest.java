/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.dao.UserDao;
import domain.dao.qualifiers.JPA;
import domain.model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import service.impl.UserServiceImpl;

/**
 *
 * @author Jeroen Roovers <jroovers>
 */
public class UserServiceTest {

    @Mock
    @JPA
    private UserDao mockDao;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of registerUser method, of class UserService.
     */
    @Test
    public void testRegisterUserNoPassword() {
        Mockito.when(mockDao.getByUsername(ArgumentMatchers.anyString())).thenReturn(null);

        System.out.println("registerUser - no password");
        String username = "";
        String password = "";
        User result = userService.registerUser(username, password);
        // Should be OK, verify that dao.save was called.
        Mockito.verify(mockDao, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
    }

    /**
     * Test of registerUser method, of class UserService. Happy flow.
     */
    @Test
    public void testRegisterUser() {
        // lets assume username is not taken
        Mockito.when(mockDao.getByUsername(ArgumentMatchers.anyString())).thenReturn(null);

        System.out.println("registerUser - happy flow");
        String username = "Frank";
        String password = "Password";
        User result = userService.registerUser(username, password);
        // Should be OK, verify that dao.save was called.
        Mockito.verify(mockDao, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
    }

    /**
     * Test of registerUser method, of class UserService. Should throw exception
     * if user already exists.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterDuplicateUser() {
        Mockito.when(mockDao.getByUsername(ArgumentMatchers.anyString())).thenReturn(new User("Henk", "Password"));

        System.out.println("registerUser - no password");
        String username = "Henk";
        String password = "Password";
        userService.registerUser(username, password);
    }
}
