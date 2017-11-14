<%-- 
    Document   : cap_nhat_ket_qua
    Created on : Nov 8, 2017, 9:48:31 PM
    Author     : HIEU
--%>

<%@page import="model.*" %>
<%@page import="control.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<! DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            DAO dao = new DAO();
            int idTranDau = Integer.parseInt(request.getParameter("idTranDau"));
            TranDau td = dao.getTranDauById(idTranDau);
        %>
        <h1>Trận đấu <%= td.getNoiDung().getTen() %></h1>
        <form:form action="/Badminton/updateKetQuaTranDau" method="POST">
        ID trận đấu: <form:input  path="id" readonly="true" value="<%= td.getId()%>"/>
        <table>
        <%
            if(td.getListDangKyCN() != null) {
        %>
        <tr>
            <th>Vận động viên</th>
            <th>Quốc gia</th>
            <th>Set 1</th>
            <th>Set 2</th>
            <th>Set 3</th>
        </tr>
        <%
            DangKyThiDauCaNhan dk1 = td.getListDangKyCN().get(0);
            DangKyThiDauCaNhan dk2 = td.getListDangKyCN().get(1);
        %>
        <tr>
            <td><%= dk1.getVdv().getHoten() %></td>
            <td><%= dk1.getVdv().getQuocGia().getTen() %></td>
            <td><form:input path="set11" value="<%=td.getSet11()%>"/></td>
            <td><form:input path="set21" value="<%=td.getSet21()%>"/></td>
            <td><form:input path="set31" value="<%=td.getSet31()%>"/></td>
        </tr>
        <tr>
            <td><%= dk2.getVdv().getHoten() %></td>
            <td><%= dk2.getVdv().getQuocGia().getTen() %></td>
            <td><form:input path="set12" value="<%=td.getSet12()%>"/></td>
            <td><form:input path="set22" value="<%=td.getSet22()%>"/></td>
            <td><form:input path="set32" value="<%=td.getSet32()%>"/></td>
        </tr>
        
        <%
            }else if(td.getListDangKyDoi() != null) {
        %>
        <tr>
            <th>Đội</th>
            <th>Set 1</th>
            <th>Set 2</th>
            <th>Set 3</th>
        </tr>
         <%
            DangKyThiDauDoi dk1 = td.getListDangKyDoi().get(0);
            DangKyThiDauDoi dk2 = td.getListDangKyDoi().get(1);
        %>
        <tr>
            <td><%= dk1.getDoi().getTenDoi() %></td>
            <td><form:input path="set11" value="<%=td.getSet11()%>"/></td>
            <td><form:input path="set21" value="<%=td.getSet21()%>"/></td>
            <td><form:input path="set31" value="<%=td.getSet31()%>"/></td>
        </tr>
        <tr>
            <td><%= dk2.getDoi().getTenDoi() %></td>
            <td><form:input path="set12" value="<%=td.getSet12()%>"/></td>
            <td><form:input path="set22" value="<%=td.getSet22()%>"/></td>
            <td><form:input path="set32" value="<%=td.getSet32()%>"/></td>
        </tr>
        <%}%>
           
        </table>
        <input type="submit" value="Cập nhật" />
        <input type="reset" value="Reset"/>
        </form:form>
    </body>
</html>
