<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/taglib/taglib.jsp"%>
<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/css/Cart.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/css/bootstrap-4.1.3-dist/css/bootstrap.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/css/Sweet/sweetalert2.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/css/Footer.css'/>" />
</head>
<sql:setDataSource var = "PreOrder" driver = "com.mysql.cj.jdbc.Driver"
                   url = "jdbc:mysql://localhost:3306/onlineshopdatabase?useSSL=false"
                   user = "root"  password = "minhngoc61021"/>
<sql:query dataSource = "${PreOrder}" var = "list">
    select *
    from products p
    inner join preorder pre
    on p.productid = pre.productid
    inner join producttype type
    on p.typeid = type.typeid
    where pre.memberid = ?;
    <sql:param value="${User.getUserID()}"/>
</sql:query>
<sql:query dataSource = "${PreOrder}" var = "Total_Cost">
    select sum(p.Price * a.quantity) as total
    from preorder a
    inner join products p
    on a.ProductID = p.ProductID where a.MemberID = ? group by a.MemberID;
    <sql:param value="${User.getUserID()}"/>
</sql:query>
<body onload="Time()" style="font-family: 'Calibri', serif; font-size: 16px;">
<div> <!--Top area of the page.-->
    <%@include file="footer.jsp"%>
    <header class="bar">
        <section style="max-width: 1119px; margin: auto;">
            <div style="margin-top: 2px;" title="Here is where the product(s) which has been added by you.">
                    <span>
                        <img src="template/IMG/shopping-cart.png" style="width: 20px; height: 20px;">
                    </span>
                <span style="position: absolute; padding-left: 5px; margin-top: -2px;"> Your Cart</span>
            </div>
        </section>
    </header>
    <%--The test condition that determines whether or not the body content should be processed.--%>
    <c:if test = "${list.rowCount > 0}">
        <section class="Preorder_list">
            <div style="max-width: 1119px; margin: auto;">
                <a href="main-page" style="text-decoration: none; padding: 5px; color: #000000">
                    <img src="template/IMG/left-arrow.png" style="margin-top: -2px;"> Go back to Main Page
                </a>
                <div style="padding: 5px;">
                    <a class="close_order" href="javascript:Abort_Order(${User.getUserID()});" ><img src="template/IMG/abort_order.png" style="width: 16px;"></a>
                    <c:if test="${pageContext.request.method=='POST'}">
                        <c:redirect url="cart"/>
                    </c:if>
                    <div class="order">
                        <div class="Productinfo" style="padding: 33px;">
                            <ul style="list-style: none; padding: 3px 0px 0px;" >
                                <c:forEach var = "result" items = "${list.rows}">
                                    <li id="product" style="padding-top: 9px; padding-bottom: 9px;">
                                        <form id="Removed-no<c:out value="${result.ProductID}"></c:out>" action="remove_product_by_option" method="post">
                                            <h6 style="padding: 5px;"><mark>Product#: <c:out value="${result.ProductID}">N/A</c:out></mark></h6>
                                            <span style="display: inline-block;">
                                                    <img src= "<c:out value="${result.Imagesource}">N/A</c:out>" style=" height: 100px; width: 100px; float: left;" alt="Product">
                                                </span>
                                            <span style="display: inline-block; position: absolute; margin: 0px 22px 0px;">
                                                    <strong class="name"><c:out value="${result.Productname}">N/A</c:out></strong>
                                                    <br>
                                                    <p class="type"> Type: <c:out value="${result.Typename}">N/A</c:out></p>
                                                </span>
                                                <%--Remove a product in the cart--%>
                                            <input type="hidden" name="Pid" value="<c:out value="${result.ProductID}"></c:out>">
                                            <input type="hidden"  name="Uid" value="<c:out value="${result.MemberID}"></c:out>">
                                            <a type="button" name="Remove" href="javascript:Remove_product('${result.Productname}', '${result.ProductID}');" class="Remove_order" style="position: absolute; margin-left: 22px; margin-top: 66px; width: 16px; height: 16px; border: none; opacity: 0.5; outline: none;"><img src="template/IMG/Remove.png"></a>
                                            <c:set var = "Price" value = "${result.Price * result.Quantity}"/>
                                            <span style="display: inline-block; float: right">
                                                    <fmt:setLocale value="en-us"/>
                                                    <p>Price: <fmt:formatNumber value="${Price}" type="currency"/></p><br>
                                                </span>
                                        </form>
                                        <form id="change_number<c:out value="${result.ProductID}"></c:out>" action="change_quantity" method="post">
                                                  <span style="margin-top: -90px; float: right;">Quantity:
                                                      <input type="hidden" name="ProductID" value="<c:out value="${result.ProductID}"></c:out>">
                                                      <input type="hidden"  name="UserID" value="<c:out value="${result.MemberID}"></c:out>">
                                                      <input type="number" id="adjust<c:out value="${result.ProductID}"></c:out>" onclick="expand_button('<c:out value="${result.ProductID}"></c:out>')" onblur="hide_button('<c:out value="${result.ProductID}"></c:out>')" name="quantity_adjust" value="${result.Quantity}" style="width: 50px; text-align: center;" min="1" max="10" class="quantity">
                                                      <a href="javascript:Quantity_Change('${result.ProductID}', '${result.Productname}');" id="quantity_adjust_btn<c:out value="${result.ProductID}"></c:out>" class = "btn btn-light btn-sm adjust" style="margin-top: -3px; margin-left: -3px; height: 30px;">Change</a>
                                                  </span>
                                        </form>
                                    </li>
                                    <div class="dropdown-divider"></div>
                                </c:forEach>
                            </ul>
                            <c:forEach var="value" items="${Total_Cost.rows}">
                                <c:set var = "cost" value = "${value.total}"/>
                                <fmt:setLocale value="en-us"/>
                                <strong style="float: right; height: 20px;">
                                    <p><strong><u>Total Cost:</u></strong> <fmt:formatNumber value="${cost}" type="currency"/></p><br>
                                    </p>
                                </strong>
                            </c:forEach>
                        </div>
                        <div class="dropdown-divider"></div>
                        <div style="text-align: center;">
                            <button class="append" onclick="this.classList.toggle('active')" type="button"></button>
                        </div>
                        <div class="dropdown-divider"></div>
                        <div class="orderfill">
                            <form id="Final" action="finalize_order" method="post">
                                <c:forEach var="P_List" items="${list.rows}">
                                    <input type="hidden" name="Product_List" form="Final" class="form-control" value="${P_List.ProductID}" aria-label="Default" aria-describedby="inputGroup-sizing-default" readonly>
                                    <input type="hidden" name="Product_List_Price" form="Final" class="form-control" value="${P_List.Price * P_List.Quantity}" aria-label="Default" aria-describedby="inputGroup-sizing-default" readonly>
                                    <input type="hidden" name="Product_List_Quantity" form="Final" value="${P_List.Quantity}" readonly>
                                </c:forEach>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Order Date</span>
                                    </div>
                                    <input type="text" name="OrderDate" form="Final" class="form-control" id="time" aria-label="Default" aria-describedby="inputGroup-sizing-default" readonly>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Required Date</span>
                                    </div>
                                    <input type="date" name="RequiredDate" form="Final" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" required>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Customer ID</span>
                                    </div>
                                    <input type="text" name="UserID" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" value="<c:out value="${User.getUserID()}"/>" readonly>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Customer</span>
                                    </div>
                                    <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" value="<c:out value="${User.getFullname()}"/>" readonly>
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Note to our Shop</span>
                                    </div>
                                    <input type="text" name="Note" form="Final" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" placeholder=" Your Note(Not required)">
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Comment</span>
                                    </div>
                                    <input type="text" name="Comment" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" placeholder="Your Comment(Not required)">
                                </div>
                                <input type="hidden" name="Status" value="In Stock" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" readonly>
                                <div class="form-check" style="float: left; margin-bottom: 16px; margin-left: -20px;">
                                    <div class="input-group-prepend" style="display: inline-block;">
                                        <span class="input-group-text">Payment Method</span>
                                    </div>
                                    <div style="display: inline-block;">
                                        <input class="form-check-input position-static" name="Method" type="radio" value="Credit Card" aria-label="..." style="margin: 10px;"><img src="template/IMG/credit_card.png" style="width: 38px; height: 38px; margin-top: -5px;" title="Credit Card">
                                        <input class="form-check-input position-static" name="Method" type="radio" value="Cash" aria-label="..." style="margin: 10px;"><img src="template/IMG/money_cash.png" style="width: 38px; height: 38px; margin-top: -5px;" title="Cash">
                                        <input class="form-check-input position-static" name="Method" type="radio" value="Bitcoin" aria-label="..." style="margin: 10px;"><img src="template/IMG/bitcoin.png" style="width: 38px; height: 38px; margin-top: -5px;" title="Bitcoin">
                                        <input class="form-check-input position-static" name="Method" type="radio" value="Gift Card" aria-label="..." style="margin: 10px;"><img src="template/IMG/gift_card.png" style="width: 38px; height: 38px; margin-top: -5px;" title="Gift Card">
                                    </div>
                                </div>
                                <c:forEach var="val" items="${Total_Cost.rows}">
                                    <c:set var="total" value="${val.total}"/>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">Payment(₫)</span>
                                        </div>
                                        <form>
                                            <input type="text" class="form-control" name="Payment" aria-label="Default" aria-describedby="inputGroup-sizing-default" value="${total}" readonly>
                                        </form>
                                    </div>
                                </c:forEach>
                                <a href="javascript:CF_Order()" form="Final" class="Order_done"  style="float: right; text-decoration: none;">Done!</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </c:if>
    <c:if test = "${list.rowCount == 0}">
        <section class="Empty_cart">
            <div style="max-width: 500px; min-width: 500px; position: absolute;">
                <img src="template/IMG/shopping-cart-empty.png" style="margin-left: 161px; margin-bottom: 10px; opacity: 0.8; filter: alpha(opacity=80);">
                <p style="padding: 7px 2px 7px;">You haven't added anything to the cart yet, please choose your product! </p>
                <a href="main-page" class="Go_back">Go back to Main Page</a>
            </div>
        </section>
    </c:if>
</div>
<!--Load jquery lib-->
<script src="<c:url value='/template/script/JQuery/jquery-3.3.1.min.js'/>"></script>
<!--Load js bootstrap-->
<script src="<c:url value='/template/script/Bootstrap/popper.min.js'/>"></script>
<script src="<c:url value='/template/script/Bootstrap/bootstrap.js'/>"></script>
<script src="<c:url value='/template/script/Real_Time.js'/>"></script>
<%--Load SweetAlert Plugin--%>
<script src="<c:url value='/template/script/Sweet/sweetalert2.min.js'/>"></script>
<!--Load cart page script-->
<script src="<c:url value='/template/script/Cart.js'/>"></script>
</body>
</html>