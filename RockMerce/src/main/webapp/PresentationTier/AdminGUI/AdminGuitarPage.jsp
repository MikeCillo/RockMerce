<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/menu/AdminMenu.jsp" %>
    <title>${guitar.name} GuitarShop-RockMerce</title>
    <link rel="stylesheet" type="text/css" href="./Styles/GuitarPageStyle.css">
</head>


<style>
    #Shop{
        background: #1C889E;
        color: white;
    }
</style>


<body>

<ul id="gPage">
    <li>
        <div id="product">                                     <!-- GUITAR'S PHOTO !-->
            <img id="gImage" src="${guitar.image}">
        </div>
    </li>


    <li>
        <div id="Details">                                                      <!-- GUITAR'S DETAILS !-->

            <form action="CustomGuitar-Servlet" method="post">

                 <div id="title">${guitar.producer}: ${guitar.name}</div><br>     <!-- GUITAR'S PRODUCER AND NAME !-->

                 <div id="disp">Disponibility: ${guitar.disponibility} </div><br>         <!-- GUITAR'S DISPONIBILITY !-->

                 <div id="col">Color: ${guitar.color} </div><br>                <!-- GUITAR'S COLOR !-->

                 <div id="price">Price: € ${guitar.price}0</div><br>             <!-- GUITAR'S PRICE !-->

                 <div id="vis">Visibility: ${guitar.visibility}</div><br>        <!-- GUITAR'S VISIBILITY !-->

                 <!-- HIDDEN GUITAR ID TO GET ITEM FROM DB!-->
                 <input type="hidden" id="guitarId" name="guitarId" value="${guitar.id}">

                <!-- BUTTON TO CUSTOM THE GUITAR TO CART !-->
                <button id="btnCart">CUSTOM</button>

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
