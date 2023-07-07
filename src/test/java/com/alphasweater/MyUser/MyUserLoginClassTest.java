package com.alphasweater.MyUser;

/*
 * Author: Chad Fairlie
 * Pseudonym: AlphaSweater
 * Student Number: ST10269509
 */

import com.alphasweater.MyGUI.MyLoginWorkerClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Login class.
 */
public class MyUserLoginClassTest {
    private MyUserLoginClass testLoginUserWorker;
    private MyLoginWorkerClass testLoginWorker;
    private String[][] userDatabase;

    @Before
    public void setUp() {
        // Create the necessary objects and set up the user database before each test
        testLoginUserWorker = new MyUserLoginClass();
        testLoginWorker = new MyLoginWorkerClass();
        testLoginUserWorker.setLoginWorker(testLoginWorker);

        // Set up a sample user database
        userDatabase = new String[][]{
                {"username1", "password1", "John", "Doe"},
                {"username2", "password2", "Jane", "Smith"},
                {"username3", "password3", "Alice", "Johnson"}
        };
    }

    @Test
    public void testLogInUser_validUsernameAndPassword_ReturnsTrue() {
        boolean result = testLoginUserWorker.logInUser("username2", "password2", userDatabase);

        assertTrue("Login should succeed for a valid username and password.", result);
        assertEquals("Welcome Jane Smith, it is great to see you.", testLoginUserWorker.getStatus());
    }

    @Test
    public void testLogInUser_incorrectUsername_ReturnsFalse() {
        boolean result = testLoginUserWorker.logInUser("username4", "password1", userDatabase);

        assertFalse("Login should fail for an incorrect username.", result);
        assertEquals("Username or password incorrect, please try again or sign up if you do not have an account already.", testLoginUserWorker.getStatus());
    }

    @Test
    public void testLogInUser_incorrectPassword_ReturnsFalse() {
        boolean result = testLoginUserWorker.logInUser("username3", "password4", userDatabase);

        assertFalse("Login should fail for an incorrect password.", result);
        assertEquals("Username or password incorrect, please try again or sign up if you do not have an account already.", testLoginUserWorker.getStatus());
    }
}

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//