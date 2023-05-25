import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Login class.
 */
public class LoginTest {

    /**
     * Tests the logInUser method.
     */
    @Test
    public void testLogInUser() {
        // Set up a sample user database
        String[][] userDatabase = {
                {"username1", "password1", "John", "Doe"},
                {"username2", "password2", "Jane", "Smith"},
                {"username3", "password3", "Alice", "Johnson"}
        };

        // Test for correct username and password
        boolean result1 = Login.toTestLogInUser("username2", "password2", userDatabase);
        assertTrue(result1, "Login should succeed for a valid username and password.");
        assertEquals("Welcome Jane Smith, it is great to see you.", Login.getStatus());

        // Test for incorrect username
        boolean result2 = Login.toTestLogInUser("username4", "password1", userDatabase);
        assertFalse(result2, "Login should fail for an incorrect username.");
        assertEquals("Username or password incorrect, please try again or sign up if you do not have an account already.", Login.getStatus());

        // Test for incorrect password
        boolean result3 = Login.toTestLogInUser("username3", "password4", userDatabase);
        assertFalse(result3, "Login should fail for an incorrect password.");
        assertEquals("Username or password incorrect, please try again or sign up if you do not have an account already.", Login.getStatus());
    }

}