import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for the SignUp class.
 */
public class SignUpTest {

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
        String result1 = SignUp.toTestRegisterUser("us_r4", "Password123!", "Tom", "Hanks", userDatabase);
        assertTrue(SignUp.getIsRegistered(), "Registration should succeed for valid input.");
        assertEquals("Welcome Tom Hanks, it is great to have you join us.", result1);

        // Test for an invalid username
        String result2 = SignUp.toTestRegisterUser("us", "Password123!", "Invalid", "Username", userDatabase);
        assertFalse(SignUp.getIsRegistered(), "Registration should fail for an invalid username.");
        assertEquals("Invalid Username. The username must contain an underscore (_) and be no more than 5 characters long.", result2);

        // Test for an invalid password
        String result3 = SignUp.toTestRegisterUser("us_r5", "password", "Invalid", "Password", userDatabase);
        assertFalse(SignUp.getIsRegistered(), "Registration should fail for an invalid password.");
        assertEquals("Invalid Password. The password must contain a capital letter (A, B, C), a number (1, 2, 3), a special character (#, &, !), and be at least 8 characters long.", result3);

        // Test for an existing username
        String result4 = SignUp.toTestRegisterUser("us_r2", "Password123!", "Username", "Exists", userDatabase);
        assertFalse(SignUp.getIsRegistered(), "Registration should fail for an existing username.");
        assertEquals("This Username already exists. Please try again with a different Username.", result4);
    }
}