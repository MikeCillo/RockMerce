<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>${customer.username}-Purchases-RockMerce</title>
    <%@include file="/menu/Menu.jsp" %>
</head>

<style>

    #LoggedBtn{
        background-color: #c6ac8f;
        color: #0a0908;
        opacity: 1;
    }

    #checkoutTable{
        margin-top: 100px;
    }
    .HeadCheckout th{
        width: 200px;
        background-color: #22333B;
        color: #c6ac8f;
        font-size: 20px;
        border: 2px solid black;
    }

    .HeadCheckout td{
        text-align: center;
        width: 200px;
        background-color: #c6ac8f;
        color: #0a0908;
        font-size: 20px;
        border: 2px solid black;
    }

    .dataCheckout th{
        width: 200px;
        background-color: #22333B;
        color: #c6ac8f;
        font-size: 20px;
        border: 2px solid black;
    }
    .dataCheckout td{
        text-align: center;
        width: 200px;
        background-color: #c6ac8f;
        color: #0a0908;
        font-size: 20px;
        border: 2px solid black;
    }
</style>
<body>



    <c:forEach items="${checkouts}" var="checkout">
<table id="checkoutTable">
    <tr class="HeadCheckout">
        <th>ORDER DATE</th>
        <th>SEND DATE</th>
        <th>TOTAL</th>
    </tr>

    <tr class="HeadCheckout">
        <td>${checkout.orderDate}</td>
        <td>${checkout.sendDate}</td>
        <td>€ ${checkout.totalPrice}0</td>
    </tr>

</table>

        <table>
            <tr class="dataCheckout">
                <th>NAME</th>
                <th>PRODUCER</th>
                <th>CATEGORY</th>
                <th>COLOR</th>
                <th>QUANTITY</th>
                <th>PRICE</th>
            </tr>
        <c:forEach items="${checkout.guitars}" var="guitar">

    <tr class="dataCheckout">
            <td>${guitar.name}</td>
            <td>${guitar.producer}</td>
            <td>${guitar.category}</td>
            <td>${guitar.color}</td>
            <td>${guitar.disponibility}</td>
            <td>€ ${guitar.price}0</td>
    </tr>

        </c:forEach>
        </table>
        <br>
    </c:forEach>
</body>
</html>
