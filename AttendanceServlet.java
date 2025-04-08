import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String date = request.getParameter("date");
        String status = request.getParameter("status");
        
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement pst = connection.prepareStatement("INSERT INTO attendance (student_id, date, status) VALUES (?, ?, ?)");
            pst.setInt(1, Integer.parseInt(studentId));
            pst.setDate(2, Date.valueOf(date));
            pst.setString(3, status);
            pst.executeUpdate();
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h3>Attendance successfully recorded!</h3>");
            out.println("</body></html>");
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h3>Error recording attendance.</h3>");
            out.println("</body></html>");
        }
    }
}
