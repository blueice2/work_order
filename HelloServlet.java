/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.println;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NNAMDI
 */
@WebServlet(urlPatterns = {"/save"})
public class HelloServlet extends HttpServlet {

    String user_department = "user";
    String ict = "ict";
    String head_ict = "head_ict";
    String user_confirm = "user_confirm";
    String user_confirm_first = "user_confirm_first";
    String user_department_confirm = "";
    int ict_position = 0;
    int head_ict_position = 0;
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
    ArrayList<String> it_presonnel = new ArrayList<String>();
    
    ArrayList<String> id_of_entry = new ArrayList<String>();

 
    //Retrieve 
    public static ResultSet retrieve(String job_id) {

        try {
            //create a mysql connection
            //create_mysql_connection();

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

    //For the header
    public void header(PrintWriter out) {
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
    }

    //For the footer
    public void footer(PrintWriter out) {
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
        if (user_department.equals("user")) {
            out.println("<h3>Repair and Maintenance Work Order Form Wilson</h3>\n");
        }
        out.println("<li>Design: <a href=\"#\">TEMPLATED</a></li>\n"
                + "						<li>Images: <a href=\"#\">Unsplash</a></li>\n");

        out.println("</ul>\n"
                + "				</div>\n"
                + "			</footer>\n"
                + "\n"
                + "	</body>\n"
                + "</html>");
        //out.println("</html>");
    }

    public void user_confirm_first(PrintWriter out) {
        out.println("<br/><br/>Hello, please enter the job ID of the job you wish to certify.<br/>  <form method=\"post\" action=\"user2\">\n\n\n\n<br/><br/> <br/> "
                + "<div class=\"6u$ 12u$(xsmall)\">\n <input type=\"text\" name=\"job_id\" id=\"email\" value=\"\" placeholder=\"Enter your Job ID Here\" />\n"
                + "									</div>\n"
                + "   <div class=\"12u$\">\n"
                + "										<ul class=\"actions\">\n"
                + "											<li><input type=\"submit\" value=\"Retrieve\" class=\"special\" /></li>\n"
                + "											"
                + ""
                + "										</ul>\n"
                + "									</div>\n" + ""
                + "</form>\n");
    }

    public void user(PrintWriter out) {
        out.println("<br/><br/><br/> </section><h3>Repair and Maintenance Work Order Form</h3>\n"
                + "                                                        \n"
                + "                                                        <section>\n"
                + "                                                        <h4>User Department</h4>\n"
                + "							<form method=\"post\" action=\"user\">\n"
                + "								<div class=\"row uniform 50%\">\n"
                + "									<div class=\"6u 12u$(xsmall)\">\n"
                + "										<input type=\"text\" name=\"office_unit\" id=\"name\" value=\"\" placeholder=\"Office/Unit\" />\n"
                + "									</div>\n"
                + "									<div class=\"6u$ 12u$(xsmall)\">\n"
                + "										<input type=\"text\" name=\"equipment\" id=\"email\" value=\"\" placeholder=\"Equipment\" />\n"
                + "									</div>\n"
                + "                                                                    	<div class=\"6u$ 12u$(xsmall)\">\n"
                + "										<input type=\"text\" name=\"nature_of_fault\" id=\"email\" value=\"\" placeholder=\"Nature of Fault/Request\" />\n"
                + "									</div>\n"
                + "                                                                    <div class=\"6u$ 12u$(xsmall)\">\n"
                + "										<input type=\"text\" name=\"user\" id=\"email\" value=\"\" placeholder=\"User/ID Number\" />\n"
                + "									</div>\n" + ""
                + "<div class=\"6u$ 12u$(xsmall)\">\n <input type=\"text\" name=\"name\" id=\"email\" value=\"\" placeholder=\"HOD's Name\" />\n"
                + "									</div>\n"
                + "<div class=\"6u$ 12u$(xsmall)\">\n <input type=\"text\" name=\"phone_extension\" id=\"email\" value=\"\" placeholder=\"Extension\" />\n"
                + "									</div>\n"
                + "                                                                    <div class=\"12u$\">\n"
                + "										<ul class=\"actions\">\n"
                + "											<li><input type=\"submit\" value=\"Send\" class=\"special\" /></li>\n"
                + "											<li><input type=\"reset\" value=\"Clear\" /></li>\n"
                + ""
                + "										</ul>\n"
                + "									</div>\n"
                + "                                                        </form>\n"
                + "                                                                    </section>\n"
                + "                                                                    <br/> <br/> <br/> <br/>\n"
                + "                                                                  \n"
                + "                                                                    \n"
                + "                                                                    \n"
                + "                                                                 \n"
                + "                                                                    <section>\n"
        );
    }

    //For ICT position =0 to be handled by
    public void ict_and_ict_position(PrintWriter out) {
        try {

            // Sql query
            String query = "SELECT user_department.name, user_department.office_unit, "
                    + "user_department.user,user_department.id,user_department.date,ict.handled_by FROM user_department "
                    + " inner join ict on user_department.id =ict.id order by user_department.id desc";

            // Use prepared statement to set the ball roling
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();
            System.out.println("Reached here");

            out.println("<!-- Table -->\n"
                    + "						<section>\n"
                    + "							<h3>Handled Jobs</h3>\n"
                    + "							\n"
                    + "							<div class=\"table-wrapper\">\n"
                    + "								<table>\n"
                    + "									<thead>\n"
                    + "										<tr>\n"
                    + "											<th>Name</th>\n"
                    + "											<th>ID</th>\n"
                    + "											<th>Department</th>\n"
                    + " <th>Date</th>\n"
                    + "                                                                            <th>Handled By</th>\n"
                    + "										</tr>\n"
                    + "									</thead>\n  <tbody>\n");
            while (rs.next()) {

                out.println(
                        "										<tr>\n"
                        + "											<td>" + rs.getString("user") + "</td>\n"
                        + "											<td>" + rs.getString("id") + "</td>\n"
                        + "											<td>" + rs.getString("office_unit") + "</td>\n "
                        + "                                                                             <td>" + rs.getString("date") + "</td>\n"
                        + " <td>" + rs.getString("handled_by") + "</td>\n"
                        + "										</tr>\n");

            }

            out.println("</tbody>\n"
                    + "									<tfoot>\n"
                    + "										<tr>\n"
                    + "											<td colspan=\"2\"></td>\n"
                    + "											\n"
                    + "										</tr>\n"
                    + "									</tfoot>\n"
                    + "								</table>\n</div>\n</section>");
            state = true;
            //conn.close();

        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());

            state = false;
        };

        
//Unhandled
        try {
            //create a mysql connectdeion
            // create_mysql_connection();
            
          //SQL query for to be handled by column only
            ArrayList<String> id = new ArrayList<String>(100);
            ArrayList<String> it_personnel_name = new ArrayList<String>(100);
            
            try{
            String querys = "select * from to_be_handled_by   ";
            PreparedStatement preparedStmts = conn.prepareStatement(querys);
            ResultSet rss = preparedStmts.executeQuery();
          
           
            while(rss.next()){
                // System.out.println("ID "+": "+ rss.getString("id")+ " Personnel Name:"+": "+ rss.getString("it_personnel_name")+"<br/>");
            id.add(rss.getString("id"));
            it_personnel_name.add(rss.getString("it_personnel_name"));
            } 
            
            } catch(Exception e){//out.println(""+e.printStackTrace(s));
            }
            
          
           
           
            for(int j = 0; j<id.size(); j++)
                System.out.println("ID "+j+": "+ id.get(j)+ " Personnel Name:"+j+": "+ it_personnel_name.get(j)+"<br/>");
            
             /* **/
            // Sql query
            String query = "SELECT user_department.name, user_department.office_unit, user_department.user,user_department.id,user_department.date,ict.handled_by FROM user_department  inner join ict on user_department.id =ict.id ";

            query = "select * from user_department  left join ict on user_department.id = ict.id  "
                    // + "union "
                    //+ "select * from user_department right join ict on user_department.id = ict.id"
                    + " where ict.handled_by  is null order by user_department.id desc";
            // Use prepared statement to set the ball roling
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();
            System.out.println("Reached here");

            out.println("<!-- Table -->\n"
                    + "						<section>\n"
                    + "							<h3>UnHandled Jobs</h3>\n"
                    + "							\n"
                    + "							<div class=\"table-wrapper\">\n <table class=\"alt\">\n"
                    + "								<table>\n"
                    + "									<thead>\n"
                    + "										<tr>\n"
                    + "											<th>Name</th>\n"
                    + "											<th>ID</th>\n"
                    + "											<th>Department</th>\n"
                    + " <th>Date</th>\n"
                    + "                                                                            <th>Handled By</th> <th>To Be Handled By</th>\n"
                    + "										</tr>\n"
                    + "									</thead>\n  <tbody>\n");
            while (rs.next()) {

                out.println(
                        "										<tr>\n"
                        + "											<td>" + rs.getString("user") + "</td>\n"
                        + "											<td>" + rs.getString("id") + "</td>\n"
                        + "											<td>" + rs.getString("office_unit") + "</td>\n "
                        + "                                                                             <td>" + rs.getString("date") + "</td>\n"
                        + "                                                                             <td>" + rs.getString("handled_by") + "</td>\n"
                );
                               /*if(id.contains(rs.getString("id"))){
                                  
                               out.println( " <td>" + it_personnel_name.get(id.indexOf(rs.getString("id")))+"James" + "</td>\n");
                               }
                               
                               
                               
                               String search = rs.getString("id");
                               for(String str:id){
                                if(str.trim().contains(search)){
                                //out.println( " ");
                                out.println( " <td>" + it_personnel_name.get(id.indexOf(rs.getString("id")))+"Jane" + "</td>\n");
                                }
                               }
                                  */
                               
                               String search = rs.getString("id");
                               if(id.contains(search)){out.println( " <td>" + it_personnel_name.get(id.indexOf(rs.getString("id")))+" " + "</td>\n");}
                           out.println( "										</tr>\n");

            }

            out.println("</tbody>\n"
                    + "									<tfoot>\n"
                    + "										<tr>\n"
                    + "											<td colspan=\"2\"></td>\n"
                    + "											\n"
                    + "										</tr>\n"
                    + "									</tfoot>\n"
                    + "								</table>\n</div>\n</section>");
            state = true;
            conn.close();

        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());

            state = false;
        };

        out.println("IT Staff<br/>  <form method=\"post\" action=\"ict\">\n\n\n\n<br/><br/> <br/> "
                + "<div class=\"6u$ 12u$(xsmall)\">\n <input type=\"text\" name=\"job_id\" id=\"email\" value=\"\" placeholder=\"Enter Job ID Here\" />\n"
                + "									</div>\n"
                + "   <div class=\"12u$\">\n"
                + "										<ul class=\"actions\">\n"
                + "											<li><input type=\"submit\" value=\"Retrieve\" class=\"special\" /></li>\n"
                + "											"
                + ""
                + "										</ul>\n"
                + "									</div>\n" + ""
                + "</form>\n");
        // ict_position = 1;
    }

    //For ICT position =1
    public void ict_and_ict_position1(PrintWriter out, HttpServletRequest request) {
        String job_id = request.getParameter("job_id");
        out.println("<br/><br/>" + "Information on job with Job ID " + job_id + "<br/>");

        try {
            ResultSet rs = retrieve(job_id);

            /* out.println("HOD Name: " + namez + "<br/>");
                    out.println("Office Unit: " + office_unitz + "<br/>");
                    out.println("HOD Name: " + namez + "<br/>");
                    out.println("Equipment: " + equipmentz + "<br/>");
                    out.println("Nature of Fault: " + nature_of_faultz + "<br/>");
                    out.println("User: " + userz + "<br/>");
                    System.out.println("ict_position "+ict_position);
                    out.println("HOD Name: " + namez + "<br/>");
                    out.println("Phone Extension: " + phone_extensionz + "<br/>");
                    out.println("Date: " + datez + "<br/>");*/
        } catch (Exception e) {
        }

        out.println("<form method=\"post\" action=\"#\">\n"
                + "                                                                        <h4><br/><br/><br/>Information and Communication Technology Unit </h4>\n"
                + "									<div class=\"12u$\">\n"
                + ""
                + "										<div class=\"select-wrapper\">\n"
                + "											<select name=\"support\" id=\"category\">\n"
                + "												<option value=\"\">- Category -</option>\n"
                + "												<option value=\"1\">Hardware/Network Support</option>\n"
                + "												<option value=\"2\">Application Support</option>\n"
                + "												<option value=\"3\">Development Support</option>\n"
                + "												<option value=\"4\">Telecommunications Support</option>\n"
                + "											</select>\n"
                + "										</div>\n"
                + "									</div>\n"
                + "                                                                        <br/><br/>\n"
                + "									<div class=\"4u 12u$(small)\">\n"
                + "										<input type=\"radio\" id=\"priority-low\" name=\"priority\" checked>\n"
                + "										<label for=\"priority-low\">Low Priority</label>\n"
                + "									</div>\n"
                + "									<div class=\"4u 12u$(small)\">\n"
                + "										<input type=\"radio\" id=\"priority-normal\" name=\"priority\">\n"
                + "										<label for=\"priority-normal\">Normal Priority</label>\n"
                + "									</div>\n"
                + "									<div class=\"4u$ 12u$(small)\">\n"
                + "										<input type=\"radio\" id=\"priority-high\" name=\"priority\">\n"
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
                + "											<select name=\"support\" id=\"category\">\n"
                + "												\n"
                + "												<option value=\"1\">Farouk</option>\n"
                + "												<option value=\"2\">Eugene</option>\n"
                + "												<option value=\"3\">Chinda</option>\n"
                + "												\n"
                + "											</select>\n"
                + "										</div>\n"
                + "									</div>\n"
                + "                                                                        <br/>\n"
                + "									<div class=\"12u$\">\n"
        );
    }

    //For head ICT position =0
    public void head_ict_and_head_ict_position(PrintWriter out) {

        try {
            //create a mysql connectdeion
            //  create_mysql_connection();

            // Sql query
            String query = "SELECT user_department.name, user_department.office_unit, "
                    + "user_department.user,user_department.id,user_department.date,ict.handled_by FROM user_department "
                    + " inner join ict on user_department.id =ict.id order by user_department.id desc";

            // Use prepared statement to set the ball roling
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();
            System.out.println("Reached here");

            out.println("<!-- Table -->\n"
                    + "						<section>\n"
                    + "							<h3>Handled Jobs for Lagos ICT Head</h3>\n"
                    + "							\n"
                    + "							<div class=\"table-wrapper\">\n"
                    + "								<table>\n"
                    + "									<thead>\n"
                    + "										<tr>\n"
                    + "											<th>Name</th>\n"
                    + "											<th>ID</th>\n"
                    + "											<th>Department</th>\n"
                    + " <th>Date</th>\n"
                    + "                                                                            <th>Handled By</th>\n"
                    + "										</tr>\n"
                    + "									</thead>\n  <tbody>\n");
            while (rs.next()) {

                out.println(
                        "										<tr>\n"
                        + "											<td>" + rs.getString("user") + "</td>\n"
                        + "											<td>" + rs.getString("id") + "</td>\n"
                        + "											<td>" + rs.getString("office_unit") + "</td>\n "
                        + "                                                                             <td>" + rs.getString("date") + "</td>\n"
                        + " <td>" + rs.getString("handled_by") + "</td>\n"
                        + "										</tr>\n");

            }

            out.println("</tbody>\n"
                    + "									<tfoot>\n"
                    + "										<tr>\n"
                    + "											<td colspan=\"2\"></td>\n"
                    + "											\n"
                    + "										</tr>\n"
                    + "									</tfoot>\n"
                    + "								</table>\n</div>\n</section>");
            state = true;
            //conn.close();

        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());

            state = false;
        };

//Unhandled
        try {
            //create a mysql connectdeion
            create_mysql_connection();

            // Sql query
            String query = "SELECT user_department.name, user_department.office_unit, user_department.user,user_department.id,user_department.date,ict.handled_by FROM user_department  inner join ict on user_department.id =ict.id ";

            query = "select * from user_department  left join ict on user_department.id = ict.id  "
                    // + "union "
                    //+ "select * from user_department right join ict on user_department.id = ict.id"
                    + " where ict.handled_by  is null order by user_department.id desc";
            // Use prepared statement to set the ball roling
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            rs = preparedStmt.executeQuery();

            System.out.println("Reached here");

            out.println("<!-- Table -->\n"
                    + "						<form method=\"post\" action=\"allocate_job\"> <section>\n"
                    + "							<h3>UnHandled Jobs</h3>\n"
                    + "							\n"
                    + "							<div class=\"table-wrapper\">\n <table class=\"alt\">\n"
                    + "								<table>\n"
                    + "									<thead>\n"
                    + "										<tr>\n"
                    + "											<th>Name</th>\n"
                    + "											<th>ID</th>\n"
                    + "											<th>Department</th>\n"
                    + " <th>Date</th>\n"
                    + "                                                                            <th>To be handled By</th>\n"
                    + "										</tr>\n"
                    + "									</thead>\n  <tbody>\n");
            while (rs.next()) {
                try {
                    id_of_entry.add(rs.getString("id"));
                } catch (Exception e) {
                    out.println(e);
                }

                out.println(
                        "										<tr>\n"
                        + "											<td>" + rs.getString("user") + "</td>\n"
                        + "											<td>" + rs.getString("id") + "</td>\n"
                        + "											<td>" + rs.getString("office_unit") + "</td>\n "
                        + "                                                                             <td>" + rs.getString("date") + "</td>\n"
                        + " <td>"
                        + ""
                        + ""
                        + "<div class=\"4u$ 12u$(small)\">\n"
                        + "										<input type=\"radio\" id=\""
                        + "chinda" + rs.getString("id")
                        + "\" "
                        + "name=\""
                        + "priority" + rs.getString("id")
                        + " \" "
                        + "value=\"chinda\">\n"
                        + "										<label for=\""
                        + "chinda" + rs.getString("id")
                        + "\">Chinda</label>\n"
                        + "									</div>\n"
                        + "<div class=\"4u$ 12u$(small)\">\n"
                        + "										<input type=\"radio\" id=\""
                        + "farouk" + rs.getString("id")
                        + "\" "
                        + "name=\""
                        + "priority" + rs.getString("id")
                        + " \" "
                        + " value=\"farouk\">\n"
                        + "										<label for="
                        + "\"farouk" + rs.getString("id")
                        + "\">Farouk</label>\n"
                        + ""
                        + "<div class=\"4u$ 12u$(small)\">\n"
                        + "										<input type=\"radio\" id=\""
                        + "eugene" + rs.getString("id")
                        + "\""
                        + " name=\""
                        + "priority" + rs.getString("id")
                        + " \" "
                        + " value=\"eugene\">\n"
                        + "										<label for="
                        + "\"eugene" + rs.getString("id")
                        + "\">Eugene</label>\n"
                        + "									</div>\n"
                        + "									</div>\n"
                        + "</td>"
                        + ""
                        + "										</tr>\n");

                //add the values of the id to the arraylist
                // id_of_entry.add(rs.getString("id"));
            }

            //Serialize the arraylist to a file
            try {
                FileOutputStream fos = new FileOutputStream("hjy.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(id_of_entry);
                fos.close();
                oos.close();

            } catch (Exception e) {
            }

            out.println("</tbody>\n"
                    + "									<tfoot>\n"
                    + "										<tr>\n"
                    + "											<td colspan=\"2\"></td>\n"
                    + "											\n"
                    + "										</tr>\n"
                    + "									</tfoot>\n"
                    + "								</table>\n"
                    + "	<input type=\"hidden\" name=\"id_of_entry\" value="
                    + id_of_entry
                    + "<ul class=\"actions\">\n"
                    + "											<li><input type=\"submit\" value=\"Allocate Job\" class=\"special\" /></li>\n"
                    + "											"
                    + ""
                    + "										</ul>\n</div>\n</section></form>");
            state = true;
            conn.close();

        } catch (Exception e) {

            System.err.println("Got an exception");
            System.err.println(e.getMessage());

            state = false;
        };

        out.println("Head ICT  <br/><form method=\"post\" action=\"head_ict\">\n\n\n\n<br/><br/> <br/> "
                + "<div class=\"6u$ 12u$(xsmall)\">\n <input type=\"text\" name=\"job_id\" id=\"email\" value=\"\" placeholder=\"Enter Job ID Here\" />\n"
                + "									</div>\n"
                + "   <div class=\"12u$\">\n"
                + "										<ul class=\"actions\">\n"
                + "											<li><input type=\"submit\" value=\"Retrieve\" class=\"special\" /></li>\n"
                + "											"
                + ""
                + "										</ul>\n"
                + "									</div>\n" + ""
                + "</form>\n");
        // ict_position = 1;
    }

    public void head_ictert(PrintWriter out) {
        out.println("<textarea name=\"message\" id=\"message\" placeholder=\"Head ICT Remarks\" rows=\"6\"></textarea>\n"
                + "									</div>\n"
                + "									<div class=\"12u$\">\n"
                + "										<ul class=\"actions\">\n"
                + "											<li><input type=\"submit\" value=\"Save\" class=\"special\" /></li>\n"
                + "											<li><input type=\"reset\" value=\"Send\" /></li>\n"
                + "                                                                                       <li><input type=\"submit\" value=\"Load\" class=\"special\" /></li>\n"
                + "										</ul>\n"
                + "									</div>\n"
                + "								</div>\n"
                + "							</form>\n"
                + "						</section>");

        out.println(" <br/> <br/> <br/> <br/>                  \n"
                + "                                                        <section>\n"
                + "                                                        <h4>User Department</h4>\n"
                + "                                                         <h5>Fault/Request OK</h5>\n"
                + "							<form method=\"post\" action=\"#\">\n"
                + "								<div class=\"4u 12u$(small)\">\n"
                + "										<input type=\"radio\" id=\"yes\" name=\"yes\">\n"
                + "										<label for=\"yes\">Yes</label>\n"
                + "									</div>\n"
                + "									<div class=\"4u$ 12u$(small)\">\n"
                + "										<input type=\"radio\" id=\"no\" name=\"no\">\n"
                + "										<label for=\"no\">No</label>\n"
                + "									</div>\n"
                + "                                                            <br/>\n"
                + "                                                            <div class=\"12u$\">\n"
                + "										<textarea name=\"message\" id=\"message\" placeholder=\"Comments if any\" rows=\"6\"></textarea>\n"
                + "									</div>\n"
                + "                                                            <div class=\"6u$ 12u$(xsmall)\">\n"
                + "										<input type=\"text\" name=\"nature_of_fault\" id=\"email\" value=\"\" placeholder=\"Name\" />\n"
                + "									</div>\n"
                + "                                                            \n"
                + "                                                            <div class=\"12u$\">\n"
                + "										<ul class=\"actions\">\n"
                + "											<li><input type=\"submit\" value=\"Save\" class=\"special\" /></li>\n"
                + "											<li><input type=\"reset\" value=\"Send\" /></li>\n"
                + "                                                                                       <li><input type=\"submit\" value=\"Load\" class=\"special\" /></li>\n"
                + "										</ul>\n"
                + "									</div>\n"
                + "                                                        </form>\n"
                + "                                                                    </section>\n");
    }
    
     ArrayList<String> id ;
     ArrayList<String> it_personnel_name;
            
            
    public void  to_beHandledb_by(PrintWriter out){
        //SQL query for to be handled by column only
             id = new ArrayList<String>();
             it_personnel_name = new ArrayList<String>();
            
            try{
            String querys = "select * from to_be_handled_by   ";
            PreparedStatement preparedStmts = conn.prepareStatement(querys);
            ResultSet rss = preparedStmts.executeQuery();
          
           
            while(rss.next()){
                //out.println("ID "+": "+ rss.getString("id")+ " Personnel Name:"+": "+ rss.getString("it_personnel_name")+"<br/>");
            id.add(rss.getString("id"));
            it_personnel_name.add(rss.getString("it_personnel_name"));
            } 
            
            } catch(Exception e){//out.println(""+e.printStackTrace(s));
            }
            
          
           
           
            for(int j = 0; j<id.size(); j++){
               //out.println("ID "+j+": "+ id.get(j)+ " Personnel Name my man:"+j+": "+ it_personnel_name.get(j)+"<br/>");
            }
            
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          
            create_mysql_connection();
            
          to_beHandledb_by(out);
          
           header(out);

           
           //For user certification
            if (user_confirm_first.equals("user_confirm_first")) {
                user_confirm_first(out);

            }// end of user confirm_first

            if (user_confirm.equals("user_confirm")) {

            }// end of user confirm

            //user complain input
            if (user_department.equals("user")) {
                user(out);
            }

            //ICT Personnel Page
            if (ict.equals("ict") && ict_position == 0) {

                ict_and_ict_position(out);
            }

            
            if (ict.equals("ict") && ict_position == 1) {

               // ict_and_ict_position1(out, request);
            }
            
            //Head ICT Page
            if (head_ict.equals("head_ict") && head_ict_position == 0) {
                head_ict_and_head_ict_position(out);
            }

            
            if (head_ict.equals("head_ictert")) {
               // head_ictert(out);
            }

            footer(out);

        }
        try {
            conn.close();
        } catch (Exception e) {
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
