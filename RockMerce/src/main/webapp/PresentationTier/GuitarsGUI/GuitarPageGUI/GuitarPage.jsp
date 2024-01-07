<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/menu/Menu.jsp" %>
    <title>${guitar.name} -RockMerce</title>
    <link rel="stylesheet" type="text/css" href="./Styles/GuitarPageStyle.css">
</head>


<body>


<ul id="gPage">
    <li>
        <div id="product">                                     <!-- GUITAR'S PHOTO !-->
            <img id="gImage" src="${guitar.image}">
        </div>
    </li>




    <li>
         <div id="Details">                  <!-- GUITAR'S DETAILS !-->
             <form action="AddGuitarToCartControl" method="post">

                    <div id="title">${guitar.producer}: ${guitar.name}</div><br>       <!-- GUITAR'S PRODUCER AND NAME !-->

                    <div id="disp">Disponibility: ${guitar.disponibility} </div><br>             <!-- GUITAR'S DISPONIBILITY !-->

                    <div id="col">Color: ${guitar.color} </div><br>                     <!-- GUITAR'S COLOR !-->

                    <div id="price">Price: € ${guitar.price}0</div><br>                 <!-- GUITAR'S PRICE !-->

                     <!-- SELECT AMOUNT OF GUITAR TO ADD TO CART!-->
                    <div class="GuitarField" id="quantity">Choose quantity:
                        <input type="number" id="quant"  name="quant"  value="1" min="1" max="${guitar.disponibility}">
                        <span id="ErrorInput"></span>
                    </div>

                 <!-- HIDDEN GUITAR ID TO GET ITEM FROM DB!-->
                 <input type="hidden" id="guitarId" name="guitarId" value="${guitar.id}">

                 <!-- BUTTON TO ADD THE GUITAR TO CART !-->
                 <button id="btnCart">ADD TO CART</button>

             </form>

        </div>
    </li>

</ul>



    <div id="description">                                              <!-- GUITAR'S DESCRIPTION !-->
            <div>${guitar.description}</div>
        <iframe  id="YTsrc" allow="autoplay" src="${guitar.sound}"></iframe>     <!-- GUITAR'S VIDEO !-->
    </div>


</body>
</html>
