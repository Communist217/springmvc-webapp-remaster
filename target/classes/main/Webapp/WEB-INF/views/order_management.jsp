<%--
  Created by IntelliJ IDEA.
  User: B86M
  Date: 8/25/2019
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/taglib/taglib.jsp"%>
<html>
    <head>
        <title>Order Management</title>
        <link rel="stylesheet" type="text/css" href='<c:url value="/template/css/bootstrap-4.1.3-dist/css/bootstrap.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/template/css/Sweet/sweetalert2.css"/>'>
        <link rel="stylesheet" type="text/css" href='<c:url value="/template/css/Order.css"/>'>
        <link rel="stylesheet" type="text/css" href="<c:url value='/template/css/Footer.css'/>" />
    </head>
    <sql:setDataSource var="Order_Management"  driver = "com.mysql.cj.jdbc.Driver"
                       url = "jdbc:mysql://localhost:3306/onlineshopdatabase?useSSL=false"
                       user = "root"  password = "minhngoc61021"/>
    <sql:query dataSource="${Order_Management}" var="Order">
        Select *
        from orders o1
        inner join orderdetails o2
        on o1.orderID = o2.OrderID
        where o1.memberid = ?
        group by o1.orderID order by orderdate desc;
        <sql:param value="${User.getUserID()}"/>
    </sql:query>
    <body style="font-family: 'Calibri', sans-serif; font-size: 16px;">
        <%@include file="footer.jsp"%>
        <input type="hidden" name="UserID" value="${User.getUserID()}">
        <div>
            <%--bar navigation--%>
            <header class="bar">
                <section style="max-width: 1119px; margin: auto;">
                    <div style="margin-top: 2px;" title="Here is where the product(s) which has been added by you.">
                                    <span>
                                        <img src="template/IMG/order-manage.png" style="width: 20px; height: 20px;">
                                    </span>
                        <span style="position: absolute; padding-left: 5px; margin-top: -2px;"> Your Orders</span>
                    </div>
                </section>
            </header>

            <%--order list--%>
            <%--The test condition that determines whether or not the body content should be processed, when there is/are order(s).--%>
            <c:if test="${Order != null}">
                <section class="Order_list">
                    <a href="main-page" style="text-decoration: none; padding: 5px; color: #000000">
                        <img src="template/IMG/left-arrow.png" style="margin-top: -2px;"> Go back to Main Page
                    </a>
                    <c:forEach items="${Order.rows}" var="res">
                        <div style="margin-top: 5px; margin-bottom: -10px;" >
                            <div class="expand<c:out value="${res.OrderID}"/>" onclick="Toggle_expand('<c:out value="${res.OrderID}"/>');" style="padding: 5px 5px 8px;">
                                <div class="order">
                                    <div>
                                                    <span style="float: left;">
                                                        <mark>Order#: <c:out value="${res.OrderID}">N/A</c:out></mark>
                                                    </span>
                                        <span style="float: left; margin-left: 5px;">
                                                        <strong><u>Order Date:</u></strong> <c:out value="${res.orderdate}">N/A</c:out>
                                                    </span>
                                        <sql:query dataSource="${Order_Management}" var="Day_gap">
                                            Select datediff(shippeddate, requireddate) as gap, shippeddate
                                            from orders
                                            where orderid = ${res.OrderID};
                                        </sql:query>
                                        <c:forEach items="${Day_gap.rows}" var="result">

                                            <%--Determined when the product is delivered.
                                            If the ship date is before the current date, it means that the merchandise has been delivered
                                            Also the reason why the day gap is null is the ship date has not been determined yet, so the merchandise is in process--%>
                                            <jsp:useBean id="now" class="java.util.Date" />
                                            <fmt:formatDate var="current" value="${now}" pattern="yyyy-MM-dd" />

                                            <c:if test="${result.gap != null && result.shippeddate <= current}">
                                                            <span style="float: right;">
                                                                <img src="template/IMG/Delivered.png" style="width: 20px; height: 20px;"><p style="display: inline; margin-left: 4px;">Delivered</p>
                                                            </span>
                                                <sql:update dataSource="${Order_Management}">
                                                    update orders
                                                    set Status = 'Delivered'
                                                    where orderID = ${res.OrderID};
                                                </sql:update>
                                            </c:if>
                                            <c:if test="${result.gap == null || result.shippeddate > current}">
                                                            <span style="float: right;">
                                                                <img src="template/IMG/in-process.gif" style="width: 20px; height: 20px;"><p style="display: inline; margin-left: 4px;">In Process</p>
                                                            </span>
                                                <sql:update dataSource="${Order_Management}">
                                                    update orders
                                                    set Status = 'In Process'
                                                    where orderID = ${res.OrderID};
                                                </sql:update>
                                            </c:if>
                                        </c:forEach>
                                        <span style="float: right; padding-right: 5px;">
                                                        <fmt:setLocale value="en-us"/>
                                                        <strong><u>Total Cost:</u></strong> <fmt:formatNumber value="${res.Payment}" type="currency"/>
                                                    </span>
                                        <br>
                                    </div>
                                </div>
                            </div>
                            <sql:query dataSource="${Order_Management}" var="orderdetails">
                                Select o1.ProductID, p.ProductName, o1.Product_Quantity, p.ImageSource, o1.Price
                                from products p
                                inner join orderdetails o1
                                on o1.productid = p.productid
                                inner join orders o2
                                on o1.OrderID = o2.orderID
                                where o1.OrderID = <c:out value="${res.OrderID}"/> and o2.MemberID = ${User.getUserID()};
                            </sql:query>
                            <div class="order_details list<c:out value="${res.OrderID}"/>" style="margin-bottom: 10px;">
                                <div>
                                    <c:if test="${res.PaymentMethod == 'Credit Card'}">
                                        <p style="float: right;">
                                            <strong>Payment Method:</strong> <img src="template/IMG/credit_card.png" style="width: 30px; height: 30px;" title="Credit Card">
                                        </p>
                                    </c:if>
                                    <c:if test="${res.PaymentMethod == 'Bitcoin'}">
                                        <p style="float: right;">
                                            <strong>Payment Method:</strong> <img src="template/IMG/bitcoin.png" style="width: 30px; height: 30px; padding-bottom: 1px;" title="Bitcoin">
                                        </p>
                                    </c:if>
                                    <c:if test="${res.PaymentMethod == 'Cash'}">
                                        <p style="float: right;">
                                            <strong>Payment Method:</strong> <img src="template/IMG/money_cash.png" style="width: 30px; height: 30px; padding-bottom: 2px;" title="Cash">
                                        </p>
                                    </c:if>
                                    <span style="display: block;">
                                                    <strong>Order Date:</strong> <c:out value="${res.Orderdate}"/>
                                                </span>
                                    <span style="display: block;">
                                                    <strong>Required Date:</strong> <c:out value="${res.RequiredDate}"/>
                                                </span>
                                    <span style="display: block;">
                                                    <strong>Ship Date:</strong> <c:out value="${res.Shippeddate}">N/A</c:out>
                                                </span>
                                </div>
                                <div class="dropdown-divider"></div>
                                <ul style="list-style: none; margin: 5px; padding: 10px;">
                                    <c:forEach var="product" items="${orderdetails.rows}">
                                        <li id="product" style="padding-top: 9px; padding-bottom: 9px;">
                                            <h6 style="padding: 5px;"><mark>Product#: <c:out value="${product.ProductID}">N/A</c:out></mark></h6>
                                            <span style="display: inline-block;">
                                                <img src="${product.ImageSource}" style="height: 100px; width: 100px; float: left;" alt="${product.Productname}">
                                            </span>
                                            <span style="display: inline-block; position: absolute; margin: 0px 22px 0px;">
                                                <strong class="name"><c:out value="${product.Productname}">N/A</c:out></strong>
                                                <br>
                                            </span>
                                            <span style="display: inline-block; float: right">
                                                <fmt:setLocale value="en-us"/>
                                                <p>Price: <fmt:formatNumber value="${product.Price}" type="currency"/></p><br>
                                                <p style=" margin-top: -35px;">Quantity: <c:out value="${product.Product_Quantity}">N/A</c:out></p>
                                            </span>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </c:forEach>
                </section>
            </c:if>

            <%--The test condition that determines whether or not the body content should be processed, when there is no order.--%>
            <c:if test="${Order == null}">
                <%--<section class="Empty_cart">
                    <div>
                        <img src="Style/IMG/shopping-cart-empty.png" style="margin-left: 161px; margin-bottom: 10px; opacity: 0.8; filter: alpha(opacity=80);">
                        <p style="padding: 7px 2px 7px;">You haven't added anything to the cart yet, please choose your product! </p>
                        <a href="Main_Page.jsp" class="Go_back">Go back to Main Page</a>
                    </div>
                </section>--%>
            </c:if>
        </div>
    </body>
    <!--Load jquery lib-->
    <script src="<c:url value='/template/script/JQuery/jquery-3.3.1.min.js'/>"></script>
    <!--Load bootstrap script-->
    <script src="<c:url value='/template/script/Bootstrap/popper.min.js'/>"></script>
    <script src="<c:url value='/template/script/Bootstrap/bootstrap.js'/>"></script>
    <!--Load order management page script-->
    <script src="<c:url value='/template/script/Order_Page.js'/>"></script>
    <%--Load SweetAlert Plugin--%>
    <script src="<c:url value='/template/script/Sweet/sweetalert2.min.js'/>"></script>
</html>
