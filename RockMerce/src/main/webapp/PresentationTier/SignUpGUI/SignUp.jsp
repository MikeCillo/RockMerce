<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="LogicTier.Entità.Customer" %>
<html>
<head>
    <title>${newUser}-SingUp-RockMerce</title>
    <link rel="stylesheet" type="text/css" href="./Styles/SingUpStyle.css">
</head>


<style>

</style>


<body>




<ul id="signUpUl">

<li class="Field">
    <form action="BackHomepage-servlet">                                    <!--LOGO ONCLICK BACK HOMEPAGE-->
        <button id="home">
            <img id="logo" src="Images/logo.png" width="250" height="250">
        </button>
    </form>
</li>

<form id="SignUpForm" action="SignUpControl" method="post" onsubmit="return SignUpJsValidation()">                   <!--ON BTN CLICK NEXT SERVLET-->
        <li class="Field">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${customer.name}" onchange="valName()" required>        <!--INPUT NAME-->
            <span class="err" id="errName"></span>                                         <!--ERROR NAME-->
        </li>


        <li class="Field">
             <label for="surname">Surname:</label>
             <input type="text" id="surname" name="surname"  value="${customer.surname}" onchange="valSurname()" required>  <!--INPUT SURNAME-->
            <span class="err" id="errSurname"></span>                                 <!--ERROR SURNAME-->
        </li>

        <li class="Field">
             <label for="username">Username:</label>
             <input type="text" id="username" name="username" value="${customer.username}" onchange="valUsername()" required>         <!--INPUT USERNAME-->
            <span class="err" id="errUsername"></span>                             <!--ERROR USERNAME-->
         </li>

        <li class="Field">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" value="${customer.email}" onchange="valEmail()" required> <!--INPUT EMAIL-->
            <span class="err" id="errEmail"></span>                               <!--ERROR EMAIL-->
        </li>

        <li class="Field">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="${customer.password}" onchange="valPassword()" required>        <!--INPUT PASSWORD-->
            <span class="err" id="errPassword"></span>                                        <!--ERROR PASSWORD-->
            <div id="pw" >Minimum 8 characters<br>                                            <!--PASSWORD SPECIFICS-->
                At least one uppercase letter<br>
                At least one lowercase letter<br>
                At least one number<br>
                At least one special character
            </div>
        </li>

    <li class="Field">
        <label for="phone">Phone :</label>
        <input type="tel" id="phone" name="phone" value="${customer.phone}" onchange="valPhone()" required>  <!--pattern="[0-9]{10}" required-->
        <span class="err" id="errPhone"></span>
    </li>


    <div class="Field">
        <label for="country">Country:</label>
        <input type="text" id="country" name="country" value="${customer.country}" onchange="valCountry()" required>
        <span class="err" id="errCountry"></span>
    </div>



    <li class="Field">
        <label for="city">City:</label>
        <input type="text" id="city" name="city" value="${customer.city}" onchange="valCity()" required>
        <span class="err" id="errCity"></span>
    </li>

    <li class="Field">
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${customer.address}" onchange="valAddress()" required>
        <span class="err" id="errAddress"></span>
    </li>


    <li class="Field">
        <label for="card">Card number:</label>
        <input type="text" id="card" name="card"  placeholder="16 DIGITS" value="${card.cardNumber}" onchange="valCardNumber()" required>   <!--pattern="[0-9]{16}">!-->
        <span class="err" id="errCardNum"></span>
    </li>


    <li class="Field">
        <label for="owner">Card's Owner:</label>
        <input type="text" id="owner" name="owner" value="${card.owner}" onchange="valOwner()" required>
        <span class="err" id="errCardOwner"></span>
    </li>


    <li class="Field">
        <label for="expYear">Expire Date:</label>
        <label for="expYear"></label>
        <select id="expMonth" name="expMonth" required> </select>
        <select id="expYear" name="expYear"> </select>
        <span class="err"></span>
    </li>


    <li class="Field">
        <label for="cvv">CVV:</label>
        <input type="text" id="cvv" name="cvv"   value="${card.cvv}" onchange="valCvv()" required> <!--pattern="[0-9]{3}"required><br>!-->
        <span class="err" id="errCvv"></span>
    </li>



    <li class="Field">
                <button id="invia">CONTINUE</button><br><br>   <!--BUTTON VALIDATION (NEXT SERVLET)-->
                </form>

                <form action="LogIn-Servlet"  method="get" >
                <button id="LogInButton" >Click here to LogIn</button>
                </form>

        </li>
</form>

</ul>

<script>
    function start() {
        DateGenerator();
    }
    window.onload = start;
</script>


<script src="Scripts/SignUpJS/SignUpValidationJS.js"></script>


</body>
</html>
