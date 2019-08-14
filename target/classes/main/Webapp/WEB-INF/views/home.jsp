<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: B86M
  Date: 8/8/2019
  Time: 5:44 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

    <head>
        <meta charset="UTF-8">
        <title>Home Page</title>
    </head>

    <body>
    <h1>WELCOME ${User.username}!</h1>
        <a href="service-page">Service</a>
        <a href="login">Login</a>
    </body>

</html>
