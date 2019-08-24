<%--
  Created by IntelliJ IDEA.
  User: MinhPC
  Date: 3/1/2019
  Time: 9:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creating New Account</title>
    <link href="<c:url value='/template/css/bootstrap-4.1.3-dist/css/bootstrap.css'/>" rel="stylesheet" type="text/css">
</head>
<body style="font-family: Arial, Helvetica, sans-serif;background-color: skyblue;">
<div style="margin-top:300px;">
    <form action="NewAccount" method="post">
        <h1 style="text-align:  center;">Create New Account</h1>
        <p STYLE="color: red;text-align: center;">${Cannot_Create}</p>
        <p STYLE="color: red;text-align: center;">${Username_Detected}</p>
        <table align="center" cellspacing="10">
            <tr>
                <td>
                    <span><input type="text" class="textfield" placeholder="Type your username" name="Create_Username" style="height:45px;width: 300px;border-radius:5px;background-color:aliceblue;border:thin;padding-left:10px; "  /></span>
                </td>
                <p align="center" style="color: tomato ">${Space_Detected}</p>
            </tr>
            <tr>
                <td>
                    <span><input type="password" class="textfield" placeholder="Type your password" name="Create_Password" style="height:45px;width: 300px;border-radius:10px;background-color:aliceblue;border:thin;padding-left:10px;"  /></span>
                </td>
            </tr>
            <tr>
                <td>
                    <span><input type="password" class="textfield" placeholder="Confirm your password" name="Confirm_Password" style="height:45px;width: 300px;border-radius:10px;background-color:aliceblue;border:thin;padding-left:10px;"  /></span>
                </td>
            </tr>
            <tr>
                <td>
                    <span><input type="text" class="textfield" placeholder="Type your Fullname" name="Create_Fullname" style="height:45px;width: 300px;border-radius:10px;background-color:aliceblue;border:thin;padding-left:10px;"</span>
                </td>
            </tr>
            <tr>
                <td>
                    <span><input type="text" class="textfield" placeholder="Type your Address" name="Create_Address" style="height:45px;width: 300px;border-radius:10px;background-color:aliceblue;border:thin;padding-left:10px;" </span>
                </td>
            </tr>
            <tr>
                <td>
                    <span><input type="text" class="textfield" placeholder="Type your Phone Number"name="Create_Phone" style="height:45px;width: 300px;border-radius:10px;background-color:aliceblue;border:thin;padding-left:10px;" </span>
                </td>
            </tr>
            <tr>
                <td>
                    <span>
                        <select style="width: 300px; height: 30px;" name="Create_Gender">
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Others">Others</option>
                        </select>
                    </span>
                </td>
            </tr>
            <tr>
                <td>
                    <span><input type="date"class="textfield" placeholder="Type your BirthDate" name="Create_BirthDate" style="height:45px;width: 300px;border-radius:10px;background-color:aliceblue;border:thin;padding-left:10px;" </span>
                </td>
            </tr>
            <tr>
                <td>
                    <span><input type="text" class="textfield" placeholder="Type your Email" name="Create_Email" style="height:45px;width: 300px;border-radius:10px;background-color:aliceblue;border:thin;padding-left:10px;" </span>
                </td>
            </tr>
            <tr>
                <td align="left">
                    <button type="submit" style="border-radius:10px;background-color:midnightblue;color:azure;border-color:midnightblue;">Create Account</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>

