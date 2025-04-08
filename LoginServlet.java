import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user credentials from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple authentication (username: admin, password: admin123)
        if ("admin".equals(username) && "admin123".equals(password)) {
            // If credentials are correct, display welcome message
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Welcome, " + username + "!</h1>");
            out.println("</body></html>");
        } else {
            // If credentials are incorrect, show an error
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Invalid credentials. Please try again.</h1>");
            out.println("</body></html>");
        }
    }
}
