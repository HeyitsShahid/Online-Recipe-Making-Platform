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

@WebServlet("/UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    // Handles the registration POST request
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // Insert the user details into the database
            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                out.println("<html><body>");
                out.println("<h1>Registration Successful!</h1>");
                out.println("<a href='login.html'>Login</a>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h1>Error during registration!</h1>");
                out.println("<a href='registration.html'>Try Again</a>");
                out.println("</body></html>");
            }
        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            out.println("</body></html>");
        }
    }
}
