package com.alphasweater.MyUser;

/**
 * The Login class provides methods for user login functionality.
 */
public class MyUserLogin {
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
    public static boolean logInUser(String tryUserName, String tryPassWord) {
        // Get the user database from the Database class
        String[][] userDatabase = MyUserDAO.getUserDatabase();

        // Iterate through each user in the database
        for (int i = 1; i < userDatabase.length; i++) {
            String testUserName = userDatabase[i][0];
            String testPassWord = userDatabase[i][1];

            // If the username and password match, set the current user and welcome message
            if (testUserName.equals(tryUserName) && testPassWord.equals(tryPassWord)) {
                MyUser.currentUser = new MyUser(i, userDatabase[i][2], userDatabase[i][3], userDatabase[i][0], userDatabase[i][1]);
                status = "Welcome " + MyUser.currentUser.getUserFirstName() + " " + MyUser.currentUser.getUserLastName() + ", it is great to see you.";

                return true;
            }
        }

        // Set error message for login failure
        status = ERROR_LOGIN_FAILED;

        // Return false to indicate login failure
        return false;
    }

    /**
     * Tests user login with the given username and password against the provided user database.
     * This method is used for testing purposes.
     *
     * @param tryUserName   The username to log in.
     * @param tryPassWord   The password to log in.
     * @param userDatabase  The user database to check against.
     * @return True if the login is successful, false otherwise.
     */
    public static boolean toTestLogInUser(String tryUserName, String tryPassWord, String[][] userDatabase) {
        for (int j = 1; j < userDatabase.length; j++) {
            String testUserName = userDatabase[j][0];
            String testPassWord = userDatabase[j][1];

            // If the username and password match, set the current user and welcome message
            if (testUserName.equals(tryUserName) && testPassWord.equals(tryPassWord)) {
                // User object called currentUser is created here and can now be called from anywhere in the program.
                MyUser.currentUser = new MyUser(j, userDatabase[j][2], userDatabase[j][3], userDatabase[j][0], userDatabase[j][1]);
                status = "Welcome " + MyUser.currentUser.getUserFirstName() + " " + MyUser.currentUser.getUserLastName() + ", it is great to see you.";
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