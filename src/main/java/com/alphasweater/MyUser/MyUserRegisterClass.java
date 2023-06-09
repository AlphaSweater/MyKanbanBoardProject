package com.alphasweater.MyUser;
/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
---------------------------------------------------------------------------------------------------------------------*/

import com.alphasweater.MyDatabases.MyDatabaseClass;
import com.alphasweater.MyUtil.MyAuthenticationClass;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The MyUserRegisterClass class handles user registration.
 */
public class MyUserRegisterClass {
    private boolean isRegistered;
    private final MyDatabaseClass databaseWorker = new MyDatabaseClass();

    /**
     * Retrieves the registration status.
     *
     * @return True if the registration was successful, false otherwise.
     */
    public boolean getIsRegistered() {
        return isRegistered;
    }
    public void setIsRegistered(boolean registered) {
        isRegistered = registered;
    }
    //----------------------------------------------------------------------------------------------------------------//

    /**
    Default Constructor for MyUserRegisterClass
     */
    public MyUserRegisterClass(){
    }
    /**
     * Registers a new user by checking the input validity, writing to a file, and returning a welcome message.
     *
     * @param newUserName  The new user's username.
     * @param newPassWord  The new user's password.
     * @param newFirstName The new user's first name.
     * @param newLastName  The new user's last name.
     * @return A welcome message if registration is successful, an error message otherwise.
     */
    public String registerUser(Boolean isTest, String newUserName, String newPassWord, String newFirstName, String newLastName, String[][] userDatabase) {
        // Check if input is valid
        String error = checkInputValidity(newUserName, newPassWord, userDatabase);
        if (error != null) {
            // Return error message if input is invalid
            isRegistered = false;
            return error;
        } else {
            if (!isTest) {
                // Write username and password to file if input is valid
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(databaseWorker.getUSERS_FILE_NAME(), true))) {
                    writer.write("\n" + newUserName + "||" + newPassWord + "||" + newFirstName + "||" + newLastName);
                    // Return welcome message if registration is successful
                    isRegistered = true;
                    return "Welcome " + newFirstName + " " + newLastName + ", it is great to have you join us.";
                } catch (IOException ex) {
                    // Provide a user-friendly error message if an error occurs while writing to the file
                    isRegistered = false;
                    return "An error occurred while registering. Please try again later.";
                }
            } else {
                // Return welcome message if registration is successful
                isRegistered = true;
                return "Welcome " + newFirstName + " " + newLastName + ", it is great to have you join us.";
            }
        }
    }
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Checks the validity of the input for the registerUser method.
     *
     * @param userName The username to check.
     * @param passWord The password to check.
     * @return An error message if input is invalid, null otherwise.
     */
    private String checkInputValidity(String userName, String passWord, String[][] userDatabase) {
        // Check username validity
        if (!MyAuthenticationClass.checkUserName(userName)) {
            // These are error messages that will be returned if input is invalid
            return "Invalid Username. The username must contain an underscore (_) and be no more than 5 characters long.";
        }

        // Check password validity
        if (!MyAuthenticationClass.checkPasswordComplexity(passWord)) {
            return "Invalid Password. The password must contain a capital letter (A, B, C), a number (1, 2, 3), a special character (#, &, !), and be at least 8 characters long.";
        }

        // Check is username already exists
        if (MyAuthenticationClass.checkUserNameExists(userName, userDatabase)) {
            return "This Username already exists. Please try again with a different Username.";
        }

        // Input is valid
        return null;
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//