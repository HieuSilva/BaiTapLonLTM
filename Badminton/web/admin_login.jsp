<%-- 
    Document   : admin
    Created on : Nov 8, 2017, 9:25:40 AM
    Author     : HIEU
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<! DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Login</title>
    </head>
    <body>
        <form:form action="/Badminton/checkAdminLogin" method="POST">
            Username: <form:input type="text" path="username"/>
            Password: <form:input type="text" path="password"/>
            <input type="submit" value="Login"/>
            <inpyt type="reset" value="Reset" />
        </form:form>
    </body>
</html>