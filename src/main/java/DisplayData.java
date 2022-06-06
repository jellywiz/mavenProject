import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisplayData extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String driver= "com.mysql.jdbc.Driver";
        String dbname = "studentcourse";
        String url = "jdbc:mysql://localhost:8080/"+dbname+"?";
        String namee="root";
        String pass="";
         Class.forName(driver);
             
             Connection conn = DriverManager.getConnection(url,namee,pass);
             
             Statement sen = conn.createStatement();
            
            String om = "select * from student ";
            
             ResultSet ans = sen.executeQuery(om);
             
            
            HttpSession session=request.getSession(false);
            
             Student student = (Student)session.getAttribute("student");  
              om= "select * from course where cid = " + student.getstid();
                     ans = sen.executeQuery(om);
                     while(ans.next())
                     {
                         out.println("<br>");
                         out.println("<br>");
                          out.println("<br>");
                          
                         String coursen = ans.getString("cname");
                         
                         
                         out.print(student.get_stid()+ " " + coursen);
                         
                         
                         
                     }
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Display Data</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Display at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
