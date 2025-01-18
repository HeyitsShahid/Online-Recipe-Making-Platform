import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Annotation-based configuration for the servlet
@WebServlet("/RecipeServlet")
public class RecipeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handles GET requests (e.g., viewing recipes)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>View Recipes</h1>");
        out.println("<p>This is where recipes will be listed.</p>");
        out.println("<a href='index.html'>Go Back</a>");
        out.println("</body></html>");
    }

    // Handles POST requests (e.g., adding recipes)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");

        out.println("<html><body>");
        out.println("<h1>Recipe Added</h1>");
        out.println("<p><strong>Name:</strong> " + name + "</p>");
        out.println("<p><strong>Ingredients:</strong> " + ingredients + "</p>");
        out.println("<a href='index.html'>Add Another Recipe</a>");
        out.println("</body></html>");
    }
}
