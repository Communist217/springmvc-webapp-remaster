<%--
  Created by IntelliJ IDEA.
  User: MinhPC
  Date: 2/28/2019
  Time: 6:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/taglib/taglib.jsp"%>
<html>
    <head>
        <title>Main Page</title>
        <!--Load angularjs lib-->
        <script src='<c:out value="template/script/AngularJS/angular.min.js"/>'></script>
        <link rel="stylesheet" type="text/css" href='<c:url value="/template/css/Main_Content.css"/>'/>
        <link rel="stylesheet" type="text/css" href='<c:url value="/template/css/Navigation_Content.css"/>'/>
        <link rel="stylesheet" type="text/css" href='<c:url value="/template/css/SpinnyWheel_Loading.css"/>'/>
        <link rel="stylesheet" type="text/css" href='<c:url value="/template/css/bootstrap-4.1.3-dist/css/bootstrap.css"/>'/>
        <link rel="stylesheet" type="text/css" href='<c:url value="/template/css/Sweet/sweetalert2.css"/>'/>
        <sql:setDataSource var="shop_db" driver = "com.mysql.cj.jdbc.Driver"
                           url = "jdbc:mysql://localhost:3306/onlineshopdatabase?useSSL=false"
                           user = "root" password = "minhngoc61021"/>
        <sql:query dataSource="${shop_db}" var="type">
            Select typename
            from producttype;
        </sql:query>
    </head>
    <body style="font-family: 'Calibri', sans-serif; font-size: 16px; ">
        <!--Header of the page.-->
        <%@ include file="header.jsp" %>

        <input type="hidden" value="<c:out value="${User.getUserID()}">-1</c:out>" id="Userid">
        <%--All Product--%>
        <section class="Content-area" onload="TypeValue()">
            <div style="margin: auto; max-width: 1119px; height: auto; min-width: 700px;">
                <h1>Product</h1>
                <div class="dropdown-divider"></div>
                <strong style="margin: 5px;">Price: </strong>
                <label class="container_radio">All
                    <input type="radio" id="All" onchange="TypeValue()" name="radio">
                    <span class="checkmark"></span>
                </label>
                <c:forEach items="${type.rows}" var="producttype">
                    <label class="container_radio"><c:out value="${producttype.typename}"/>
                        <input type="radio" id="<c:out value="${producttype.typename}"/>" onchange="TypeValue()" name="radio">
                        <span class="checkmark"></span>
                    </label>
                </c:forEach>
                <div ng-app="Search" style="float: left; position: absolute; display: inline-block;" ng-controller="product_search_controller">
                    <input class="search-input" type="text" ng-model="product_name" placeholder="Searching...">
                    <p id="search-result" ng-bind="product_name"></p>
                </div>
                <select id="Sort_option">
                    <option selected="selected">Default</option>
                    <option>Price: Low to High</option>
                    <option>Price: High to Low</option>
                    <option>Popularity</option>
                </select>
                <strong style="float: right; margin: 5px;">Sort by: </strong>
                <div class="dropdown-divider"></div>
                <div class="Product-list">
                    <div style="left: 46%; margin-top: 100px; position: fixed;">
                        <div class="loader"></div>
                    </div>
                    <ul id="List">

                    </ul>
                </div>
            </div>
        </section>
        <!--Load jquery script lib-->
        <script src="<c:url value='/template/script/JQuery/jquery-3.3.1.min.js'/>"></script>
        <!--Load main page script-->
        <script src="<c:url value='/template/script/Main_Page.js'/>"></script>
        <!--Load  bootstrap script lib-->
        <script src="<c:url value='/template/script/Bootstrap/popper.min.js'/>"></script>
        <script src="<c:url value='/template/script/Bootstrap/bootstrap.js'/>"></script>
        <script src="<c:url value='/template/script/Sweet/sweetalert2.min.js'/>"></script>
    </body>
</html>
