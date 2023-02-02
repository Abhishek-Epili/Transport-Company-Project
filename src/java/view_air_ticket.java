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
@WebServlet(urlPatterns = {"/view_air_ticket"})
public class view_air_ticket extends HttpServlet {
    String name, source, end, comp, date, person, price, takeoff_time, land_time, seat_no;
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
        PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */ 
            name = session.getAttribute("name").toString();
            source = session.getAttribute("air_source").toString();
            end = session.getAttribute("air_end").toString();
            comp = session.getAttribute("air_comp").toString();
            date = session.getAttribute("air_date").toString();
            price = session.getAttribute("air_price").toString();
            person = session.getAttribute("air_person").toString();
            takeoff_time = session.getAttribute("air_takeoff").toString();
            land_time = session.getAttribute("air_land").toString();
            seat_no = session.getAttribute("air_seat").toString();
            System.out.println(name+source+end+comp+date+price+person+takeoff_time+land_time+seat_no);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Morning Star Transports</title>");   
            if(insert_airway_details()){
            out.println("<script>");
            out.println("alert('Ticket booked successfully!')");  
            out.println("</script>");
            
            out.println(
"       <link rel='stylesheet' href='ticket.css'>\n" +
"    </head>\n" +
"    <body class='air_ticket'>\n" +
"       <h1 class='air_title'>MORNING STAR TRANSPORTS</h1>"+
"        <div class='air_row'>\n" +
"            <div class='air_column'>\n" +
"        <table class='air_table1'>\n" +
"            <tr>\n" +
"                <th>Name</th>\n" +
"                <th>Number of Tickets</th>\n" +
"                <th>Takeoff Time</th>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td>"+name+"</td>\n" +
"                <td>"+person+"</td>\n" +
"                <td>"+takeoff_time+"</td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td><br></td>\n" +
"                <td><br></td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <th>Source Stop</th>\n" +
"                <th>Class</th>\n" +
"                <th>Landing time</th>\n" +
"                <th>Seat No.</th>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td>"+source+"</td>\n" +
"                <td>"+comp+"</td>\n" +
"                <td>"+land_time+"</td>\n" +
"                <td>"+seat_no+"</td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td><br></td>\n" +
"                <td><br></td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <th>Destination</th>\n" +
"                <th>Ticket Price</th>\n" +
"                <th>Date</th>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td>"+end+"</td>\n" +
"                <td>"+price+"</td>\n" +
"                <td>"+date+"</td>\n" +
"            </tr>\n" +
"\n" +
"        </table>\n" +
"            </div>\n" +
"            <div class='air_column'>\n" +
"        <table class='air_table2'>\n" +
"            <tr>\n" +
"                <th>Name</th>\n" +
"                <th colspan='2'>Number of Tickets</th>\n" +
"                \n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td>"+name+"</td>\n" +
"                <td>"+person+"</td>\n" +
"                \n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td><br></td>\n" +
"                <td><br></td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <th>From</th>\n" +
"                <th>Class</th>\n" +
"                <th>Date</th>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td>"+source+"</td>\n" +
"                <td>"+comp+"</td>\n" +
"                <td>"+date+"</td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td><br></td>\n" +
"                <td><br></td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <th>Destination</th>\n" +
"                <th>Ticket Price</th>\n" +
"                <th>Seat No.</th>\n" +
"                \n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td>"+end+"</td>\n" +
"                <td>"+price+"</td>\n" +
"                <td>"+seat_no+"</td>\n" +
"                \n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td><br></td>\n" +
"                <td><br></td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <th >Takeoff Time</th>\n" +
"                <th>Landing time</th>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td >"+takeoff_time+"</td>\n" +
"                <td>"+land_time+"</td>\n" +
"            </tr>\n" +
"\n" +
"        </table>\n" +
"            </div>\n" +
"            </div>");
            }
            else{
                out.println("<script>");
                out.println("alert('Unfortunately ticket was not booked!')");
                out.println("location.href='airways_html'");
                out.println("</script>");
            }
                
            out.println("</body>");
            out.println("</html>");
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
public boolean insert_airway_details(){  
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");    
            System.out.println("Welcome");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/transport_company","root","root");
            System.out.println("Connection Created");
            String s = "insert into airway_details(name, source, end,  person_count, class, price, seat_no, date, takeoff ,landing ) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(s);
            ps.setString(1,name);
            ps.setString(2, source);
            ps.setString(3, end);
            ps.setString(4, person);
            ps.setString(5, comp);
            ps.setString(6, price);
            ps.setString(7, seat_no);
            ps.setString(8,date);
            ps.setString(9, takeoff_time);
            ps.setString(10, land_time);
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
