
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class manage extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
               
     
        String driver= "com.mysql.jdbc.Driver";
        String dbname = "studentcourse";
        String url = "jdbc:mysql://localhost:8080/"+dbname+"?";
        String namee="root";
        String pass="";
       boolean found = false;
        
       
        
       
             Class.forName(driver);
             
             Connection conn = DriverManager.getConnection(url,namee,pass);
             Statement sen = conn.createStatement();
             
             
             String name = request.getParameter("email");
             String password = request.getParameter("pass");
             
             Student student = new Student();
             
             String om = "select * from student "; 
             ResultSet ans = sen.executeQuery(q);
        
             while(ans.next()){
                 if(name.equals(ans.getString("name") )
                         && password.equals(ans.getString("password")) ){
                     found = true;
                     student.set_name(ans.getString("name"));
                     student.set_stid(ans.getInt("stid"));
                     student.set_age(ans.getInt("age"));
                     student.set_address(ans.getString("address"));
                     student.set_pass(ans.getString("pass"));
                     student.set_userName(ans.getString("userName"));
                      HttpSession session=request.getSession();  
                      session.setAttribute("student", student);
                    
                     response.sendRedirect("DisplayData");

                    
                 }
                 
               
               }
             if(!ans.next() && found == false ){
                   RequestDispatcher req=request.getRequestDispatcher("index.jsp");
               req.include(request, response);
               out.print("<br>Wrong Username and Password");
             }
             
             
             
            
//             
//             if(rs.next()){
//                 student.setNAme(rs.getString("name"));
//             }
//             out.print(student.getNAme());
             
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
   

}
