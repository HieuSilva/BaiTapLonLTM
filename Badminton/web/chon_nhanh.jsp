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
    </head>
    <body>
        <h1>Chọn nội dung</h1>
        <%  DAO dao = new DAO();
            NoiDung [] listND = dao.getNoiDungList();
        %>
        <form:form action="/Badminton/chonNhanhNoiDung" method="POST">
            <form:select path="idNoiDung" >
                <%for(NoiDung nd: listND) { 
                    System.out.println(nd.getTen());%>
                    <form:option value="<%= nd.getId()%>"  title="<%= nd.getTen() %>" label="<%= nd.getTen() %>"/>
                <% } %>
            </form:select>
            <input type="submit" value="Xem" />
        </form:form>
    </body>
</html>
