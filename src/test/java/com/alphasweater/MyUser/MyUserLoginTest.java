package com.alphasweater.MyUser;/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST********
---------------------------------------------------------------------------------------------------------------------*/

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Login class.
 */
public class MyUserLoginTest {

    /**
     * Tests the logInUser method.
     */
    @Test
    public void testLogInUser() {
        // Set up a sample user database
        String[][] userDatabase = {
                {"username1", "password1", "John", "Doe"},
                {"username2", "password2", "Jane", "Smith"},
                {"username3", "password3", "Alice", "Johnson"}
        };

        // Test for correct username and password
        boolean result1 = MyUserLogin.logInUser("username2", "password2", userDatabase);
        assertTrue("Login should succeed for a valid username and password.", result1);
        assertEquals("Welcome Jane Smith, it is great to see you.", MyUserLogin.getStatus());

        // Test for incorrect username
        boolean result2 = MyUserLogin.logInUser("username4", "password1", userDatabase);
        assertFalse("Login should fail for an incorrect username.", result2);
        assertEquals("Username or password incorrect, please try again or sign up if you do not have an account already.", MyUserLogin.getStatus());

        // Test for incorrect password
        boolean result3 = MyUserLogin.logInUser("username3", "password4", userDatabase);
        assertFalse("Login should fail for an incorrect password.", result3);
        assertEquals("Username or password incorrect, please try again or sign up if you do not have an account already.", MyUserLogin.getStatus());
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//