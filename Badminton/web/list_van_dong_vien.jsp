<%-- 
    Document   : list_van_dong_vien
    Created on : Nov 8, 2017, 9:47:02 PM
    Author     : HIEU
--%>

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
        <h1>Danh sách vận động viên</h1>
        <form action="/Badminton/listVanDongVien" method="POST">
            <input type="radio" name="gioitinh" value="nam"/>
            <input type="radio" name="gioitinh" value="nu"/>
        </form>
        
        <c:forEach items="${listVDV}" var="vdv">
            ${vdv.id}
        </c:forEach>

        ${listVDV[0].id}
        ${listVDV[0].hoten}
        ${listVDV[0].gioiTinh}
        ${listVDV[0].diem}
        ${listVDV[0].mota}
        ${listVDV[0].ngaySinh}
        ${listVDV[0].anh}
        ${listVDV[0].quocGia.id}
    </body>
</html>
