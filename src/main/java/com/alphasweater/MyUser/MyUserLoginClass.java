package com.alphasweater.MyUser;
/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
---------------------------------------------------------------------------------------------------------------------*/

import com.alphasweater.MyGUI.MyHomeWorkerClass;
import com.alphasweater.MyGUI.MyLoginWorkerClass;

/**
 * The MyUserLoginClass class provides methods for user login functionality.
 */
public class MyUserLoginClass {
    private String status;
    private MyLoginWorkerClass loginWorker;
    public void setLoginWorker(MyLoginWorkerClass loginWorker) {
        this.loginWorker = loginWorker;
    }

    /**
     * Retrieves the login status message.
     *
     * @return The login status message.
     */
    public String getStatus() {
        return status;
    }

    /**
     Default Constructor for MyUserLoginClass
     */
    public MyUserLoginClass(){
    }
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Attempts to log in a user.
     *
     * @param tryUserName The username to log in.
     * @param tryPassWord The password to log in.
     * @return True if the login is successful, false otherwise.
     */
    public boolean logInUser(String tryUserName, String tryPassWord, String[][] userDatabase) {
        // Iterate through each user in the database
        for (int i = 1; i < userDatabase.length; i++) {
            String testUserName = userDatabase[i][0];
            String testPassWord = userDatabase[i][1];

            // If the username and password match, set the current user and welcome message
            if (testUserName.equals(tryUserName) && testPassWord.equals(tryPassWord)) {
                MyUserClass loggedInUser = new MyUserClass(i, userDatabase[i][2], userDatabase[i][3], userDatabase[i][0], userDatabase[i][1]);
                status = "Welcome " + loggedInUser.getUserFirstName() + " " + loggedInUser.getUserLastName() + ", it is great to see you.";
                loginWorker.setCurrentUser(loggedInUser);

                return true;
            }
        }
        // Set error message for login failure
        // Constants for error messages
        String ERROR_LOGIN_FAILED = "Username or password incorrect, please try again or sign up if you do not have an account already.";
        status = ERROR_LOGIN_FAILED;
        // Return false to indicate login failure
        return false;
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//