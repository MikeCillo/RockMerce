<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./Styles/LoginStyle.css">
    <title>RockMerce-Login</title>


</head>


<style>

    a:link{
        color: #D10C0C;
    }

    a:visited{
        color: #D10C0C;
    }


    #err{
        font-size: 24px;
        color: #D10C0C;
    }


</style>
<body>


<form name="login" action="LogInControl" onsubmit="test()" method="post">

    <div id="LoginArea">

        <div id="err">${error}</div>
    <div class="Field">
        <label for="emUs">Email/Username:</label>
        <input type="text" id="emUs" name="emUs" required>
    </div>

    <div class="Field">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>

    <div class="Field">
        <button id="invia">LOGIN</button><br>       <!--BOUNDARY OBJECT -->
    </div>
    </div>
</form>

<div class="Field">
<form action="SignUpServlet">
    <button id="SignUp">SignUp</button><br>
</form>
</div>


</body>
</html>
