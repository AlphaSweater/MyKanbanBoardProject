import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The SignUp class handles user registration.
 */
public class SignUp {
    private static boolean isRegistered;
    // These are error messages that will be returned if input is invalid
    private static final String INVALID_USERNAME_MESSAGE = "Invalid Username. The username must contain an underscore (_) and be no more than 5 characters long.";
    private static final String INVALID_PASSWORD_MESSAGE = "Invalid Password. The password must contain a capital letter (A, B, C), a number (1, 2, 3), a special character (#, &, !), and be at least 8 characters long.";
    private static final String USERNAME_EXISTS_MESSAGE = "This Username already exists. Please try again with a different Username.";

    /**
     * Retrieves the registration status.
     *
     * @return True if the registration was successful, false otherwise.
     */
    public static boolean getIsRegistered() {
        return isRegistered;
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
    public static String registerUser(String newUserName, String newPassWord, String newFirstName, String newLastName) {
        // Check if input is valid
        String error = checkInputValidity(newUserName, newPassWord);
        if (error != null) {
            // Return error message if input is invalid
            isRegistered = false;
            return error;
        } else {
            // Write username and password to file if input is valid
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Database.getFileName(), true))) {
                writer.write("\n" + newUserName + "||" + newPassWord + "||" + newFirstName + "||" + newLastName);
                // Return welcome message if registration is successful
                isRegistered = true;
                return "Welcome " + newFirstName + " " + newLastName + ", it is great to have you join us.";
            } catch (IOException ex) {
                // Provide user-friendly error message if an error occurs while writing to the file
                isRegistered = false;
                return "An error occurred while registering. Please try again later.";
            }
        }
    }

    /**
     * Checks the validity of the input for the registerUser method.
     *
     * @param userName The username to check.
     * @param passWord The password to check.
     * @return An error message if input is invalid, null otherwise.
     */
    private static String checkInputValidity(String userName, String passWord) {
        // Check username validity
        if (!Authentication.checkUserName(userName)) {
            return INVALID_USERNAME_MESSAGE;
        }

        // Check password validity
        if (!Authentication.checkPasswordComplexity(passWord)) {
            return INVALID_PASSWORD_MESSAGE;
        }

        // Check is username already exists
        if (Authentication.checkUserNameExists(userName)) {
            return USERNAME_EXISTS_MESSAGE;
        }

        // Input is valid
        return null;
    }

    /**
     * This method is a copy of the registerUser method, but with the code that writes to the file commented out.
     * It is used for testing so that the file is not written to each time the method is tested.
     *
     * @param newUserName   The new user's username.
     * @param newPassWord   The new user's password.
     * @param newFirstName  The new user's first name.
     * @param newLastName   The new user's last name.
     * @param userDatabase  The database containing existing users' information.
     * @return A welcome message if registration is successful (without writing to file), an error message otherwise.
     */
    public static String toTestRegisterUser(String newUserName, String newPassWord, String newFirstName, String newLastName,String[][] userDatabase) {
        // Check if input is valid
        String error = toTestCheckInputValidity(newUserName, newPassWord,userDatabase);
        if (error != null) {
            // Return error message if input is invalid
            isRegistered = false;
            return error;
        } else {
            // Return a welcome message if registration is successful (without writing to file)
            isRegistered = true;
            return "Welcome " + newFirstName + " " + newLastName + ", it is great to have you join us.";
        }

    }

    private static String toTestCheckInputValidity(String userName, String passWord, String[][] userDatabase) {
        // Check username validity
        if (!Authentication.checkUserName(userName)) {
            // Return error message for invalid username
            return INVALID_USERNAME_MESSAGE;
        }

        // Check password validity
        if (!Authentication.checkPasswordComplexity(passWord)) {
            // Return error message for invalid password
            return INVALID_PASSWORD_MESSAGE;
        }

        if (Authentication.toTestCheckUserNameExists(userName, userDatabase)) {
            // Return error message if username already exists in the user database
            return USERNAME_EXISTS_MESSAGE;
        }

        // Input is valid
        return null;
    }
}