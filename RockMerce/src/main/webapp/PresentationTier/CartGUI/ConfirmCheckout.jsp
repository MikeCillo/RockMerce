<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<html>
<head>
    <title>${customer.username}-COMPLETE PURCHASE</title>
    <link rel="stylesheet" type="text/css" href="./Styles/CompletedPurchase.css">
</head>

<body>

<h1 class="confirm">${customer.name} ${customer.surname}
    YOUR ORDER HAS BEEN PLACED <br>
    Estimated delivery: ${checkout.sendDate}</h1>



<ul id="checkoutBUTTONS">


    <li id="liHomePage">
        <form action="BackHomepage-servlet" >
            <button id="homePagebtn">HOMEPAGE</button>
        </form>
    </li>

</ul>


</body>

</html>
