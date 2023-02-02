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
import java.sql.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vidyadhar epili
 */
@WebServlet(urlPatterns = {"/transport"})
public class transport extends HttpServlet {
String name, age, contact, user, email, pass1, pass2, rpass, source, end, person, comp, price, admin_username, admin_pass;
int id, source_num, end_num;
long millis=System.currentTimeMillis();  
java.sql.Date date=new java.sql.Date(millis); 


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Morning Star Transports</title>");
            out.println("<link rel='stylesheet' href='table.css'>");
            out.println("<script src=\"https://code.jquery.com/jquery-1.10.2.js\"></script>\n" +
"        <script src=\"navbar.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"nav-placeholder\"></div>");
            if(request.getParameter("page").equals("login_type")){
                if(request.getParameter("login_type").equals("admin")){
                    response.sendRedirect("admin_login.html");
                } 
                else if(request.getParameter("login_type").equals("user")){
                    response.sendRedirect("login.html");
                }  
            }
            else if(request.getParameter("page").equals("Page1")){
                user = request.getParameter("user");
                pass1 = request.getParameter("pass1");
                session.setAttribute("logged_in","N");
                if(check_user_details()){
                    session.setAttribute("logged_in","Y");
                    response.sendRedirect("choose_type.html");
                }
                else{
                    out.println("<script>");
                    out.println("alert('Login details are invalid!');");
                    out.println("location.replace('login.html')");
                    out.println("</script>");
                    return;
                }
                session.setAttribute("name", name);
            }
            else if(request.getParameter("page").equals("admin_login")){
                user = request.getParameter("user");
                pass1 = request.getParameter("pass1");
                session.setAttribute("admin_log", "N");
                if(check_admin_details()){
                    session.setAttribute("admin_log", "Y");
                    response.sendRedirect("admin_control.html");
                }
                else{
                    out.println("<script>");
                    out.println("alert('Login details are invalid!');");
                    out.println("location.replace('admin_login.html')");
                    out.println("</script>");
                    return;
                }
            }
            else if(request.getParameter("page").equals("Page2")){
                name = request.getParameter("name");
                age = request.getParameter("age");
                contact = request.getParameter("contact");
                email = request.getParameter("email");
                pass2 = request.getParameter("pass2");
                rpass = request.getParameter("rpass");
                
                if(!rpass.equals(pass2)){
                    System.out.println(pass2+rpass);
                    out.println("<script>");
                    out.println("alert('Passwords do not match!');");
                    out.println("location.replace('register.html');");
                    out.println("</script>");
                    return;
                }
                if(register()){
                    out.println("<script>");
                    out.println("alert('Registered successfully!');");
                    out.println("location.replace('register.html');");
                    out.println("</script>");
                }
            }
            else if(request.getParameter("page").equals("Page3")){
                String log="";
                try{
                    log =session.getAttribute("logged_in").toString();
                }
                catch(Exception e){
                    System.out.println(e);
                }
                System.out.println(log);
                if(log.equals("Y")){
                    if(request.getParameter("mode_type").equals("bus")){
                        response.sendRedirect("bus.html");
                    }
                    else if(request.getParameter("mode_type").equals("air")){
                        response.sendRedirect("airways.html");
                    }
                }
                else{
                    out.println("<script>");
                    out.println("alert('Please login first!');");
                    out.println("location.replace('login.html');");
                    out.println("</script>");
                }
            }
            else if(request.getParameter("page").equals("Page4")){
                
                source = request.getParameter("hidden_source");
                end = request.getParameter("hidden_end");
                price = request.getParameter("hidden_price");
                person = request.getParameter("person");
                comp = request.getParameter("compartment"); 
                source_num = Integer.parseInt(request.getParameter("source"));
                end_num = Integer.parseInt(request.getParameter("destination"));
                String t2c1[] = bus_timing("08");
                String t2c2[] = bus_timing("10");
                String t2c3[] = bus_timing("12");
                String t2c4[] = bus_timing("14");
                String t2c5[] = bus_timing("16");
                String c2t1[] = bus_timing("09");
                String c2t2[] = bus_timing("11");
                String c2t3[] = bus_timing("13");
                String c2t4[] = bus_timing("15");
                String c2t5[] = bus_timing("17");
                String bus_num1[] = {"101","A-102","103","A-104","105"};
                String bus_num2[] = {"201","A-202","203","A-204","205"};
                
                
                session.setAttribute("name",name);
                session.setAttribute("bus_price",price);
                session.setAttribute("bus_end",end);
                session.setAttribute("bus_source",source);
                session.setAttribute("bus_comp",comp);
                session.setAttribute("bus_person",person);
                session.setAttribute("bus_date",date);
                out.print("<h1>Buses available for you</h1>");
                out.print("<table border='1'>");
                out.print("<tr><th>Bus Number</th><th>Source</th><th>Destination</th><th>Time at Source</th><th>Time at Destination</th><th>Compartment</th><th>Book</th></tr>");
                if(end_num>source_num){
                    System.out.println(comp);
                    if(comp.equals("AC")){
                        out.print("<tr><td>"+bus_num1[1]+"</td><td>"+source+"</td><td>"+end+"</td><td>"+t2c2[source_num]+"</td><td>"+t2c2[end_num]+"</td><td>AC</td><td><a href='bus_confirm?bus_num=A-102'>Book</a></td></tr>");
                        out.print("<tr><td>"+bus_num1[3]+"</td><td>"+source+"</td><td>"+end+"</td><td>"+t2c4[source_num]+"</td><td>"+t2c4[end_num]+"</td><td>AC</td><td><a href='bus_confirm?bus_num=A-104'>Book</a></td></tr>");
                    }
                    else if(comp.equals("normal")){
                        out.print("<tr><td>"+bus_num1[0]+"</td><td>"+source+"</td><td>"+end+"</td><td>"+t2c1[source_num]+"</td><td>"+t2c1[end_num]+"</td><td>Normal</td><td><a href='bus_confirm?bus_num=101'>Book</a></td></tr>");
                        out.print("<tr><td>"+bus_num1[2]+"</td><td>"+source+"</td><td>"+end+"</td><td>"+t2c3[source_num]+"</td><td>"+t2c3[end_num]+"</td><td>Normal</td><td><a href='bus_confirm?bus_num=A-103'>Book</a></td></tr>");
                        out.print("<tr><td>"+bus_num1[4]+"</td><td>"+source+"</td><td>"+end+"</td><td>"+t2c5[source_num]+"</td><td>"+t2c5[end_num]+"</td><td>Normal</td><td><a href='bus_confirm?bus_num=105'>Book</a></td></tr>");
                    }
                }
                else{
                    if(comp.equals("AC")){
                        out.print("<tr><td>"+bus_num2[1]+"</td><td>"+source+"</td><td>"+end+"</td><td>"+c2t2[end_num]+"</td><td>"+c2t2[source_num]+"</td><td>AC</td><td><a href='bus_confirm?bus_num=A-202'>Book</a></td></tr>");
                        out.print("<tr><td>"+bus_num2[3]+"</td><td>"+source+"</td><td>"+end+"</td><td>"+c2t4[end_num]+"</td><td>"+c2t4[source_num]+"</td><td>AC</td><td><a href='bus_confirm?bus_num=A-204'>Book</a></td></tr>");
                    }
                    else{
                        out.print("<tr><td>"+bus_num2[0]+"</td><td>"+source+"</td><td>"+end+"</td><td>"+c2t1[end_num]+"</td><td>"+c2t1[source_num]+"</td><td>Normal</td><td><a href='bus_confirm?bus_num=201'>Book</a></td></tr>");                        
                        out.print("<tr><td>"+bus_num2[2]+"</td><td>"+source+"</td><td>"+end+"</td><td>"+c2t3[end_num]+"</td><td>"+c2t3[source_num]+"</td><td>Normal</td><td><a href='bus_confirm?bus_num=A-203'>Book</a></td></tr>");
                        out.print("<tr><td>"+bus_num2[4]+"</td><td>"+source+"</td><td>"+end+"</td><td>"+c2t5[end_num]+"</td><td>"+c2t5[source_num]+"</td><td>Normal</td><td><a href='bus_confirm?bus_num=205'>Book</a></td></tr>");
                    }
                }
                out.print("</table>");
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

    public boolean check_user_details(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Welcome");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/transport_company","root","root");
            System.out.println("Connection Created");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select name from user_data where email='"+user+"' and pass='"+pass1+"'");
            if(rs.next()){
                name=rs.getString(1);
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Exception: "+e);
        }
        return false;
    }
    public boolean register(){
         try{
            Class.forName("com.mysql.cj.jdbc.Driver");    
            System.out.println("Welcome");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/transport_company","root","root");
            System.out.println("Connection Created");
            String s = "insert into user_data(name, age, contact, email, pass) values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(s);
            ps.setString(1,name);
            ps.setString(2, age);
            ps.setString(3, contact);
            ps.setString(4, email);
            ps.setString(5, pass2);
            ps.execute();
            con.close();
            return true;
        }
        catch(Exception e){
            System.out.println("Exception "+e);
        }
         return false;
    }
    
    public String[] bus_timing(String hr){
        int min = 0;
        String B[] = new String[11];
        for(int i=0;i<B.length;i++){
            if(min<10){
                B[i]=hr+":0"+min;
            }
            else{
                B[i]=hr+":"+min;
            }
            min+=5;
        }
        return B;
    }

    private boolean check_admin_details() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Welcome");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/transport_company","root","root");
            System.out.println("Connection Created");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from admin where email='"+user+"' and pass='"+pass1+"'");
            if(rs.next()){
                name=rs.getString(1);
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Exception: "+e);
        }
        return false;
    }
}
