/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vidyadhar epili
 */
@WebServlet(urlPatterns = {"/admin_control"})
public class admin_control extends HttpServlet {

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
        HttpSession session = request.getSession();
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Morning Star Transports</title>");   
            out.println("<link rel='stylesheet' href='admin_control.css'>");
            out.println("<script src=\"https://code.jquery.com/jquery-1.10.2.js\"></script>\n" +
"        <script src=\"navbar.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='nav-placeholder'></div>");
            if(session.getAttribute("admin_log")=="Y"){
            Connection con=null;
            try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/transport_company","root","root");
            }
            catch(Exception e){
                System.out.println(e);
            }
            if(request.getParameter("admin_control").equals("user_data")){
                try{
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from user_data");
                String name,age, contact, email;
                out.println("<br><h1>All the user login details are as follows:</h1><br>");
                out.println("<table border='1'>");
                out.print("<tr><th>Name</th><th>Age</th><th>Contact</th><th>Email</th>");
                while(rs.next()){
                    name=rs.getString(2);
                    age=rs.getString(3);
                    contact=rs.getString(4);
                    email=rs.getString(5);
                    out.print("<tr><td>"+name+"</td><td>"+age+"</td><td>"+contact+"</td><td>"+email+"</td>");
                }
                out.println("</table>");
                }
                catch(Exception e){
                    System.out.println("Exception: "+e);
                }
            }
            else if(request.getParameter("admin_control").equals("bus_data")){
                try{
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from bus_details");
                String name,source,end,bus_num,person_count,comp,price,date;
                out.println("<br><h1>All the bus ticket details are as follows:</h1><br>");
                out.println("<table border='1'>");
                out.print("<tr><th>Name</th><th>Source</th><th>End</th><th>Bus_Num</th><th>No. of Tickets</th><th>Compartment</th><th>Price</th><th>Date</th>");
                while(rs.next()){
                    name=rs.getString(2);
                    source=rs.getString(3);
                    end=rs.getString(4);
                    bus_num=rs.getString(5);
                    person_count=rs.getString(6);
                    comp=rs.getString(7);
                    price=rs.getString(8);
                    date=rs.getString(9);
                    out.print("<tr><td>"+name+"</td><td>"+source+"</td><td>"+end+"</td><td>"+bus_num+"</td><td>"+person_count+"</td><td>"+comp+"</td><td>"+price+"</td><td>"+date+"</td>");
                }
                out.println("</table>");
                }
                catch(Exception e){
                    System.out.println("Exception: "+e);
                }
            }
            else if(request.getParameter("admin_control").equals("airway_data")){
                try{
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from airway_details");
                String name,source,end,person_count,comp,price,seat_no,date,takeoff,landing;
                out.println("<br><h1>All the airway ticket details are as follows:</h1><br>");
                out.println("<table border='1'>");
                out.print("<tr><th>Name</th><th>Source</th><th>End</th><th>No. of Tickets</th><th>Class</th><th>Price</th><th>Seat Number</th><th>Date</th><th>Takeoff Time</th><th>Landing Time</th>");
                while(rs.next()){
                    name=rs.getString(2);
                    source=rs.getString(3);
                    end=rs.getString(4);
                    person_count=rs.getString(5);
                    comp=rs.getString(6);
                    price=rs.getString(7);
                    seat_no=rs.getString(8);
                    date=rs.getString(9);
                    takeoff=rs.getString(10);
                    landing=rs.getString(11);
                    out.print("<tr><td>"+name+"</td><td>"+source+"</td><td>"+end+"</td><td>"+person_count+"</td><td>"+comp+"</td><td>"+price+"</td><td>"+seat_no+"</td><td>"+date+"</td><td>"+takeoff+"</td><td>"+landing+"</td>");
                }
                out.println("</table>");
                }
                catch(Exception e){
                    System.out.println("Exception: "+e);
                }
            }
            else if(request.getParameter("admin_control").equals("feedback_data")){
                try{
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from feedback");
                String name,bus,air,desc;
                out.println("<br><h1>All the feedbacks from users are as follows:</h1><br>");
                out.println("<table border='1'>");
                out.print("<tr><th>Name</th><th>Bus Feedback</th><th>Airway Feedback</th><th>Description</th>");
                while(rs.next()){
                    name=rs.getString(1);
                    bus=rs.getString(2);
                    air=rs.getString(3);
                    desc=rs.getString(4);
                    out.print("<tr><td>"+name+"</td><td>"+bus+"</td><td>"+air+"</td><td>"+desc+"</td>");
                }
                out.println("</table>");
                }
                catch(Exception e){
                    System.out.println("Exception: "+e);
                }
            }
            }
            else{
                out.println("<script>");
                out.println("alert('You need to login first!');");
                out.println("location.replace('login.html')");
                out.println("</script>");
            }
            out.println("</body>");
            out.println("</html>");
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
