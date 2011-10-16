<%-- 
    Document   : home
    Created on : 15-Oct-2011, 04:03:26
    Author     : Albadr
--%>

<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% 
if(session.getAttribute("user")== null){
    response.sendRedirect("../index.jsp");
} else {
Customer r = (Customer) session.getAttribute("user");
String notif = request.getParameter("n");

%>   
<!DOCTYPE html>
<html>
    <%
    if(session.getAttribute("user")==null){
        response.sendRedirect("index.jsp");
    }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/default.css" type="text/css" />
        <link rel="stylesheet" href="style/front.css" type="text/css" />
        <script type="text/javascript" src="script/jquery-1.6.1.js"></script>
        <title>Welcome Home</title>
    </head>
    <body>
        <%@include file="layout/bighead.jsp" %>
        <div id="content-wrapper">
            <div id="content">
                <div class="list-sheet" style="width: 676px; height: 340px;padding-bottom: 40px;float: left;position: relative">
                    <div style="float: left; width: 204px;">
                        <div style="width: 128px; height: 128px; text-align: center; margin: 40px;margin-bottom: 10px;text-align: center">
                            <img src="images/person.png" alt="Bingkisan" style="max-height: 128px;" />
                        </div>
                        <a class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;"  href="<%= request.getContextPath()%>">Home</a>
                        <a  class="thrbutton" style="margin-left: 40px;margin-top: 10px;width: 132px;" href="<%= request.getContextPath()%>/editProfile/editProfilPage.jsp">Edit Profile</a>
                        
                    </div>
                    <div style="float: left; width: 460px; min-height: 140px; margin-bottom: 40px;padding-top: 15px;">
                        
                        <h2>Welcome Home</h2>
                        <hr/><br/>
                        <div>
                            <table>
                                <tr>
                                    <td>First Name</td>
                                    <td>: <% if(r.getFirst_name()!=null)out.print(r.getFirst_name());else {out.print("-");} %></td>
                                </tr>
                                <tr>
                                    <td>Last Name</td>
                                    <td>: <% if(r.getLast_name()!=null)out.print(r.getLast_name());else {out.print("-");} %></td>
                                </tr>
                                <tr>
                                    <td>Registered Email</td>
                                    <td>: <% if(r.getFirst_name()!=null)out.print(r.getEmail());else {out.print("-");} %></td>
                                </tr>
                                <tr>
                                    <td>Phone</td>
                                    <td>: <% if(r.getPhone()!=null)out.print(r.getPhone());else {out.print("-");} %></td>
                                </tr>
                                <tr>
                                    <td>Address</td>
                                    <td>: <% if(r.getAddress()!=null)out.print(r.getAddress());else {out.print("-");} %></td>
                                </tr>
                            </table>
                        </div>   
                    </div>  
                </div>
                <%
                if (session.getAttribute("jenisUser").equals("1")){
                %>
                <div class="manager-bucket bucket">
                    <div style="margin-top: 15px;text-align: center;vertical-align: middle;"><img src="images/stats.png" alt="Bingkisan"/></div>
                    <div class="paket-button" style="width: 236px">
                        <a href="^^v">Statistik</a>
                    </div>        
                </div>
                <%
                }else if (session.getAttribute("jenisUser").equals("3")){
                %>
                <div class="manager-bucket bucket">
                    <div style="margin-top: 15px;text-align: center;vertical-align: middle;"><img src="images/user.png" alt="Bingkisan"/></div>
                    <div class="paket-button" style="width: 236px; bottom: 104px">
                        <a href="^^v">Manajemen Kastemer</a>
                    </div>
                    <div class="paket-button" style="width: 236px">
                        <a href="^^v">Manajemen Staff</a>
                    </div>        
                </div>
                <%
                }else if (session.getAttribute("jenisUser").equals("2")){
                %>
                <div class="manager-bucket bucket">
                    <div style="margin-top: 15px;text-align: center;vertical-align: middle;"><img src="images/container.png" alt="Bingkisan"/></div>
                    <div class="paket-button" style="width: 236px; bottom: 104px;">
                        <a href="" style="font-size: 1.3em">Manajemen Paket Bingkisan</a>
                    </div>
                    <div class="paket-button" style="width: 236px">
                        <a href="^^v" style="font-size: 1.3em">Manajemen Paket Perjalanan</a>
                    </div>        
                </div>
                <%
                }
                %>
            </div>
        </div>
        <%@include file="layout/footer.jsp" %>
    </body>
</html>
<% } %>