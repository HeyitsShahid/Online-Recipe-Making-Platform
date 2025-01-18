import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class UserDAOTest {
    private UserDAO userDAO;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Before
    public void setUp() throws Exception {
        // Mocking database connection and query execution
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        userDAO = new UserDAO();

        // Mock the interaction with the database
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void testGetUserByEmail() throws Exception {
        String email = "test@example.com";

        // Mock the result set returned by the query
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("username")).thenReturn("TestUser");
        when(resultSet.getString("email")).thenReturn(email);

        // Test DAO method
        User user = userDAO.getUserByEmail(email);

        assertNotNull(user);
        assertEquals("TestUser", user.getUsername());
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testGetUserByEmail_UserNotFound() throws Exception {
        String email = "nonexistent@example.com";

        // Simulate no result from the query
        when(resultSet.next()).thenReturn(false);

        User user = userDAO.getUserByEmail(email);

        assertNull(user); // No user should be found
    }
}
