<%--
  Created by IntelliJ IDEA.
  User: Hau_hypress
  Date: 8/21/2019
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="Top-area">
    <div class="navigation_bar">
        <a href="main-page">
            <span><img style="height: 20px; width: 20px; margin-top: -6px;" src="template/IMG/home.png"></span>
            <span>Home</span>
        </a>
        <div class="drop_down">
            <button class="drop_button">
                <span><img style="height: 20px; width: 20px; margin-top: -5px;" src="template/IMG/Product.png"></span>
                <span>Product</span>
            </button>
            <div class="drop_down_content">
                <a href="Laptop" class="type">
                    <span><img style="height: 20px; width: 20px; margin-top: -5px;" src="template/IMG/laptop.png" alt="Laptop"></span>
                    <span>Laptop</span>
                    <table>
                        <tr>
                            <td><a href="Gaming_Laptop" style="width: inherit;">Gaming Tier</a></td>
                        </tr>
                        <tr>
                            <td><a href="Notebook" style="width: inherit;">Notebook</a></td>
                        </tr>
                        <tr>
                            <td><a href="Ultrabook" style="width: inherit;">Ultrabook</a></td>
                        </tr>
                        <tr>
                            <td><a href="BudgetLaptop" style="width: inherit;">Budget Tier</a></td>
                        </tr>
                    </table>
                </a>
                <a href="Smartphone" class="type">
                    <span><img style="height: 20px; width: 20px; margin-top: -5px;" src="template/IMG/smartphone-call.png" alt="Phone"></span>
                    <span>Phone</span>
                    <table>
                        <tr>
                            <td><a href="Low-Budget" style="width: inherit;">Low-Budget</a></td>
                        </tr>
                        <tr>
                            <td><a href="Mid-Range" style="width: inherit;">Mid-Range</a></td>
                        </tr>
                        <tr>
                            <td><a href="High-Range" style="width: inherit;">High-Range</a></td>
                        </tr>
                    </table>
                </a>
                <a href="Accesories" class="type">
                    <span><img style="height: 20px; width: 20px; margin-top: -5px;" src="template/IMG/hard-disk.png" alt="Accessories"></span>
                    <span>Accesories</span>
                    <table>
                        <tr>
                            <td><a href="Headphones" style="width: inherit;">Headphones</a></td>
                        </tr>
                        <tr>
                            <td><a href="Charger" style="width: inherit;">Phone Charger</a></td>
                        </tr>
                        <tr>
                            <td><a href="Case" style="width: inherit;">Case</a></td>
                        </tr>
                        <tr>
                            <td><a href="SSD_HDD" style="width: inherit;">Hard Drives</a></td>
                        </tr>
                    </table>
                </a>
            </div>
        </div>
        <div class="dropdown show" style="float: right;">
            <a href="javascript:void(0)" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span style="width: 22px;
                                            height: 22px;
                                            border-radius: 30px ;
                                            background-image: url('template/IMG/avatar.png');
                                            background-repeat: no-repeat;
                                            background-position: center;
                                            background-size: cover;
                                            margin-top:2px;
                                            float: left;
                                            ">
                        </span>
                <span style="float: left; margin-left: 5px; height:22px;">
                            <p>Account</p>
                        </span>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                <c:if test="${not empty User.getUserID()}">
                    <a class="dropdown-item" href="Profile.jsp"><c:out value="${User.getFullname()}"/></a>
                    <a class="dropdown-item" href="Order_Management_Page">
                        <span><img src="template/IMG/order-manage.png" style="width: 20px; height: 20px; margin-right: 2px; margin-top: -2px;" alt="Login"></span>
                        <span>Your Orders</span>
                    </a>
                </c:if>
                <c:if test="${empty User.getUserID()}">
                    <a class="dropdown-item" href="login">
                        <span><img src="template/IMG/login.png" style="width: 20px; height: 20px; margin-right: 2px; margin-top: -2px;" alt="Login"></span>
                        <span>Log In or Sign Up</span>
                    </a>
                </c:if>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="service-page">
                    <span><img src="template/IMG/information_and_help.png" style="width: 20px; height: 20px; margin-right: 2px; margin-top: -2px;" alt="?"></span>
                    <span>Help</span>
                </a>
                <div class="dropdown-divider"></div>
                <c:if test="${not empty User.getUserID()}">
                    <a class="dropdown-item" href="logout">
                        <span><img src="template/IMG/logout.png" style="width: 20px; height: 20px; margin-right: 2px; margin-top: -2px;" alt="?"></span>
                        <span>Log Out</span>
                    </a>
                </c:if>
            </div>
        </div>
        <c:if test="${not empty User.getUserID()}">
            <div style="float: right;">
                <a href="Cart_Page">
                    <span><img style="height: 20px; width: 20px; margin-top: -3px;" src="template/IMG/shopping-cart.png"></span>
                    <span>Cart</span>
                </a>
            </div>
        </c:if>
        <c:if test="${empty User.getUserID()}">
            <div style="float: right;">
                <a href="#" onclick="No_Account_Notify();">
                    <span><img style="height: 20px; width: 20px; margin-top: -3px;" src="template/IMG/shopping-cart.png"></span>
                    <span>Cart</span>
                </a>
            </div>
        </c:if>
    </div>
</header>

