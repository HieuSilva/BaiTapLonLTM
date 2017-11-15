<%-- 
    Document   : admin
    Created on : Nov 8, 2017, 9:25:40 AM
    Author     : HIEU
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<! DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Login</title>
    </head>
    <body style="text-align: center">
        <h1 style="margin-top: 100px">Đăng nhập</h1>
        <form:form style="margin:auto" action="/Badminton/checkAdminLogin" method="POST">
            Username: <form:input type="text" path="username"/> </br> </br>
            Password: <form:input type="password" path="password"/> </br></br>
            
            <input type="submit" value="Login"/>
            <input type="reset" value="Reset" />
        </form:form>
    </body>
</html>
