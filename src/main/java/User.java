
/**
 * Represents a user in the system.
 */
public class User {
    private int userID;
    private String userFirstName;
    private String userLastName;
    private String userUserName;
    private String userPassWord;

    /**
     * Represents the currently logged-in user.
     */
    public static User currentUser;

    /**
     * Constructs a User object with the provided information.
     *
     * @param userID        The unique identifier of the user.
     * @param userFirstName The first name of the user.
     * @param userLastName  The last name of the user.
     * @param userUsername  The username of the user.
     * @param userPassword  The password of the user.
     */
    public User(int userID, String userFirstName, String userLastName, String userUsername, String userPassword) {
        // Initialize the user object with provided values
        this.userID = userID;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userUserName = userUsername;
        this.userPassWord = userPassword;
    }

    // Getter methods for retrieving user information

    public int getUserID() {
        // Returns the unique identifier of the user
        return userID;
    }

    public String getUserFirstName() {
        // Returns the first name of the user
        return userFirstName;
    }

    public String getUserLastName() {
        // Returns the last name of the user
        return userLastName;
    }

    public String getUserUserName() {
        // Returns the username of the user
        return userUserName;
    }

    public String getUserPassWord() {
        // Returns the password of the user
        return userPassWord;
    }

    // Setter methods for modifying user information

    public void setUserFirstName(String userFirstName) {
        // Sets the first name of the user
        this.userFirstName = userFirstName;
    }

    public void setUserLastName(String userLastName) {
        // Sets the last name of the user
        this.userLastName = userLastName;
    }

    public void setUserUserName(String userUserName) {
        // Sets the username of the user
        this.userUserName = userUserName;
    }

    public void setUserPassWord(String userPassWord) {
        // Sets the password of the user
        this.userPassWord = userPassWord;
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//