/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Webservice;
import util.Database;

/**
 *
 * @author user
 */
@WebServlet(name = "webserviceController", urlPatterns = {"/webserviceController"})
public class webserviceController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if(request.getParameter("managed").equals("ws")){
                
                boolean edit = true;
                if(request.getParameter("action") != null){
                    if(request.getParameter("action").equals("add")){
                        edit=false;
                    }
                }
                Webservice ws = new Webservice();
                Database db = new Database();

                String nama = new String();
                String jenis = new String();
                String server = new String();
                String port = new String();
                String username = new String();
                String password = new String();
                String ids = new String();
                String sql = new String();
                int id = 0;

                nama = request.getParameter("nama");
                jenis = request.getParameter("jenis");
                server = request.getParameter("server");
                port = request.getParameter("port");
                username = request.getParameter("username");
                password = request.getParameter("password");

                if (edit){
                    ids = request.getParameter("idws");
                    id = Integer.parseInt(ids);

                    sql="UPDATE webservice SET nama='"+nama+"',jenis='"+jenis+"',server='"+server+"'";
                    if(!port.equals("")){
                        sql=sql.concat(",port='"+port+"'");
                    }
                    else{
                        sql=sql.concat(",port=Null");
                    }
                    if(!username.equals("")){
                        sql=sql.concat(",username='"+username+"'");
                    }
                    else{
                        sql=sql.concat(",username=Null");
                    }
                    if(!password.equals("")){
                        sql=sql.concat(",password='"+password+"'");
                    }
                    else{
                        sql=sql.concat(",password=Null");
                    }
                    sql=sql.concat(" WHERE id='"+ids+"'");
                    
                    out.print(sql);
                    Database.setConnection();
                    Database.updatingQuery(sql);
                    //ws.editWebService(ids, nama, jenis, server, port, username, password);

                    response.sendRedirect("manageWS/webservicePage.jsp");
                }else
                if(!edit){
                    sql="INSERT INTO webservice (nama,jenis,server,port,username,password) VALUES ('"+nama+"','"+jenis+"','"+server+"'";
                    if(!port.equals("")){
                        sql=sql.concat(",'"+port+"'");
                    }
                    else{
                        sql=sql.concat(",Null");
                    }
                    if(!username.equals("")){
                        sql=sql.concat(",'"+username+"'");
                    }
                    else{
                        sql=sql.concat(",Null");
                    }
                    if(!password.equals("")){
                        sql=sql.concat(",'"+password+"'");
                    }
                    else{
                        sql=sql.concat(",Null");
                    }
                    sql=sql.concat(")");
                    out.print(sql);
                    Database.setConnection();
                    Database.updatingQuery(sql);

                    response.sendRedirect("manageWS/webservicePage.jsp");
                }


            }   
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
