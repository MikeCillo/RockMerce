<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="LogicTier.Entità.Customer" %>
<%@ page import="LogicTier.Entità.Guitar" %>
<%@ page import="LogicTier.Entità.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<br>

<head>
    <title>${customer.username}-cart-RockMerce</title>
    <%@include file="/menu/Menu.jsp" %>
</head>


<style>

    #cartName{
        font-weight: bold;
        font-size: 28px;
    }

    #cart{
    background-color: #1C889E;
    color: white;
    opacity: 1;
    }

    .cartHeads th{
        width: 200px;
        background-color: #FFC12C;
        color: black;
        font-size: 20px;
        border: 2px solid black;
    }

    #cartData td{
        text-align: center;
        width: 200px;
        background-color: #1C889E;
        color: white;
        font-size: 20px;
        border: 2px solid black;
    }


    #cartData #btnRemove{
        background-color: transparent;
        border: none;
    }


    #buy{
        width: 200px;
        height: 50px;
        font-size: 24px;
        transition: border 0.5s,font-size 0.5s;

    }

    #buy:hover{
        font-size: 28px;
        border: 2px solid black;
        transition: border 0.5s, font-size 0.5s;
        background-color: whitesmoke;
    }

    #cartTotal{
        font-weight: bold;
        font-size: 28px;

    }
    #aLog:link{
        color: #D10C0C;
    }

    #aLog:visited{
        color: #D10C0C;
    }
</style>



<div id="cartName">YOUR CART:</div>
<table>
    <tr class="cartHeads">
        <th>NAME</th>
        <th>PRODUCER</th>
        <th>COLOR</th>
        <th>CATEGORY</th>
        <th>QUANTITY</th>
        <th>PRICE</th>
    </tr>

    <%int pos=0;%>
    <c:forEach items="${guitars}" var="guitar">

    <tr id="cartData">
        <td>${guitar.name}</td>
        <td>${guitar.producer}</td>
        <td>${guitar.color}</td>
        <td>${guitar.category}</td>
        <td>${guitar.disponibility}</td>
        <td>€ ${guitar.price}0</td>

            <td id="btnRemove">
                <form action="RemoveGuitarControl" method="post">
                    <input type="hidden" name="idGuitar" value="<%=pos%>">
                    <input type="hidden" name="idG" value="${guitar.id}">
                    <button class="RemoveBtn">REMOVE GUITAR</button>
                 </form>
            </td>

        </tr>
        <%pos+=1;%>

    </c:forEach>
</table>
</div>

    <div id="cartTotal">
        NUMBER OF PRODUCTS: ${cart.numGuitars}
        TOTAL: € ${cart.tempTotal}0

        <form action="FreeCartControl" method="post">
            <input type="hidden" name="idGuitar" value="<%=pos%>">
            <button class="RemoveBtn">FREE CART</button>
        </form>

    </div>


    <%
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer ==null){%>

                <div>YOU MUST BE LOGGED :<a id="aLog" href="LogIn-Servlet"> CLICK HERE TO LOGIN</a></div>

    <%}
        else {%>

    <form action="ConfirmCheckOutControl" method="post">
        <button id="buy">BUY</button>
    </form>


       <% }%>
</table>

</body>
</html>
