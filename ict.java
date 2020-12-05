/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
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
@WebServlet(urlPatterns = {"/ict"})
public class ict extends HttpServlet {
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
    boolean confirm = true;

    
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
    
    public void confirm_handled_by(String job_id, PrintWriter out){
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
                System.out.println(rs.getString("handled_by"));
                out.println("<br/><br/>Handled by:<font color='red'>"+rs.getString("handled_by")+"</font><br/>");
                out.println("Work Done:<font color='red'>"+rs.getString("work_done")+"</font><br/>");
                out.println("Category:<font color='red'>"+rs.getString("category")+"</font><br/>");
                out.println("Priority:<font color='red'>"+rs.getString("priority")+"</font><br/>");
                confirm =false;
            }
            state = true;
            //conn.close();

        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());

            state = false;
        };
    
    }

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
      
        try ( PrintWriter  out = response.getWriter()) {
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

        
                String job_id = request.getParameter("job_id");
                out.println( "<br/><br/>"+"Information on job with Job ID: <font color='red'> " + job_id + "</font><br/>");

                try {
                    ResultSet rs = retrieve(job_id);

                   
                    out.println("Office Unit: " + office_unitz + "<br/>");
                    out.println("HOD Name: " + namez + "<br/>");
                    out.println("Equipment: " + equipmentz + "<br/>");
                    out.println("Nature of Fault: " + nature_of_faultz + "<br/>");
                    out.println("User: <font color='red'>" + userz + "</font><br/>");
                    //System.out.println("ict_position "+ict_position);
                    out.println("Time: " + timez + "<br/>");
                    out.println("ID: " + idz + "<br/>");
                    out.println("Phone Extension: " + phone_extensionz + "<br/>");
                    out.println("Date: " + datez + "<br/>");
                    confirm_handled_by( job_id,  out);
                } catch (Exception e) {
                }

                
                if(confirm){
                    
                out.println("<form method=\"post\" action=\"ict2\">\n"
                        + "                                                                        <h4><br/><br/><br/>Information and Communication Technology Unit </h4>\n"
                        + "									<div class=\"12u$\">\n"
                        + ""
                        + "										<div class=\"select-wrapper\">\n"
                        + "											<select name=\"category\" id=\"category\">\n"
                        + "												<option value=\"\">- Category -</option>\n"
                        + "												<option value=\"Hardware Network Support\">Hardware/Network Support</option>\n"
                        + "												<option value=\"Application Support\">Application Support</option>\n"
                        + "												<option value=\"Development Support\">Development Support</option>\n"
                        + "												<option value=\"Telecommunications Support\">Telecommunications Support</option>\n"
                        + "											</select>\n"
                        + "										</div>\n"
                        + "									</div>\n"
                        + "                                                                        <br/><br/>\n"
                        + "									<div class=\"4u 12u$(small)\">\n"
                        + "										<input type=\"radio\" id=\"priority-low\" name=\"priority\" value=\"Low Priority\" checked>\n"
                        + "										<label for=\"priority-low\">Low Priority</label>\n"
                        + "									</div>\n"
                        + "									<div class=\"4u 12u$(small)\">\n"
                        + "										<input type=\"radio\" id=\"priority-normal\" name=\"priority\" value=\"Normal Priority\">\n"
                        + "										<label for=\"priority-normal\">Normal Priority</label>\n"
                        + "									</div>\n"
                        + "									<div class=\"4u$ 12u$(small)\">\n"
                        + "										<input type=\"radio\" id=\"priority-high\" name=\"priority\" value=\"High Priority\">\n"
                        + "										<label for=\"priority-high\">High Priority</label>\n"
                        + "									</div>\n"
                        + "                                                                        <br/><br/>\n"
                        + "                                                                        \n"
                        + "									<div class=\"6u 12u$(small)\">\n"
                        + "										\n"
                        + "										<label for=\"copy\">Work Done</label>\n"
                        + "									</div>\n"
                        + "                                                                        \n"
                        + "                                                                        <div class=\"12u$\">\n"
                        + "										<textarea name=\"message\" id=\"message\" placeholder=\"Enter work done here\" rows=\"6\"></textarea>\n"
                        + "									</div>\n"
                        + "                                                                        \n"
                        + "									<div class=\"6u$ 12u$(small)\">\n"
                        + "										\n"
                        + "										<label for=\"human\">Handled by</label>\n"
                        + "									</div>\n"
                        + "                                                                        <div class=\"12u$\">\n"
                        + "										<div class=\"select-wrapper\">\n"
                        + "											<select name=\"handled_by\" id=\"category\">\n"
                        + "												\n"
                        + "												<option value=\"Farouk\">Farouk</option>\n"
                        + "												<option value=\"Eugene\">Eugene</option>\n"
                        + "												<option value=\"Chinda\">Chinda</option>\n"
                        + "												\n"
                        + "											</select>\n"
                        + "										</div>\n"
                        + "									</div>\n"
                        + "                                                                        <br/>\n"
                        +"     <div class=\"12u$\">\n"
                        + "										<ul class=\"actions\">\n"
                        
                        + "	<input type=\"hidden\" name=\"id\" value="
                        +idz+ "										<li><input type=\"submit\" value=\"Save\" class=\"special\" /></li>\n"
                        + "											<li><input type=\"reset\" value=\"Clear\" /></li>\n"
                        + "                                                                                       "
                        + "										</ul>\n"
                        + "									</div>\n"									
                        
                );}
                
                confirm =true;
                
                try{
                conn.close();}
                catch(Exception e){}
                
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
