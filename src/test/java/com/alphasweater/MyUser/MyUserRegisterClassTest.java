package com.alphasweater.MyUser;
/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
---------------------------------------------------------------------------------------------------------------------*/

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Unit tests for the SignUp class.
 */
public class MyUserRegisterClassTest {
    //----------------------------------------------------------------------------------------------------------------//
    /**
     * Tests the registerUser method.
     */
    @Test
    public void testRegisterUser() {
        // Set up a sample user database
        String[][] userDatabase = {
                {"us_r1", "P@ssword1", "John", "Doe"},
                {"us_r2", "P@ssword2", "Jane", "Smith"},
                {"us_r3", "P@ssword3", "Alice", "Johnson"}
        };

        // Test for a valid registration
        String result1 = MyUserRegisterClass.registerUser(true,"us_r4", "Password123!", "Tom", "Hanks", userDatabase);
        assertTrue("Registration should succeed for valid input.", MyUserRegisterClass.getIsRegistered());
        assertEquals("Welcome Tom Hanks, it is great to have you join us.", result1);

        // Test for an invalid username
        String result2 = MyUserRegisterClass.registerUser(true,"us", "Password123!", "Invalid", "Username", userDatabase);
        assertFalse("Registration should fail for an invalid username.", MyUserRegisterClass.getIsRegistered());
        assertEquals("Invalid Username. The username must contain an underscore (_) and be no more than 5 characters long.", result2);

        // Test for an invalid password
        String result3 = MyUserRegisterClass.registerUser(true,"us_r5", "password", "Invalid", "Password", userDatabase);
        assertFalse("Registration should fail for an invalid password.", MyUserRegisterClass.getIsRegistered());
        assertEquals("Invalid Password. The password must contain a capital letter (A, B, C), a number (1, 2, 3), a special character (#, &, !), and be at least 8 characters long.", result3);

        // Test for an existing username
        String result4 = MyUserRegisterClass.registerUser(true,"us_r2", "Password123!", "Username", "Exists", userDatabase);
        assertFalse("Registration should fail for an existing username.", MyUserRegisterClass.getIsRegistered());
        assertEquals("This Username already exists. Please try again with a different Username.", result4);
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//