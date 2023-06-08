package com.alphasweater.MyUtil;
/* Author: Chad Fairlie
*  Pseudonym: AlphaSweater
*  Student Number: ST10269509
---------------------------------------------------------------------------------------------------------------------*/

/**
 * The Authentication class provides methods for username and password validation.
 */
public class MyAuthenticationClass {

    // Define special characters that are required in a strong password
    private static final String SPECIAL_CHARS = "~!@#$%^&*+-/.,\\{}[]();:?<>=\"'`_";

    // Define numeric characters that are required in a strong password
    private static final String NUMBER_CHARS = "1234567890";

    // Define uppercase characters that are required in a strong password
    private static final String CAPITAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Define the minimum length required for a strong password
    private static final int VALID_PASSWORD_LENGTH = 8;

    // Define the maximum length allowed for a username
    private static final int MAX_USERNAME_LENGTH = 5;

    /**
     * Checks if the username exists in the user database.
     *
     * @param inUsername the username to check
     * @return true if the username exists, false otherwise
     */
    public static boolean checkUserNameExists(String inUsername, String[][] userDatabase) {

        // Iterate through each user in the database
        for (int i = 1; i < userDatabase.length; i++) {
            String testUserName = userDatabase[i][0];
            if (testUserName.equals(inUsername)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the input username is valid.
     *
     * @param testUserName the username to check
     * @return true if the username is valid, false otherwise
     */
    public static boolean checkUserName(String testUserName) {

        // Initialize a boolean flag to check if the username has an underscore character
        boolean hasUnderscore = false;

        // Loop through each character in the username
        for (int i = 0; i < testUserName.length(); i++) {
            char n = testUserName.charAt(i);

            // Check if the current character is an underscore
            if (n == '_') {
                // If an underscore is found, set the flag to true and break out of the loop
                hasUnderscore = true;
                break;
            }
        }

        // Check if the username length is less than or equal to the maximum allowed length
        boolean isLengthValid = testUserName.length() <= MAX_USERNAME_LENGTH;

        // If the username contains an underscore and the length is valid, return true
        return hasUnderscore && isLengthValid;
    }

    /**
     * Checks if the input password is strong enough.
     *
     * @param testPassword the password to check
     * @return true if the password is strong enough, false otherwise
     */
    public static boolean checkPasswordComplexity(String testPassword) {

        // Initialize boolean flags to check if the password contains the required characters
        boolean hasSpecialChar = false;
        boolean hasNumberChar = false;
        boolean hasCapitalChar = false;

        // Loop through each character in the password
        for (int i = 0; i < testPassword.length(); i++) {
            char p = testPassword.charAt(i);

            // Check if the current character is a special character
            if (SPECIAL_CHARS.indexOf(p) != -1) {
                hasSpecialChar = true;
            }
            // Check if the current character is a numeric character
            else if (NUMBER_CHARS.indexOf(p) != -1) {
                hasNumberChar = true;
            }
            // Check if the current character is an uppercase character
            else if (CAPITAL_CHARS.indexOf(p) != -1) {
                hasCapitalChar = true;
            }
        }

        // Check if the password length is at least the required length
        boolean isLengthValid = testPassword.length() >= VALID_PASSWORD_LENGTH;

        // If the password contains at least one special character, one numeric character, 
        // one uppercase character, and the length is valid, return true
        return hasSpecialChar && hasNumberChar && hasCapitalChar && isLengthValid;
    }


    /**
     * Method to test CheckUserNameExists.
     * This method is used for testing purposes.
     *
     * @param inUsername the username to check
     * @return true if the username is valid, false otherwise
     */
    public static boolean toTestCheckUserNameExists(String inUsername,String[][] userDatabase) {
        // Iterate through each user in the database
        for (int i = 1; i < userDatabase.length; i++) {
            String testUserName = userDatabase[i][0];
            if (testUserName.equals(inUsername)) {
                return true;
            }
        }
        return false;
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//