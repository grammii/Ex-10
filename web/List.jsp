<%-- 
    Document   : List
    Created on : Nov 11, 2022, 2:13:08 PM
    Author     : prosf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1 align="right">Student List</h1>
        <table align="right" border ="1" >
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>GPA</th>
            </tr>
            <c:forEach items="${all}" var="stu" varStatus="status">
                <tr>
                    <td>${stu.id}</td>
                    <td>${stu.name}</td>
                    <td>${stu.gpa}</td>
                </tr>
            </c:forEach>
            <h5 align="right"><a href="/StudentApp" >Back to Add Student</a></h5>    
        </table>


    </body>
</html>
