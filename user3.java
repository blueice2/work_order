/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NNAMDI
 */
@WebServlet(urlPatterns = {"/user3"})
public class user3 extends HttpServlet {
        static Connection conn;
    static boolean state;
    static ResultSet rs;

    static String comment;
     static String id;
     static String  name;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     
                     public static boolean insert(PrintWriter out,String comment, String id, String name
    ) {
        // For inserting from the store handler form into the database i.e. store_handler 
        try {
            //create a mysql connection
            create_mysql_connection();
            
        DateTimeFormatter date_dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter time_dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        
            

     

            // Sql query
            String query = "insert into user_2 (comments ,id, name)"
                    + "values (?,?,?)";

            // Use prepared statement to set the ball roling
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, comment);
            preparedStmt.setString(2, id);
           preparedStmt.setString(3, name);
          
             preparedStmt.execute();
        out.println("Data saved ");
            
            conn.close();

            state = true;
        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());
            out.println(e);
            state = false;
        }
        return state;
    }// End of insert
                     
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
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
                              out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\"> <head> <meta charset=\"UTF-8\"> <title>Work Order by NSPMC IT LAGOS</title>");
            out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" /><meta name=\"description\" content=\"\" />");
            out.println("<meta name=\"keywords\" content=\"\" />");
            out.println("<script src=\"js/jquery.min.js\"></script><script src=\"js/skel.min.js\"></script><script src=\"js/skel-layers.min.js\"></script>");
            out.println("<script src=\"js/init.js\"></script>");
            out.println("<noscript>");
            out.println("<link rel=\"stylesheet\" href=\"css/skel.css\" />\n"
                    + "			<link rel=\"stylesheet\" href=\"css/style.css\" />\n"
                    + "			<link rel=\"stylesheet\" href=\"css/style-xlarge.css\" />\n"
                    + "		</noscript>"
                    + "</head>\n"
                    + "	<body class=\"landing\">\n"
                    + "\n"
                    + "		<!-- Header -->\n"
                    + "			<header id=\"header\">\n"
                    + "				<h1><a href=\"index.html\">IT LAGOS</a></h1>\n"
                    + "				<nav id=\"nav\">\n"
                    + "					<ul><li><a href=\"index.html\">Home</a></li>\n"
                    + "						<li><a href=\"generic.html\">Generic</a></li>\n"
                    + "						<li><a href=\"elements.html\">Elements</a></li>\n"
                    + "					</ul>\n"
                    + "				</nav>\n"
                    + "			</header>\n"
                    + "\n"
            );
            
                        comment= request.getParameter("comment");
                        id= request.getParameter("id");
                        name  = request.getParameter("names");
                        
                       
            
             insert(out,comment,id,name);
            
                                                    out.println(
                    "		<!-- Footer -->\n"
                    + "			<footer id=\"footer\">\n"
                    + "				<div class=\"container\">\n"
                    + "					<div class=\"row\">\n"
                    + "					\n"
                    + "						\n"
                    + "						<section class=\"4u$ 12u$(medium) 12u$(small)\">\n"
                    + "							<h3>Contact Us</h3>\n"
                    + "							<ul class=\"icons\">\n"
                    + "								<li><a href=\"#\" class=\"icon rounded fa-twitter\"><span class=\"label\">Twitter</span></a></li>\n"
                    + "								<li><a href=\"#\" class=\"icon rounded fa-facebook\"><span class=\"label\">Facebook</span></a></li>\n"
                    + "								<li><a href=\"#\" class=\"icon rounded fa-pinterest\"><span class=\"label\">Pinterest</span></a></li>\n"
                    + "								<li><a href=\"#\" class=\"icon rounded fa-google-plus\"><span class=\"label\">Google+</span></a></li>\n"
                    + "								<li><a href=\"#\" class=\"icon rounded fa-linkedin\"><span class=\"label\">LinkedIn</span></a></li>\n"
                    + "							</ul>\n"
                    + "							<ul class=\"tabular\">\n"
                    + "								<li>\n"
                    + "									<h3>IT NSPM Lagos </h3>\n"
                    + "									Victoria Island, Lagos<br>\n"
                    + "									\n"
                    + "								</li>\n"
                    + "								\n"
                    + "							</ul>\n"
                    + "						</section>\n"
                    + "					</div>\n"
                    + "					<ul class=\"copyright\">\n"
                    + "						<li>&copy; Untitled. All rights reserved.</li>\n");
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
