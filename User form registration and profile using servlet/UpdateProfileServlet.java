import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    // Handles the profile update POST request
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Assume user email is available from session
        String email = (String) request.getSession().getAttribute("userEmail");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // Update the user details in the database
            String query = "UPDATE users SET username = ?, password = ? WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                out.println("<html><body>");
                out.println("<h1>Profile updated successfully!</h1>");
                out.println("<a href='UserProfileServlet'>View Profile</a>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h1>Error updating profile!</h1>");
                out.println("<a href='UserProfileServlet'>Try Again</a>");
                out.println("</body></html>");
            }
        } catch (Exception e) {
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
        }
    }
}
