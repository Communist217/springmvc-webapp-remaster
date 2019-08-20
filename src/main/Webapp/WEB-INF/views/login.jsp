<%--
  Created by IntelliJ IDEA.
  User: Hau_hypress
  Date: 8/15/2019
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" type="text/css" href='<c:url value="/template/css/bootstrap-4.1.3-dist/css/bootstrap.css"/>'/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="<c:url value='/template/css/Login_Content.css'/>" />
        <title>Login/Register Form</title>
    </head>
    <body>
        <div id="logreg-forms">
            <form action="main-page" method="post" class="form-signin">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign in</h1>
                <div class="social-login">
                    <button class="btn facebook-btn social-btn" type="button"><span><i class="fab fa-facebook-f"></i> Sign in with Facebook</span> </button>
                    <button class="btn google-btn social-btn" type="button"><span><i class="fab fa-google-plus-g"></i> Sign in with Google+</span> </button>
                </div>
                <p style="text-align:center"> OR  </p>
                <input type="text" id="inputUsername" class="form-control" name="Username" value="<c:out value="${Username}"></c:out>" placeholder="Username" required="required" autofocus="">
                <input type="password" id="inputPassword" class="form-control" name="Password" placeholder="Password" required="required">

                <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i> Sign in</button>
                <a href="#" id="forgot_pswd">Forgot password?</a>
                <hr>
                <!-- <p>Don't have an account!</p>  -->
                <button class="btn btn-primary btn-block" type="button" id="btn-signup"><i class="fas fa-user-plus"></i> Sign up New Account</button>
            </form>

            <form action="ResetPassword" method="post" class="form-reset">
                <input type="text" name="Username" class="form-control" placeholder="Username" required="required" autofocus="">
                <input type="text" name="Email" class="form-control" placeholder="Email" required="required" autofocus="">
                <input type="password" name="Change_Password" class="form-control" placeholder="New Password" required="required" autofocus="">
                <input type="password" name="Confirm_Password" class="form-control" placeholder="Confirm Password(Re-type your new password)" required="required" autofocus="">
                <button class="btn btn-primary btn-block" type="submit">Reset Password</button>
                <a href="#" id="cancel_reset"><i class="fas fa-angle-left"></i> Back</a>
            </form>

            <form action="NewAccount" method="post" class="form-signup">
                <div class="social-login">
                    <button class="btn facebook-btn social-btn" type="button"><span><i class="fab fa-facebook-f"></i> Sign up with Facebook</span> </button>
                </div>
                <div class="social-login">
                    <button class="btn google-btn social-btn" type="button"><span><i class="fab fa-google-plus-g"></i> Sign up with Google+</span> </button>
                </div>

                <p style="text-align:center">OR</p>

                <input type="text" class="form-control" placeholder="Type your username" name="Create_Username"  required="required" autofocus="">
                <input type="email" class="form-control" placeholder="Type your Email" name="Create_Email" required autofocus="">
                <input type="password" class="form-control" placeholder="Type your password" name="Create_Password" required autofocus="">
                <input type="password" class="form-control" placeholder="Confirm your password" name="Confirm_Password" required autofocus="">
                <input type="text" class="form-control" placeholder="Type your Fullname" name="Create_Fullname" required="required" autofocus="">
                <input type="text" class="form-control" placeholder="Type your Address" name="Create_Address" required="required" autofocus="">
                <input type="text" class="form-control" placeholder="Type your Phone Number" name="Create_Phone" required="required" autofocus="">
                <input class="form-control" type="date" name="Create_BirthDate" id="example-date-input">
                <span>
                        <select class="form-control" id="exampleFormControlSelect1" name="Create_Gender" required>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Others">Others</option>
                        </select>
                    </span>
                <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-user-plus"></i> Sign Up</button>
                <a href="#" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
            </form>
            <br>

        </div>
        <p style="text-align:center">
            <a href="http://bit.ly/2RjWFMfunction" target="_blank" style="color:black">Special thank to Artin</a>
        </p>
        <!--Load jquery lib-->
        <script src="<c:url value='/template/script/JQuery/jquery-3.3.1.min.js'/>"></script>
        <!--Load bootstrap script-->
        <script src="<c:url value='/template/script/Bootstrap/popper.min.js'/>"></script>
        <script src="<c:url value='/template/script/Bootstrap/bootstrap.js'/>"></script>
        <script src="<c:url value='/template/script/Login_Page.js'/>"></script>
    </body>
</html>
