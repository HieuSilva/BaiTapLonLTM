<%-- 
    Document   : nhanh_thi_dau
    Created on : Nov 14, 2017, 11:47:03 PM
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
        <link rel="stylesheet"  href=<c:url value="/css/nhanh_thi_dau.css"/>/> 
    </head>
    <body>
        <h1>Nhánh thi đấu</h1>
        <% NoiDung nd = new NoiDung(1, "Don nam", "");
            DAO dao = new DAO(); %>
        <table>
            <tr id="row1">
                <td class="tendoi" id="ten111"></td> <td class="tiso" id="ts111"></td> 
                <td colspan="12"></td> 
                <td class="tiso" id="ts151"></td> <td class="tendoi" id="ten151"></td>
            </tr>
            <tr id="row2">
                <td colspan="2"></td> 
                <td class="tendoi" id="ten211"></td> <td class="tiso" id="ts211"></td> 
                <td colspan="8"></td> 
                <td class="tiso" id="ts231"></td> <td class="tendoi" id="ten231"></td>
                <td colspan="2"></td>
            </tr>
            <tr id="row3">
                <td class="tendoi" id="ten112"></td> <td class="tiso" id="ts112"></td> 
                <td colspan="12"></td> 
                <td class="tiso" id="ts152"></td> <td class="tendoi" id="ten152"></td>
            </tr>
            <tr id="row4">
                <td colspan="4"></td>
                <td class="tendoi" id="ten311"></td> <td class="tiso" id="ts311"></td>
                <td colspan="4"></td>
                <td class="tiso" id="ts321"></td> <td class="tendoi" id="ten321"></td>
                <td colspan="4"></td>
            </tr>
            <tr id="row5">
                <td class="tendoi" id="ten121"></td> <td class="tiso" id="ts121"></td> 
                <td colspan="12"></td> 
                <td class="tiso" id="ts161"></td> <td class="tendoi" id="ten161"></td>
            </tr>
            <tr id="row6">
                <td colspan="2"></td> 
                <td class="tendoi" id="ten212"></td> <td class="tiso" id="ts212"></td> 
                <td colspan="8"></td> 
                <td class="tiso" id="ts232"></td> <td class="tendoi" id="ten232"></td>
                <td colspan="2"></td>
            </tr>
            <tr id="row7">
                <td class="tendoi" id="ten122"></td> <td class="tiso" id="ts122"></td> 
                <td colspan="12"></td> 
                <td class="tiso" id="ts162"></td> <td class="tendoi" id="ten162"></td>
            </tr>
            <tr id="row8">
                <td colspan="6"></td>
                <td class="tendoi" id="ten411"></td> <td class="tiso" id="ts411"></td>
                <td class="tiso" id="ts412"></td> <td class="tendoi" id="ten412"></td>
                <td colspan="6"></td>
            </tr>
            <tr id="row9">
                <td class="tendoi" id="ten131"></td> <td class="tiso" id="ts131"></td> 
                <td colspan="12"></td> 
                <td class="tiso" id="ts171"></td> <td class="tendoi" id="ten171"></td>
            </tr>
            <tr id="row10">
                <td colspan="2"></td> 
                <td class="tendoi" id="ten221"></td> <td class="tiso" id="ts221"></td> 
                <td colspan="8"></td> 
                <td class="tiso" id="ts241"></td> <td class="tendoi" id="ten241"></td>
                <td colspan="2"></td>
            </tr>
            <tr id="row11">
                <td class="tendoi" id="ten132"></td> <td class="tiso" id="ts132"></td> 
                <td colspan="12"></td> 
                <td class="tiso" id="ts172"></td> <td class="tendoi" id="ten172"></td>
            </tr>
            <tr id="row12">
                <td colspan="4"></td>
                <td class="tendoi" id="ten312"></td> <td class="tiso" id="ts312"></td>
                <td colspan="4"></td>
                <td class="tiso" id="ts322"></td> <td class="tendoi" id="ten322"></td>
                <td colspan="4"></td>
            </tr>
            <tr id="row13">
               <td class="tendoi" id="ten141"></td> <td class="tiso" id="ts141"></td> 
                <td colspan="12"></td> 
                <td class="tiso" id="ts181"></td> <td class="tendoi" id="ten181"></td>
            </tr>
            <tr id="row14">
                <td colspan="2"></td> 
                <td class="tendoi" id="ten222"></td> <td class="tiso" id="ts222"></td> 
                <td colspan="8"></td> 
                <td class="tiso" id="ts242"></td> <td class="tendoi" id="ten242"></td>
                <td colspan="2"></td>
            </tr>
            <tr id="row15">
               <td class="tendoi" id="ten142"></td> <td class="tiso" id="ts142"></td> 
                <td colspan="12"></td> 
                <td class="tiso" id="ts182"></td> <td class="tendoi" id="ten182"></td>
            </tr>
            

        </table>
        <%for(int vong=1; vong<=4; vong++) {
            for(int cap=1; cap<=8; cap++) {
                TranDau td = dao.getTranDauByNoiDungVongCap(nd, vong, cap);
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
        <%}}}%>
       
    </body>
</html>
