<%-- 
    Document   : admin_home
    Created on : Nov 8, 2017, 9:24:29 PM
    Author     : HIEU
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<! DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="text-align: center">

        <h1 style="margin-top: 100px">WELCOME MANAGER!</h1>

<!--        <div>
            <form action="/Badminton/listVanDongVien" method="POST">
                <input type="submit" value="Xem danh sách vận động viên"/>
            <form>
        </div>-->
      
        <div>
            <form action="/Badminton/lichThiDau" method="POST">
                <input type="submit" value="Cập nhật kết quả trận đấu"/>
            </form>
        </div>

        <div>
            
            <a href="chon_nhanh">Xem nhánh thi đấu</a>
        </div>
    </body>
</html>
