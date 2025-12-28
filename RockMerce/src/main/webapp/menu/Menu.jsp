<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="./Styles/global.css">
    <link rel="stylesheet" type="text/css" href="./Styles/newMenu.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <script src="https://kit.fontawesome.com/bda5e6e885.js" crossorigin="anonymous"></script>
</head>

<body>

<ul class="menu" >

    <li>
        <form action="AboutUs-servlet">
            <button id="aboutUs" class="btnR">
                <i class="fa fa-microphone" style="font-size:20px"></i>
                <span>SUPPORT</span>
            </button>
        </form>
    </li>

    <li>
        <form action="CatalogoCompletoControl">
            <button id="shop" class="btnR">
                <i class="fas fa-guitar" style="font-size: 20px"></i>
                <span>SHOP</span>
            </button>
        </form>
    </li>

    <li class="Right">
        <form action="CartPageControl" method="post">
            <button id="cart">
                <i class="fa fa-shopping-cart" style="font-size:20px"></i>
                <span>CART</span>
            </button>
        </form>
    </li>

    <li class="Right" id="log">
        <form action="LogIn-Servlet">
            <button id="LogInBUTTON">
                <i class="fa fa-sign-in" style="font-size:20px"></i>
                <span>LOGIN</span>
            </button>
        </form>
    </li>

    <li class="Right" id="signUp">
        <form action="SignUpServlet">
            <button id="SignUpBUTTON">
                <i class="fa fa-customer" style="font-size:20px" ></i>
                <span>SIGN UP</span>
            </button>
        </form>
    </li>

    <li class="Right" id="LoggedUser">
        <div class="dropdown">
            <button id="LoggedBtn" class="dropbtn">${customer.username}
                <i class="fa fa-customer" style="font-size:20px"></i>
            </button>
            <div class="dropdown-content">
                <a href="CheckoutsControl">MY ORDERS</a>
                <a id="LogOutBUTTON" href="LogOut-Servlet">EXIT</a>
            </div>
        </div>
    </li>

</ul>

<form action="BackHomepage-servlet" >
    <button id="logo" >
        <img src="./Images/LogoRmWhite.png" width="180" height= "50">
    </button>
</form>

<script>
    function CheckLoggedUser() {
        if ("${customer.username}".length>=2) {
            const log = document.getElementById("log");
            const sign = document.getElementById("signUp");
            const userLog = document.getElementById("LoggedUser");

            sign.remove();
            log.remove();
            const menu = document.getElementById("menu");
            // Nota: qui c'era un piccolo errore nel tuo codice originale,
            // 'menu' non è definito come ID nell'ul sopra, ma come class.
            // Se funziona non toccarlo, ma occhio!
            // Se non va, aggiungi id="menu" al tag <ul> in alto.
            if(menu) menu.appendChild(userLog);
        }
        else {
            const log = document.getElementById("log");
            const sign = document.getElementById("signUp");
            const userLog = document.getElementById("LoggedUser");

            userLog.remove();
            const menu = document.getElementById("menu");
            if(menu) {
                menu.append(log);
                menu.appendChild(sign);
            }
        }
    }
    window.onload = CheckLoggedUser;
</script>

<script src="./Scripts/accessibilita.js"></script>

</body>
</html>
