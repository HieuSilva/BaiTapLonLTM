<%-- 
    Document   : xem_nhanh
    Created on : Nov 15, 2017, 4:29:48 PM
    Author     : HIEU
--%>

<%@page import="model.*" %>
<%@page import="control.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<! DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href=<c:url value="/css/chon_nhanh.css"/> />
        <link rel="stylesheet" type="text/css" href=<c:url value="/css/nhanh_thi_dau.css"/> />
    </head>
    <body>
        <h3>Chọn nội dung</h3>
        <%  DAO dao = new DAO();
            NoiDung [] listND = dao.getNoiDungList();
        %>
        <form:form style="margin-bottom: 50px" action="/Badminton/chonNhanhNoiDung" method="POST">
            <form:select path="idNoiDung" >
                <%for(NoiDung nd: listND) { 
                    System.out.println(nd.getTen());%>
                    <form:option value="<%= nd.getId()%>"  title="<%= nd.getTen() %>" label="<%= nd.getTen() %>"/>
                <% } %>
            </form:select>
            <input type="submit" value="Xem" />
        </form:form>
        <div style="text-align: center">
            <a href="admin_home.jsp">Trở về trang chủ</a>
        </div>
        <%  %>   
        <% NoiDung n = (NoiDung) session.getAttribute("noiDung");
            
            if(n != null) { %>
            <h1>Nội dung <%= n.getTen() %></h1>    
        <table>
            <tr>
                <th colspan="2">Vòng 1</th>
                <th colspan="2">Vòng 2</th>
                <th colspan="2">Vòng 3 (Bán kết)</th>
                <th colspan="4">Vòng 4 (Chung kết)</th>
                <th colspan="2">Vòng 3 (Bán kết)</th>
                <th colspan="2">Vòng 2</th>
                <th colspan="2">Vòng 1</th>
            </tr>
            <tr id="row1">
                <td class="tenvdv" id="ten111"></td> <td class="tiso" id="ts111"></td> 
                <td colspan="12"></td> 
                <td class="tiso" id="ts151"></td> <td class="tenvdv" id="ten151"></td>
            </tr>
            <tr id="row2">
                <td colspan="2"></td> 
                <td class="tenvdv" id="ten211"></td> <td class="tiso" id="ts211"></td> 
                <td colspan="8"></td> 
                <td class="tiso" id="ts231"></td> <td class="tenvdv" id="ten231"></td>
                <td colspan="2"></td>
            </tr>
            <tr id="row3">
                <td class="tenvdv" id="ten112"></td> <td class="tiso" id="ts112"></td> 
                <td colspan="2" class="lineright"></td><td colspan="8"></td><td colspan="2" class="lineleft"></td>
                <td class="tiso" id="ts152"></td> <td class="tenvdv" id="ten152"></td>
            </tr>
            <tr id="row4">
                <td colspan="4"></td>
                <td class="tenvdv" id="ten311"></td> <td class="tiso" id="ts311"></td>
                <td colspan="4"></td>
                <td class="tiso" id="ts321"></td> <td class="tenvdv" id="ten321"></td>
                <td colspan="4"></td>
            </tr>
            <tr id="row5">
                <td class="tenvdv" id="ten121"></td> <td class="tiso" id="ts121"></td> 
                <td colspan="2" class="lineright"></td><td colspan="2" class="lineright"></td><td colspan="4"></td><td colspan="2" class="lineleft"></td><td colspan="2" class="lineleft"></td>
                <td class="tiso" id="ts161"></td> <td class="tenvdv" id="ten161"></td>
            </tr>
            <tr id="row6">
                <td colspan="2"></td> 
                <td class="tenvdv" id="ten212"></td> <td class="tiso" id="ts212"></td> 
                <td colspan="2" class="lineright"></td><td colspan="4"></td><td colspan="2" class="lineleft"></td> 
                <td class="tiso" id="ts232"></td> <td class="tenvdv" id="ten232"></td>
                <td colspan="2"></td>
            </tr>
            <tr id="row7">
                <td class="tenvdv" id="ten122"></td> <td class="tiso" id="ts122"></td> 
                <td colspan="4" class="lineright"></td><td colspan="4"><img src="<c:url value="/resources/cup.png"/>" /></td><td colspan="4" class="lineleft"> 
                <td class="tiso" id="ts162"></td> <td class="tenvdv" id="ten162"></td>
            </tr>
            <tr id="row8">
                <td colspan="6"></td>
                <td class="tenvdv" id="ten411"></td> <td class="tiso" id="ts411"></td>
                <td class="tiso" id="ts412"></td> <td class="tenvdv" id="ten412"></td>
                <td colspan="6"></td>
            </tr>
            <tr id="row9">
                <td class="tenvdv" id="ten131"></td> <td class="tiso" id="ts131"></td> 
                <td colspan="4" class="lineright"></td><td colspan="4"></td><td colspan="4" class="lineleft">
                <td class="tiso" id="ts171"></td> <td class="tenvdv" id="ten171"></td>
            </tr>
            <tr id="row10">
                <td colspan="2"></td> 
                <td class="tenvdv" id="ten221"></td> <td class="tiso" id="ts221"></td> 
                <td colspan="2" class="lineright"></td><td colspan="4"></td><td colspan="2" class="lineleft"></td>  
                <td class="tiso" id="ts241"></td> <td class="tenvdv" id="ten241"></td>
                <td colspan="2"></td>
            </tr>
            <tr id="row11">
                <td class="tenvdv" id="ten132"></td> <td class="tiso" id="ts132"></td>
                <td colspan="2" class="lineright"></td><td colspan="2" class="lineright"></td><td colspan="4"></td><td colspan="2" class="lineleft"></td><td colspan="2" class="lineleft"></td>
                <td class="tiso" id="ts172"></td> <td class="tenvdv" id="ten172"></td>
            </tr>
            <tr id="row12">
                <td colspan="4"></td>
                <td class="tenvdv" id="ten312"></td> <td class="tiso" id="ts312"></td>
                <td colspan="4"></td>
                <td class="tiso" id="ts322"></td> <td class="tenvdv" id="ten322"></td>
                <td colspan="4"></td>
            </tr>
            <tr id="row13">
               <td class="tenvdv" id="ten141"></td> <td class="tiso" id="ts141"></td> 
                <td colspan="2" class="lineright"></td><td colspan="8"></td><td colspan="2" class="lineleft"></td>
                <td class="tiso" id="ts181"></td> <td class="tenvdv" id="ten181"></td>
            </tr>
            <tr id="row14">
                <td colspan="2"></td> 
                <td class="tenvdv" id="ten222"></td> <td class="tiso" id="ts222"></td> 
                <td colspan="8"></td> 
                <td class="tiso" id="ts242"></td> <td class="tenvdv" id="ten242"></td>
                <td colspan="2"></td>
            </tr>
            <tr id="row15">
               <td class="tenvdv" id="ten142"></td> <td class="tiso" id="ts142"></td> 
                <td colspan="12"></td> 
                <td class="tiso" id="ts182"></td> <td class="tenvdv" id="ten182"></td>
            </tr>
        </table>
        <% }
            if(n.getId() < 3) {
                for(int vong=1; vong<=4; vong++) {
                for(int cap=1; cap<=8; cap++) {
                    TranDau td = dao.getTranDauByNoiDungVongCap(n, vong, cap);
                    if(td != null && td.getListDangKyCN() != null) {
                        char tiso [] = dao.getTiSo(td);
                        DangKyThiDauCaNhan dk1 = td.getListDangKyCN().get(0);
//                        System.out.println(dk1.getVdv().getHoten());
                        DangKyThiDauCaNhan dk2 = td.getListDangKyCN().get(1);
//                        System.out.println(dk2.getVdv().getHoten());
                        String idCellTenVDV1 = "ten" + vong + cap + "1";
                        String idCellTenVDV2 = "ten" + vong + cap + "2";
                        String idCellTiSoVDV1 = "ts" + vong + cap + "1";
                        String idCellTiSoVDV2 = "ts" + vong + cap + "2";%>
                        
                        <script type="text/javascript">
                            document.getElementById("<%= idCellTenVDV1 %>").innerHTML = "<%=dk1.getVdv().getHoten() %>"
                            document.getElementById("<%= idCellTenVDV2 %>").innerHTML = "<%=dk2.getVdv().getHoten() %>"
                            document.getElementById("<%= idCellTiSoVDV1 %>").innerHTML = "<%= tiso[0] %>"
                            document.getElementById("<%= idCellTiSoVDV2 %>").innerHTML = "<%= tiso[1] %>"
                        </script>
            <%}}}}
            else if(n.getId() > 2) {
                for(int vong=1; vong<=4; vong++) {
                    for(int cap=1; cap<=8; cap++) {
                        TranDau td = dao.getTranDauByNoiDungVongCap(n, vong, cap);
                        if(td != null && td.getListDangKyDoi() != null) {
                        char tiso [] = dao.getTiSo(td);
                        DangKyThiDauDoi dk1 = td.getListDangKyDoi().get(0);
//                        System.out.println(dk1.getVdv().getHoten());
                        DangKyThiDauDoi dk2 = td.getListDangKyDoi().get(1);
//                        System.out.println(dk2.getVdv().getHoten());
                        String idCellTenDoi1 = "ten" + vong + cap + "1";
                        String idCellTenDoi2 = "ten" + vong + cap + "2";
                        String idCellTiSoDoi1 = "ts" + vong + cap + "1";
                        String idCellTiSoDoi2 = "ts" + vong + cap + "2";%>
                        
                        <script type="text/javascript">
                            document.getElementById("<%= idCellTenDoi1 %>").innerHTML = "<%=dk1.getDoi().getTenDoi() %>"
                            document.getElementById("<%= idCellTenDoi2 %>").innerHTML = "<%=dk2.getDoi().getTenDoi() %>"
                            document.getElementById("<%= idCellTiSoDoi1 %>").innerHTML = "<%= tiso[0] %>"
                            document.getElementById("<%= idCellTiSoDoi2 %>").innerHTML = "<%= tiso[1] %>"
                        </script>
        <%}}}}%>
        
       
    </body>
</html>
