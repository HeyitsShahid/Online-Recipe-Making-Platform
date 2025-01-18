import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    // Displays the user profile
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        String email = (String) session.getAttribute("userEmail");  // Assume session stores user email

        if (email == null) {
            out.println("<h1>Please login to view your profile!</h1>");
            out.println("<a href='login.html'>Login</a>");
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // Retrieve user details from the database
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String userEmail = resultSet.getString("email");

                out.println("<html><body>");
                out.println("<h1>User Profile</h1>");
                out.println("<p><strong>Username:</strong> " + username + "</p>");
                out.println("<p><strong>Email:</strong> " + userEmail + "</p>");
                out.println("<form action='UpdateProfileServlet' method='POST'>");
                out.println("<label for='username'>Update Username:</label>");
                out.println("<input type='text' id='username' name='username' value='" + username + "' required>");
                out.println("<br><br>");
                out.println("<label for='password'>Update Password:</label>");
                out.println("<input type='password' id='password' name='password' required>");
                out.println("<br><br>");
                out.println("<button type='submit'>Update Profile</button>");
                out.println("</form>");
                out.println("</body></html>");
            } else {
                out.println("<h1>User not found!</h1>");
            }
        } catch (Exception e) {
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
        }
    }
}
