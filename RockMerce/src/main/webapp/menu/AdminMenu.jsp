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



    <!-- PRODUCTS -->
    <li>
        <form action="AdminGuitars-Servlet">
            <button id="shop" class="btnR">
                <i class="fas fa-guitar" style="font-size: 20px"></i>
                <span>PRODUCTS</span>
            </button>
        </form>
    </li>



    <!-- ORDERS -->
    <li id="orders">
        <form action="Orders-Servlet" method="post">
            <button >
                <i class="fa fa-shopping-basket" style="font-size:20px" ></i>
                <span>ORDERS</span>
            </button>
        </form>
    </li>

    <!-- USER BUTTON  -->
    <li class="Right" id="LoggedUser">
        <div class="dropdown">
            <button id="LoggedBtn" class="dropbtn">${admin.username}
                <i class="fa fa-customer" style="font-size:20px"></i>
            </button>
            <div class="dropdown-content">
                <a href="exit-servlet">EXIT</a>
            </div>
        </div>
    </li>




</ul>

<form action="AdminHomepage-Servlet" >
    <button id="logo" >
        <img src="./Images/logo.png" width="150" height="150">
    </button>
</form>



</body>
</html>
