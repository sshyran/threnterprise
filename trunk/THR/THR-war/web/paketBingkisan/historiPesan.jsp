<%-- 
    Document   : historipesan
    Created on : Oct 25, 2011, 6:35:48 AM
    Author     : hyouda
--%>

<%@page import="model.PesanKirimBingkisan"%>
<%@page import="model.PesanBingkisan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.PesanPaket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        if (session.getAttribute("getpaket") != null && session.getAttribute("getbingkisan") != null && session.getAttribute("getkirim") != null) {

            ArrayList<PesanPaket> pp = (ArrayList<PesanPaket>) session.getAttribute("getpaket");
            ArrayList<PesanBingkisan> pb = (ArrayList<PesanBingkisan>) session.getAttribute("getbingkisan");
            ArrayList<PesanKirimBingkisan> pk = (ArrayList<PesanKirimBingkisan>) session.getAttribute("getkirim");

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/default.css" type="text/css" />
        <link rel="stylesheet" href="../style/front.css" type="text/css" />
        <script type="text/javascript" src="script/jquery-1.6.1.js"></script>
        <title>Histori Pemesanan</title>
    </head>
    <body>
        <%@include file="../layout/head.jsp" %> 
        <div id="content-wrapper">
            <div id="content" style="width: 1080px">
                <div class="list-sheet" style="width: 100%; padding-top: 20px; min-height: 480px;">
                    <h2 style="margin-left: 10px;">Paket Bingkisan</h2><hr/>
                    <table border="1" class="tutturu">
                        <tr>
                            <th>No.</th>
                            <th>Nama Paket</th>
                            <th>Jumlah Paket</th>
                            <th>Order Date</th>
                            <th>Due Date</th>
                            <th>Tujuan</th>
                            <th>Keterangan</th>
                        </tr>
                        <% for (int i = 0; i < pp.size(); i++) {
                                PesanPaket paket = pp.get(i);
                                PesanKirimBingkisan kirim = pk.get(i);
                        %>
                        <tr>
                            <td><% out.print(i + 1);%></td>
                            <td><% out.print(paket.getPaket_name());%></td>
                            <td><% out.print(paket.getJumlah_paket());%></td>
                            <td><% out.print(paket.getOrder_dateS());%></td>
                            <td><% out.print(paket.getDue_date());%></td>
                            <td><% out.print(kirim.getAlamat());%></td>
                            <td><%
                                if (paket.getPay_date() == null) {
                                    out.print("belum bayar");
                                } else {
                                    out.print(paket.getPay_date());
                                }%></td>
                        </tr>
                        <% }%>
                    </table>
                    &nbsp;
                    <h2 style="margin-left: 10px;">Paket Perjalanan</h2><hr/>
                    <table border="1" class="tutturu">
                        <tr>
                            <th>No.</th>
                            <th>Nama Paket</th>
                            <th>Jumlah</th>
                            <th>Alamat Tujuan</th>
                            <th>Order Date</th>
                            <th>Due Date</th>
                            <th>Pay Date</th>
                        </tr>
                        <% for (int i = 0; i < pb.size(); i++) {
                                PesanBingkisan bingkisan = pb.get(i);
                                PesanKirimBingkisan kirim = pk.get(i);
                        %>
                        <tr>
                            <td><% out.print(bingkisan.getPaket_name());%></td>
                            <td><% out.print(bingkisan.getJumlah_paket());%></td>
                            <td><% out.print(kirim.getAlamat());%></td>
                            <td><% out.print(bingkisan.getOrder_dateS());%></td>
                            <td><% out.print(bingkisan.getDue_date2());%></td>

                            <td><%
                                if (bingkisan.getPay_date() == null) {
                                    out.print("belum bayar");
                                } else {
                                    out.print(bingkisan.getPay_date());
                                }%></td>
                        </tr>
                        <% }%>
                    </table></div>
            </div>
        </div>
        <%@include file="../layout/footer.jsp" %>
    </body>
</html>
<%
    } else {
        response.sendRedirect("../index.jsp");
    }
%>
