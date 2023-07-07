package com.alphasweater.MyUser;

/*
 * Author: Chad Fairlie
 * Pseudonym: AlphaSweater
 * Student Number: ST10269509
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the SignUp class.
 */
public class MyUserRegisterClassTest {
    private MyUserRegisterClass testRegistrationWorker;
    private String[][] userDatabase;
    //----------------------------------------------------------------------------------------------------------------//
    @Before
    public void setUp() {
        // Create the necessary objects and set up the user database before each test
        testRegistrationWorker = new MyUserRegisterClass();
        userDatabase = new String[][]{
                {"us_r1", "P@ssword1", "John", "Doe"},
                {"us_r2", "P@ssword2", "Jane", "Smith"},
                {"us_r3", "P@ssword3", "Alice", "Johnson"}
        };
    }
    //----------------------------------------------------------------------------------------------------------------//
    @After
    public void tearDown() {
        // Clean up after each test
        testRegistrationWorker.setIsRegistered(false);
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testRegisterUser_validRegistration() {
        String result = testRegistrationWorker.registerUser(true, "us_r4", "Password123!", "Tom", "Hanks", userDatabase);

        assertTrue("Registration should succeed for valid input.", testRegistrationWorker.getIsRegistered());
        assertEquals("Welcome Tom Hanks, it is great to have you join us.", result);
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testRegisterUser_invalidUsername() {
        String result = testRegistrationWorker.registerUser(true, "us", "Password123!", "Invalid", "Username", userDatabase);

        assertFalse("Registration should fail for an invalid username.", testRegistrationWorker.getIsRegistered());
        assertEquals("Invalid Username. The username must contain an underscore (_) and be no more than 5 characters long.", result);
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testRegisterUser_invalidPassword() {
        String result = testRegistrationWorker.registerUser(true, "us_r5", "password", "Invalid", "Password", userDatabase);

        assertFalse("Registration should fail for an invalid password.", testRegistrationWorker.getIsRegistered());
        assertEquals("Invalid Password. The password must contain a capital letter (A, B, C), a number (1, 2, 3), a special character (#, &, !), and be at least 8 characters long.", result);
    }
    //----------------------------------------------------------------------------------------------------------------//
    @Test
    public void testRegisterUser_existingUsername() {
        String result = testRegistrationWorker.registerUser(true, "us_r2", "Password123!", "Username", "Exists", userDatabase);

        assertFalse("Registration should fail for an existing username.", testRegistrationWorker.getIsRegistered());
        assertEquals("This Username already exists. Please try again with a different Username.", result);
    }
}

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//