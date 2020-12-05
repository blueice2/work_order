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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NNAMDI
 */
@WebServlet(urlPatterns = {"/user2"})
public class user2 extends HttpServlet {
    String id;
    static Connection conn;
    static boolean state;
    static ResultSet rs;
    
    static String namez;
    static String office_unitz;
    static String equipmentz;
    static String nature_of_faultz;
    static String userz;
    static String phone_extensionz;
    static String datez;
    static String timez;
    static String idz;
    static String category;
    static String priority;
    static String work_done;
    static String handled_by;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
            //Retrieve 
    public static ResultSet retrieve(String job_id) {

        try {
            //create a mysql connection
            create_mysql_connection();

            // Sql query
            String query = "SELECT * FROM `user_department` where id = ?";

            // Use prepared statement to set the ball roling
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, job_id);

            rs = preparedStmt.executeQuery();
            System.out.println("Reached here");

            while (rs.next()) {
                System.out.println(rs.getString("name"));
                namez = rs.getString("name");
                office_unitz = rs.getString("office_unit");
                equipmentz = rs.getString("equipment");
                nature_of_faultz = rs.getString("nature_of_fault");
                userz = rs.getString("user");
                phone_extensionz = rs.getString("phone_extension");;
                datez = rs.getString("date");
                timez = rs.getString("time");
                idz = rs.getString("id");
            }
            state = true;
            //conn.close();

        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());

            state = false;
        };
        return rs;

    } //End of retrieve
    
    
                //Retrieve IT information 
    public static ResultSet retrieve_it(String job_id) {

        try {
            //create a mysql connection
            create_mysql_connection();

            // Sql query
            String query = "SELECT * FROM `ict` where id = ?";

            // Use prepared statement to set the ball roling
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, job_id);

            rs = preparedStmt.executeQuery();
            System.out.println("Reached here");

            while (rs.next()) {
                
                priority = rs.getString("priority");
                work_done = rs.getString("work_done");
                handled_by = rs.getString("handled_by");
                category = rs.getString("category");
            
            }
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
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
                    String prioritys ="";
                    String work_dones="";
                    String handled_bys="";
                    String categorys="";
                    
        try (PrintWriter out = response.getWriter()) {
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
            
            id= request.getParameter("job_id");
            String job_id = request.getParameter("job_id");
            
                            try {
                    ResultSet rs = retrieve(job_id);
                    conn.close();
                   
                    out.println( "<br/>"+"<br/>"+"Office Unit: " + office_unitz + "<br/>");
                    out.println("HOD Name: " + namez + "<br/>");
                    out.println("Equipment: " + equipmentz + "<br/>");
                    out.println("Nature of Fault: " + nature_of_faultz + "<br/>");
                    out.println("User: <font color='red'>" + userz + "</font><br/>");
                    //System.out.println("ict_position "+ict_position);
                    out.println("Time: " + timez + "<br/>");
                    out.println("ID: " + idz + "<br/>");
                    out.println("Phone Extension: " + phone_extensionz + "<br/>");
                    out.println("Date: " + datez + "<br/>");
                } catch (Exception e) {
                    out.print(e);
                }
                
                //out.print("<br/><font size=3, color='red'>IT Activity Section<br/></font>");
                 
                    
                                try {
                    ResultSet rsr = retrieve_it(job_id);
                    //conn.close();
                   
                    
                      while (rs.next()) {
                prioritys = rsr.getString("priority");
                work_dones = rsr.getString("work_done");
                handled_bys = rsr.getString("handled_by");
                categorys = rsr.getString("category");
                
                      }
                      
                   // out.println("Priority: " + prioritys + "<br/>");
                    
                   // out.println("Handled by: " + handled_bys + "<br/>");
                   // out.println("Work Done: " + work_dones + "<br/>");
                   // out.println("Category: " + categorys + "<br/>"+"<br/>");
                   
                } catch (Exception e) {
                    out.print(e);
                }
            
                            out.println("    <section>\n" +
"                                                        " +
"                                                         <h5><br/><br/>Fault/Request OK</h5>\n" +
"							<form method=\"post\" action=\"user3\">\n" +
"								<div class=\"4u 12u$(small)\">\n" +
"										<input type=\"radio\" id=\"yes\" name=\"yes\">\n" +
"										<label for=\"yes\">Yes</label>\n" +
"									</div>\n" +
"									<div class=\"4u$ 12u$(small)\">\n" +
"										<input type=\"radio\" id=\"no\" name=\"yes\">\n" +
"										<label for=\"no\">No</label>\n" +
"									</div>\n" +
"                                                            <br/>\n" +
"                                                            <div class=\"12u$\">\n" +
"										<textarea name=\"comment\" id=\"comment\" placeholder=\"Comments if any\" rows=\"6\"></textarea>\n" +
"									</div>\n" +
"                                                            <div class=\"6u$ 12u$(xsmall)\">\n" +
"										<input type=\"text\" name=\"names\" id=\"email\" value=\"\"  placeholder=\"Name\"  />\n" +
"									</div>\n" +
                                         
"                                                            \n" +
"                                                            <div class=\"12u$\">\n" +
"										<ul class=\"actions\">\n" +
"											<li><input type=\"submit\" value=\"Save\" class=\"special\" /></li>\n" +
"											" +
"                                                                                     " +
"										</ul>\n" +
"									</div>\n" +
"              <input type=\"hidden\" name=\"id\" id=\"email\" value= "+id+""  + "/>"
                                    + "                                          </form>\n" +
"                                                                    </section>");
               // ict_position = 1;
            
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
