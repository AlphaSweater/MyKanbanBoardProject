package com.alphasweater.MyUser;
/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST********
---------------------------------------------------------------------------------------------------------------------*/

/**
 * The Login class provides methods for user login functionality.
 */
public class MyUserLoginClass {
    private static String status;

    // Constants for error messages
    private static final String ERROR_LOGIN_FAILED = "Username or password incorrect, please try again or sign up if you do not have an account already.";

    /**
     * Retrieves the login status message.
     *
     * @return The login status message.
     */
    public static String getStatus() {
        return status;
    }

    /**
     * Attempts to log in a user.
     *
     * @param tryUserName The username to log in.
     * @param tryPassWord The password to log in.
     * @return True if the login is successful, false otherwise.
     */
    public static boolean logInUser(String tryUserName, String tryPassWord,String[][] userDatabase) {

        // Iterate through each user in the database
        for (int i = 1; i < userDatabase.length; i++) {
            String testUserName = userDatabase[i][0];
            String testPassWord = userDatabase[i][1];

            // If the username and password match, set the current user and welcome message
            if (testUserName.equals(tryUserName) && testPassWord.equals(tryPassWord)) {
                MyUserClass.currentUser = new MyUserClass(i, userDatabase[i][2], userDatabase[i][3], userDatabase[i][0], userDatabase[i][1]);
                status = "Welcome " + MyUserClass.currentUser.getUserFirstName() + " " + MyUserClass.currentUser.getUserLastName() + ", it is great to see you.";

                return true;
            }
        }

        // Set error message for login failure
        status = ERROR_LOGIN_FAILED;

        // Return false to indicate login failure
        return false;
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//