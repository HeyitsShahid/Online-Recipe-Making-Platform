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

// Servlet Annotation for URL mapping
@WebServlet("/RecipeServlet")
public class RecipeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/recipe_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    /**
     * Handles GET requests (Retrieve and display recipes).
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Recipes</title></head><body>");
        out.println("<h1>All Recipes</h1>");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // SQL query to retrieve all recipes
            String query = "SELECT * FROM recipes";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Display recipes in an HTML table
            out.println("<table border='1' cellpadding='10'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Ingredients</th></tr>");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String ingredients = resultSet.getString("ingredients");
                out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + ingredients + "</td></tr>");
            }

            out.println("</table>");
        } catch (Exception e) {
            out.println("<p>Error retrieving recipes: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }

        out.println("<br><a href='index.html'>Add New Recipe</a>");
        out.println("</body></html>");
    }

    /**
     * Handles POST requests (Add a new recipe).
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // SQL query to insert a new recipe
            String query = "INSERT INTO recipes (name, ingredients) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, ingredients);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                out.println("<p>Recipe added successfully!</p>");
            } else {
                out.println("<p>Failed to add the recipe.</p>");
            }
        } catch (Exception e) {
            out.println("<p>Error adding recipe: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }

        out.println("<br><a href='index.html'>Add Another Recipe</a>");
        out.println("<br><a href='RecipeServlet'>View All Recipes</a>");
    }
}
