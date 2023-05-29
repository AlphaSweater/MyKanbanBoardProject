import com.alphasweater.Authentication;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the Authentication class.
 */
public class AuthenticationTest {

    /**
     * Tests the checkUserName method.
     */
    @Test
    public void testCheckUserName() {
        // Test a valid username containing an underscore and no more than 5 characters
        assertTrue(Authentication.checkUserName("kyl_1"));

        // Test an invalid username containing no underscore
        assertFalse(Authentication.checkUserName("Kyl12"));

        // Test an invalid username longer than the maximum allowed length
        assertFalse(Authentication.checkUserName("test_user_longer_than_max"));
    }

    /**
     * Tests the checkPasswordComplexity method.
     */
    @Test
    public void testCheckPasswordComplexity() {
        // Test a valid password containing a capital letter, a number, a special character, and at least 8 characters
        assertTrue(Authentication.checkPasswordComplexity("Abc123#_"));

        // Test an invalid password without a capital letter
        assertFalse(Authentication.checkPasswordComplexity("abc123#_"));

        // Test an invalid password without a number
        assertFalse(Authentication.checkPasswordComplexity("ABCdef#_"));

        // Test an invalid password without a special character
        assertFalse(Authentication.checkPasswordComplexity("Abc12345"));

        // Test an invalid password that is too short
        assertFalse(Authentication.checkPasswordComplexity("Abcdef#"));
    }

    /**
     * Tests the checkUserNameExists method.
     */
    @Test
    public void testCheckUserNameExists() {
        // Set up a sample user database
        String[][] userDatabase = {
                {"username1", "password1"},
                {"username2", "password2"},
                {"username3", "password3"}
        };

        // Test an existing username
        boolean result1 = Authentication.toTestCheckUserNameExists("username2", userDatabase);
        assertTrue("The username 'username2' should exist in the database.", result1);

        // Test a non-existing username
        boolean result2 = Authentication.toTestCheckUserNameExists("username4", userDatabase);
        assertFalse("The username 'username4' should not exist in the database.", result2);
    }

}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//