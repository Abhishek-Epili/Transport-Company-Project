/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 *
 * @author Vidyadhar epili
 */
@WebServlet(urlPatterns = {"/airway_operations"})
public class airway_operations extends HttpServlet {
    static String name, source, end, comp, date, person, price, takeoff_time, land_time, seat_no;
    static int n_end;
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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            view_air_ticket.name = name = transport.name;
            view_air_ticket.source = source = request.getParameter("hidden_source");
            view_air_ticket.end = end = request.getParameter("hidden_end");
            view_air_ticket.comp = comp = request.getParameter("class");
            view_air_ticket.person = person = request.getParameter("person");
            view_air_ticket.price = price = request.getParameter("hidden_price");
            view_air_ticket.date = date = request.getParameter("date");
            Random random = new Random();
            
            String delhi_takeoff[] = {null,"06:04","09:23","04:40","12:30","18:34","15:16","21:52"};
            String delhi_land[] = {null,"07:26","10:50","06:45","14:12","20:21","17:55","00:42"};
            String lucknow_takeoff[] = {"14:24",null,"05:17","09:40","19:32","03:34","06:13","20:52"};
            String lucknow_land[] = {"15:39",null,"06:53","10:36","21:12","05:03","07:50","23:14"};
            String ahmedabad_takeoff[] = {"16:13","01:07",null,"13:56","16:21","06:34","11:32","08:29"};
            String ahmedabad_land[] = {"15:34","02:23",null,"15:20","17:58","08:26","13:49","12:14"};
            String kolkata_takeoff[] = {"16:04","19:23","14:40",null,"19:30","05:34","07:16","05:32"};
            String kolkata_land[] = {"17:34","20:34","15:53",null,"21:48","07:24","09:32","08:24"};
            String chandigarh_takeoff[] = {"6:34","12:23","04:58","15:32",null,"19:24","21:16","16:02"};
            String chandigarh_land[] = {"7:54","13:41","06:14","16:38",null,"20:54","23:03","18:25"};
            String mumbai_takeoff[] = {"03:34","06:13","08:32","12:21","15:44",null,"17:26","20:03"};
            String mumbai_land[] = {"05:02","07:42","10:13","14:26","16:50",null,"19:05","22:32"};
            String banglore_takeoff[] = {"14:14","16:32","17:22","20:03","23:14","03:36",null,"06:52"};
            String banglore_land[] = {"15:47","18:12","18:56","21:38","00:22","05:23",null,"08:58"};
            String chennai_takeoff[] = {"17:02","20:32","00:42","05:22","09:03","12:23","14:41",null};
            String chennai_land[] = {"19:44","22:11","02:24","07:05","10:44","14:01","16:13",null};
            n_end = Integer.parseInt(request.getParameter("destination"));
            switch (source) {
                case "Delhi":
                    takeoff_time = delhi_takeoff[n_end-1];
                    land_time = delhi_land[n_end-1];
                    break;
                case "Lucknow":
                    takeoff_time = lucknow_takeoff[n_end-1];
                    land_time = lucknow_land[n_end-1];
                    break;
                case "Ahmedabad":
                    takeoff_time = ahmedabad_takeoff[n_end-1];
                    land_time = ahmedabad_land[n_end-1];
                    break;
                case "Kolkata":
                    takeoff_time = kolkata_takeoff[n_end-1];
                    land_time = kolkata_land[n_end-1];
                    break;
                case "Chandigarh":
                    takeoff_time = chandigarh_takeoff[n_end-1];
                    land_time = chandigarh_land[n_end-1];
                    break;
                case "Mumbai":
                    takeoff_time = mumbai_takeoff[n_end-1];
                    land_time = mumbai_land[n_end-1];
                    break;
                case "Banglore":
                    takeoff_time = banglore_takeoff[n_end-1];
                    land_time = banglore_land[n_end-1];
                    break;
                case "Chennai":
                    takeoff_time = chennai_takeoff[n_end-1];
                    land_time = chennai_land[n_end-1];
                    break;
                default:
                    break;
            }
            switch (comp){
                case "Economy":
                    seat_no = "E"+random.nextInt(20);
                    break;
                case "Business":
                    seat_no = "B"+random.nextInt(15);
                    break;
                case "First":
                    seat_no = "F"+random.nextInt(10);
                    break;
                default:
                    break;
            }
            view_air_ticket.takeoff_time = takeoff_time;
            view_air_ticket.land_time = land_time;
            view_air_ticket.seat_no = seat_no;           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Fast Transport Services</title>");   
            out.println("<link rel='stylesheet' href='table.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Flight Available on "+date+"</h1>");
            out.println("<table border='1'");
            out.print("<tr><th>Source</th><th>Destination</th><th>Takeoff Time</th><th>Landing Time</th><th>Class</th><th>Date</th><th>Book</th></tr>");
            out.print("<tr><td>"+source+"</td><td>"+end+"</td><td>"+takeoff_time+"</td><td>"+land_time+"</td><td>"+comp+"</td><td>"+date+"</td><td><a href='view_air_ticket'>Book</a></td></tr>");
            out.println("</table>");
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
