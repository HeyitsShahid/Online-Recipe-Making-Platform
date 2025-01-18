import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {
    private UserService userService;
    private UserDAO mockUserDAO;

    @Before
    public void setUp() throws Exception {
        // Mock UserDAO
        mockUserDAO = mock(UserDAO.class);
        userService = new UserService(mockUserDAO);
    }

    @Test
    public void testGetUserDetailsByEmail() {
        String email = "test@example.com";
        User mockUser = new User("TestUser", email);

        // Mock the DAO method to return a user
        when(mockUserDAO.getUserByEmail(email)).thenReturn(mockUser);

        // Test Service method
        User user = userService.getUserDetailsByEmail(email);

        assertNotNull(user);
        assertEquals("TestUser", user.getUsername());
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testGetUserDetailsByEmail_UserNotFound() {
        String email = "nonexistent@example.com";

        // Mock the DAO method to return null (user not found)
        when(mockUserDAO.getUserByEmail(email)).thenReturn(null);

        User user = userService.getUserDetailsByEmail(email);

        assertNull(user); // No user should be found
    }
}
