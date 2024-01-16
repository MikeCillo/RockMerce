<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ORDERS - ROCKMERCE</title>
    <%@include file="/menu/AdminMenu.jsp" %>
</head>



<style>

    #WELCOME {
        font-size: 24px;
        font-weight: bold;
        text-transform: uppercase;

    }

    #orders button{
        background-color: #1C889E;
        color: white;
        opacity: 1;
    }

    .HeadCheckout th{
        width: 200px;
        background-color: #FFC12C;
        color: black;
        font-size: 20px;
        border: 2px solid black;
    }

    .HeadCheckout td{
        text-align: center;
        width: 200px;
        background-color: #1C889E;
        color:whitesmoke;
        font-size: 20px;
        border: 2px solid black;
    }

    .dataCheckout th{
        width: 200px;
        background-color: #FFC12C;
        color: black;
        font-size: 20px;
        border: 2px solid black;
    }
    .dataCheckout td{
        text-align: center;
        width: 200px;
        background-color: #1C889E;
        color: white;
        font-size: 20px;
        border: 2px solid black;
    }

    #CheckoutG{
        margin-top:100px;
        color: black;
        font-size: 20px;
        font-weight: bold;
    }
    #ContentG{
        font-size: 20px;
        font-weight: bold;
    }
</style>

<body>



<c:forEach items="${checkouts}" var="checkout">
    <p id="CheckoutG">CHECKOUT:</p>
    <table id="checkoutTable">
        <tr class="HeadCheckout">
            <th>CUSTOMER</th>
            <th>EMAIL</th>
            <th>PHONE</th>
            <th>ADDRESS</th>
            <th>ORDER DATE</th>
            <th>SEND DATE</th>
            <th>TOTAL</th>
        </tr>

        <tr class="HeadCheckout">
            <td>${checkout.customer.name} ${checkout.customer.surname}</td>
            <td>${checkout.customer.email}</td>
            <td>${checkout.customer.phone}</td>
            <td>${checkout.customer.country} ${checkout.customer.city} ${checkout.customer.address}</td>
            <td>${checkout.orderDate}</td>
            <td>${checkout.sendDate}</td>
            <td>€ ${checkout.totalPrice}0</td>
        </tr>

    </table>

    <p id="ContentG">CONTENT:</p>
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

<div id="WELCOME">
    <p>YOUR EARNINGS:€ <span>${earnings}0 </span></p>
</div>

</body>


</html>
