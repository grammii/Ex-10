<%-- 
    Document   : Added
    Created on : Nov 11, 2022, 2:13:39 PM
    Author     : prosf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Id:<%=request.getAttribute("id")%><br>
        Name:<%=request.getAttribute("name")%><br>
        Gpa:<%=request.getAttribute("gpa")%><br>
        is added</h1>
        <%@ include file ="List.jsp"%>
        
    </body>
</html>
