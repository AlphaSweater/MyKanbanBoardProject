package com.alphasweater.MyUtil;

/*
 * Author: Chad Fairlie
 * Pseudonym: AlphaSweater
 * Student Number: ST********
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the Authentication class.
 */
public class MyAuthenticationClassTest {
    private String[][] userDatabase;
    //----------------------------------------------------------------------------------------------------------------//
    @Before
    public void setUp() {
        // Set up the user database before each test
        userDatabase = new String[][]{
                {"username1", "password1"},
                {"username2", "password2"},
                {"username3", "password3"}
        };
    }
    //----------------------------------------------------------------------------------------------------------------//
    @After
    public void tearDown() {
        // Clean up after each test
        userDatabase = null;
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testCheckUserName_validUsername() {
        assertTrue("Username 'kyl_1' should be considered valid.", MyAuthenticationClass.checkUserName("kyl_1"));
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testCheckUserName_invalidUsernameWithoutUnderscore() {
        assertFalse("Username 'Kyl12' should be considered invalid.", MyAuthenticationClass.checkUserName("Kyl12"));
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testCheckUserName_invalidUsernameTooLong() {
        assertFalse("Username 'test_user_longer_than_max' should be considered invalid.", MyAuthenticationClass.checkUserName("test_user_longer_than_max"));
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testCheckPasswordComplexity_validPassword() {
        assertTrue("Password 'Abc123#_' should be considered valid.", MyAuthenticationClass.checkPasswordComplexity("Abc123#_"));
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testCheckPasswordComplexity_invalidPasswordWithoutCapitalLetter() {
        assertFalse("Password 'abc123#_' should be considered invalid.", MyAuthenticationClass.checkPasswordComplexity("abc123#_"));
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testCheckPasswordComplexity_invalidPasswordWithoutNumber() {
        assertFalse("Password 'ABCdef#_' should be considered invalid.", MyAuthenticationClass.checkPasswordComplexity("ABCdef#_"));
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testCheckPasswordComplexity_invalidPasswordWithoutSpecialCharacter() {
        assertFalse("Password 'Abc12345' should be considered invalid.", MyAuthenticationClass.checkPasswordComplexity("Abc12345"));
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testCheckPasswordComplexity_invalidPasswordTooShort() {
        assertFalse("Password 'Abcdef#' should be considered invalid.", MyAuthenticationClass.checkPasswordComplexity("Abcdef#"));
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testCheckUserNameExists_existingUsername() {
        assertTrue("Username 'username2' should be considered existing in the user database.", MyAuthenticationClass.checkUserNameExists("username2", userDatabase));
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testCheckUserNameExists_nonExistingUsername() {
        assertFalse("Username 'username4' should be considered non-existing in the user database.", MyAuthenticationClass.checkUserNameExists("username4", userDatabase));
    }
}

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//