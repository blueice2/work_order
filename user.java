/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 *
 * @author NNAMDI
 */
@WebServlet(urlPatterns = {"/user"})
public class user extends HttpServlet {

    static Connection conn;
    static boolean state;
    static ResultSet rs;
    
        public static boolean insert(String office_unit, String equipment, String nature_of_fault,
            String user, String name, String phone_extension
            , String dates, String time
    ) {
        // For inserting from the store handler form into the database i.e. store_handler 
        try {
            //create a mysql connection
            create_mysql_connection();
            
        DateTimeFormatter date_dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter time_dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        
            

     

            // Sql query
            String query = "insert into user_department (office_unit ,equipment ,nature_of_fault,user,name,phone_extension,date,time)"
                    + "values (?,?,?,?,?,?,?,?)";

            // Use prepared statement to set the ball roling
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, office_unit);
            preparedStmt.setString(2, equipment);
            preparedStmt.setString(3, nature_of_fault);
            preparedStmt.setString(4, user);
            preparedStmt.setString(5, name);
            preparedStmt.setString(6, phone_extension);
          
            preparedStmt.setString(7, date_dtf.format(now));
            preparedStmt.setString(8, time_dtf.format(now));
            

            preparedStmt.execute();
            conn.close();

            state = true;
        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());
            
            state = false;
        }
        return state;
    }// End of insert
        
        
        //Retrieve 
    public static ResultSet retrieve() {

        try {
            //create a mysql connection
            create_mysql_connection();

            // Sql query
            String query = "SELECT * FROM `audit_first` group by date having date = max(date) ";

            // Use prepared statement to set the ball roling
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();
            state = true;
            //conn.close();

        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());

            state = false;
        };
        return rs;

    } //End of retrieve
    
    
      // Creates the connection to the database
    public static void create_mysql_connection() {
        try {
            //Select a jdbc driver which was added to the class path
            Class.forName("com.mysql.jdbc.Driver");

            //select the database - shop_floor
            String myUrl = "jdbc:mysql://localhost/work_order";
              //Create the connection
           conn = DriverManager.getConnection(myUrl, "root", "");
           
            
             //Office db
             //String myUrl = "jdbc:mysql://192.9.200.8/hakuna";
             //conn = DriverManager.getConnection(myUrl, "root", "karamazoff");

        } catch (Exception e) {
            System.err.println("Got an exception");
            System.err.println(e.getMessage());
        }

    }// End of create_mysql_connection()
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String office_unit = request.getParameter("office_unit");
        String equipment = request.getParameter("equipment");
        String nature_of_fault = request.getParameter("nature_of_fault");
        String user = request.getParameter("user");
        String name = request.getParameter("name");
        String phone_extension = request.getParameter("phone_extension");
        
        DateTimeFormatter date_dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter time_dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Now "+now);
        
        String dates = request.getParameter(date_dtf.format(now));
        String time = request.getParameter(time_dtf.format(now));
      
        
       insert( office_unit, equipment,  nature_of_fault,
          user,  name, phone_extension, 
            dates, time
    );
        
try (PrintWriter out = response.getWriter()) {
out.println("Your request from "+office_unit+" as regards "+equipment+" has been forwarded.<br/>Have a good day "+user+".");
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
