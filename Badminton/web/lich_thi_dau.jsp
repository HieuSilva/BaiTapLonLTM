<%-- 
    Document   : lich_thi_dau
    Created on : Nov 14, 2017, 9:46:50 AM
    Author     : HIEU
--%>
<%@page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<! DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/lich_thi_dau.css"/>"/>
    </head>
    <body>
        <h1>Lịch thi đấu</h1>
        <%  TranDau [] listTD = (TranDau[])session.getAttribute("listTranDau");
            System.out.println(listTD);%>
        <table>
            <tr>
                <th>Mã trận đấu</th>
                <th>Nội dung</th>
                <th>Vòng</th>
                <th>Cặp</th>
                <th>Sân</th>
                <th>Thời gian</th>
                <th>Điểm thưởng</th>
                <th></th>
            </tr>
            <% for(TranDau td : listTD) {
            %>
            <tr>
                <td><%= td.getId()%></td>
                <td><%= td.getNoiDung().getTen()%></td>
                <td><%= td.getVong()%></td>
                <td><%= td.getCap() %></td>
                <td><%= td.getSan().getTen() %></td>
                <td><%= td.getThoiGian().toString() %></td>
                <td><%= td.getDiemThuong() %></td>
                <td><% if(td.getListDangKyCN() != null || td.getListDangKyDoi() != null) {%>
                    <a href="cap_nhat_ket_qua?idTranDau=<%= td.getId() %>">Cập nhật kết quả</a>
                    <% }else {} %>
                </td>
            </tr>
            <% } %>
        </table>
    </body>
</html>
