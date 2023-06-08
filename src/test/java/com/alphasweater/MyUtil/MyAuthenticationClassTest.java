package com.alphasweater.MyUtil;/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST********
---------------------------------------------------------------------------------------------------------------------*/

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the Authentication class.
 */
public class MyAuthenticationClassTest {
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Tests the checkUserName method.
     */
    @Test
    public void testCheckUserName() {
        // Test a valid username containing an underscore and no more than 5 characters
        assertTrue(MyAuthenticationClass.checkUserName("kyl_1"));

        // Test an invalid username containing no underscore
        assertFalse(MyAuthenticationClass.checkUserName("Kyl12"));

        // Test an invalid username longer than the maximum allowed length
        assertFalse(MyAuthenticationClass.checkUserName("test_user_longer_than_max"));
    }
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Tests the checkPasswordComplexity method.
     */
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testCheckPasswordComplexity() {
        // Test a valid password containing a capital letter, a number, a special character, and at least 8 characters
        assertTrue(MyAuthenticationClass.checkPasswordComplexity("Abc123#_"));

        // Test an invalid password without a capital letter
        assertFalse(MyAuthenticationClass.checkPasswordComplexity("abc123#_"));

        // Test an invalid password without a number
        assertFalse(MyAuthenticationClass.checkPasswordComplexity("ABCdef#_"));

        // Test an invalid password without a special character
        assertFalse(MyAuthenticationClass.checkPasswordComplexity("Abc12345"));

        // Test an invalid password that is too short
        assertFalse(MyAuthenticationClass.checkPasswordComplexity("Abcdef#"));
    }
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Tests the checkUserNameExists method.
     */
    @Test
    public void testCheckUserNameExists() {
        // Set up a sample user database
        String[][] userDatabase = {
                {"username1", "password1"},
                {"username2", "password2"},
                {"username3", "password3"}
        };

        // Test an existing username
        boolean result1 = MyAuthenticationClass.checkUserNameExists("username2", userDatabase);
        assertTrue("The username 'username2' should exist in the database.", result1);

        // Test a non-existing username
        boolean result2 = MyAuthenticationClass.checkUserNameExists("username4", userDatabase);
        assertFalse("The username 'username4' should not exist in the database.", result2);
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//