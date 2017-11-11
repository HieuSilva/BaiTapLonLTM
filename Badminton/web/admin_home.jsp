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

        <h1>WELCOME MANAGER!</h1>
        <div>
            <a href="add_van_dong_vien.jsp">
                <input type="button" value="Thêm vận động viên"/>
            </a>
        </div>

        <div>
            <form action="/Badminton/listVanDongVien" method="POST">
                <input type="submit" value="Xem danh sách vận động viên"/>
            <form>
        </div>

        <div>
            <a href="dang_ky_thi_dau_ca_nhan.jsp">
                <input type="button" value="Đăng ký thi đấu cá nhân"/>
            </a>
        </div>

        <div>
            <a href="dang_ky_thi_dau_doi.jsp">
                <input type="button" value="Đăng ký thi đấu đôi"/>
            </a>
        </div>

        <div>
            <a href="them_tran_dau.jsp">
                <input type="button" value="Thêm trận đấu"/>
            </a>
        </div>

        <div>
            <a href="cap_nhat_ket_qua.jsp">
                <input type="button" value="Cập nhật kết quả trận đấu"/>
            </a>
        </div>
    </body>
</html>
