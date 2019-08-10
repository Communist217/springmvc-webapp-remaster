<%--
  Created by IntelliJ IDEA.
  User: MinhPC
  Date: 2/24/2019
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="Style/bootstrap.min.css" rel="stylesheet" type="text/css">
        <title>Login</title>
    </head>
    <body style="background: rgb(143, 254, 173) ; font-family: 'Calibri', sans-serif;">
        <div style="margin-top:300px;">
        <form action="Log_In_MainPage" method="post">
            <h1 style="text-align:  center;">Log In</h1>
            <p style="text-align: center; color: green" ><b>${LoggedOut}</b> </p>
            <p style="color: red; text-align: center;"><b>${False}</b></p>
            <p style="color: mediumblue; text-align: center;"><b>${CreatedSucceed}</b></p>
            <p style="color: orange; text-align: center;"><b>${Changed}</b></p>
            <table align="center">
                <tr>
                    <td><i>Username</i></td>
                    <td>
                        <span><input type="text" class="textfield" placeholder="Type your username" name="Username" value="Username" style="height:25px;width:250px;border-radius:5px;background-color: aliceblue;border:thin;padding-left:10px; " /></span>
                    </td>
                </tr>
                <tr>
                    <td><i>Password</i></td>
                    <td>
                        <span><input type="password" class="textfield" placeholder="Type your password" name="Password" value="12345" style="height:25px;width:250px;border-radius:5px;background-color: aliceblue;border:thin;padding-left:10px;"  /></span>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><a href="Password_adjust.jsp">Forgot Password?</a> </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="left">
                        <button type="submit" style="border-radius:10px; background-color:midnightblue; color:azure; border-color:midnightblue;">Log In</button>
                    </td>
                </tr>
            </table>
        </form>
        <div align="center" style="margin-top: 200px;">
            <table>
                <tr>
                    <td></td>
                    <td>
                        <form action="CreateAccount.jsp" >
                            <button type="submit" style="border-radius:10px; background-color:midnightblue; color:azure; border-color:midnightblue; width:100px;" >Create New Account</button>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    </body>
    <!--Load jquery script lib-->
    <script src="Script/js/JQuery/jquery-3.3.1.min.js"></script>
    <!--Load  bootstrap script lib-->
    <script src="Script/js/Bootstrap/popper.min.js"></script>
    <script src="Script/js/Bootstrap/bootstrap.js"></script>
</html>
