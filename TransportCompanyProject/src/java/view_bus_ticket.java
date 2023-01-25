/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
@WebServlet(urlPatterns = {"/view_bus_ticket"})
public class view_bus_ticket extends HttpServlet {
    public String name, person, source, end, comp, price, bus_num;
    long millis=System.currentTimeMillis();  
    java.sql.Date date=new java.sql.Date(millis);  
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
            name=session.getAttribute("name").toString();
            price=session.getAttribute("bus_price").toString();
            end=session.getAttribute("bus_end").toString();
            source=session.getAttribute("bus_source").toString();
            comp=session.getAttribute("bus_comp").toString();
            person=session.getAttribute("bus_person").toString();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet view_bus_ticket</title>");
            bus_num = request.getParameter("bus_num");
            if(insert_bus_details()){
            out.println("<script>");
            out.println("alert('Ticket booked successfully!')");  
            out.println("</script>");
            out.println(
"       <link rel='stylesheet' href='ticket.css'>\n" +
"    </head>\n" +
"    <body class='bus_ticket'>\n" +
"        <div class='bus_no'>Bus Number:"+bus_num+"</div>\n" +
"    <center>\n" +
"        <table class='bus_table'>\n" +
"            <tr>\n" +
"                <th colspan='2' style='text-decoration: underline;'>Morning Star Transports</th>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <th>Name</th>\n" +
"                <th>Number of Tickets</th>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td>"+name+"</td>\n" +
"                <td>"+person+"</td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td><br></td>\n" +
"                <td><br></td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <th>Source Stop</th>\n" +
"                <th>Compartment</th>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td>"+source+"</td>\n" +
"                <td>"+comp+"</td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td><br></td>\n" +
"                <td><br></td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <th>Destination</th>\n" +
"                <th>Ticket Price</th>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td>"+end+"</td>\n" +
"                <td>"+price+"</td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td></td>\n" +
"                <th style='text-align: left'>Date</th>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td></td>\n" +
"                <td style='text-align: left'>"+date+"</td>\n" +
"            </tr>\n" +
"        </table>\n" +
"        </center>"
        );
            }
            else{
                out.println("<script>");
                out.println("alert('Unfortunately ticket was not booked!')");
                out.println("location.href='bus.html'");
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
public boolean insert_bus_details(){  
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");    
            System.out.println("Welcome");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/transport_company","root","root");
            System.out.println("Connection Created");
            String s = "insert into bus_details(name, source, end, bus_num, person_count, compartment, price, date) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(s);
            ps.setString(1,name);
            ps.setString(2, source);
            ps.setString(3, end);
            ps.setString(4, bus_num);
            ps.setString(5, person);
            ps.setString(6, comp);
            ps.setString(7, price);
            ps.setDate(8,date);
            ps.execute();
            con.close();
            return true;
        }
        catch(Exception e){
            System.out.println("Exception "+e);
        }
        return false;
    }
}
