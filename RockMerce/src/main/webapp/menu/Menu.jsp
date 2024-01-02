<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<html>

<head>

    <link rel="stylesheet" type="text/css" href="./Styles/newMenu.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <script src="https://kit.fontawesome.com/bda5e6e885.js" crossorigin="anonymous"></script>

</head>




<body>


<ul class="menu" >

    <!-- ABOUT US BUTTON-->
    <li>
        <form action="AboutUs-servlet">
            <button id="aboutUs" class="btnR">
                <i class="fa fa-microphone" style="font-size:20px"></i>
                <span>ABOUT US</span>
            </button>
        </form>
    </li>

            <!-- SHOP BUTTON-->
    <li>
        <form action="CatalogoCompleto-Servlet">
            <button id="shop" class="btnR">
                <i class="fas fa-guitar" style="font-size: 20px"></i>
                <span>SHOP</span>
            </button>
        </form>
    </li>

         <!-- CART BUTTON -->
    <li class="Right">
        <form action="CartPage-Servlet" method="post">
            <button id="cart">                               <!-- BOUNDARY OBJECT -->
                <i class="fa fa-shopping-cart" style="font-size:20px"></i>
                <span>CART</span>
            </button>
        </form>
    </li>


    <!-- LOGIN BUTTON -->

    <li class="Right" id="log">
        <form action="LogIn-Servlet">
            <button id="LogInBUTTON">   <!-- BOUNDARY OBJECT -->
                <i class="fa fa-sign-in" style="font-size:20px"></i>
                <span>LOGIN</span>
            </button>
        </form>
    </li>



    <!-- SIGN UP BUTTON -->
    <li class="Right" id="signUp">
        <form action="SignUpServlet">
            <button id="SignUpBUTTON">      <!-- BOUNDARY OBJECT -->
                <i class="fa fa-customer" style="font-size:20px" ></i>
                <span>SIGN UP</span>
            </button>
        </form>
    </li>



    <!-- USER BUTTON  -->
    <li class="Right" id="LoggedUser">
        <div class="dropdown">
            <button id="LoggedBtn" class="dropbtn">${customer.username}
                <i class="fa fa-customer" style="font-size:20px"></i>
            </button>
            <div class="dropdown-content">
                <a href="Checkouts-Servlet">MY ORDERS</a>
                <a id="LogOutBUTTON" href="LogOut-Servlet">EXIT</a>
            </div>
        </div>
    </li>


</ul>

<form action="BackHomepage-servlet" >
    <button id="logo" >
        <img src="./Images/logo.png" width="150" height="150">
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
            menu.appendChild(userLog);


        }
        else {
            const log = document.getElementById("log");
            const sign = document.getElementById("signUp");
            const userLog = document.getElementById("LoggedUser");

            userLog.remove();
            const menu = document.getElementById("menu");
            menu.append(log);
            menu.appendChild(sign);

        }

    }
    window.onload = CheckLoggedUser;

</script>
</body>
</html>
