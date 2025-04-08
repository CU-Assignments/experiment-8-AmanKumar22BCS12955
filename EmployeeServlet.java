import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("empId");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            Statement stmt = connection.createStatement();
            ResultSet rs;

            if (employeeId != null && !employeeId.isEmpty()) {
                // If search is requested, filter by employee ID
                PreparedStatement pst = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
                pst.setInt(1, Integer.parseInt(employeeId));
                rs = pst.executeQuery();
            } else {
                // Fetch all employees if no ID is provided
                rs = stmt.executeQuery("SELECT * FROM employees");
            }
            
            out.println("<html><body>");
            out.println("<h2>Employee List</h2>");
            out.println("<form action='' method='get'>");
            out.println("Search by Employee ID: <input type='text' name='empId' />");
            out.println("<input type='submit' value='Search' />");
            out.println("</form>");
            
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Position</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("name") + "</td><td>" + rs.getString("position") + "</td></tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
            
            connection.close();
        } catch (SQLException e) {
            out.println("<h3>Error connecting to database</h3>");
            e.printStackTrace();
        }
    }
}
