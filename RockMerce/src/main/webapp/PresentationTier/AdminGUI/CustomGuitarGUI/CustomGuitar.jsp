
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${guitar.name} - CUSTOM</title>

    <%@include file="/menu/AdminMenu.jsp" %>
    <link rel="stylesheet" type="text/css" href="./Styles/GuitarCardADMIN.css">
    <link rel="stylesheet" type="text/css" href="./Styles/CustomGuitar.css">
    <script src="Scripts/AdminJS/UpdateGuitarValidation.js"></script>

</head>

<style>
    #customProd{
        float: left;
        width: 55%;
    }

    .BoatShip{
        float: right;
        margin-right: 20%;
    }

    .heads{
        font-size: 26px;
        font-weight: bold;
    }


    .errorMessage{
        text-shadow: -4px 0 #FFC12C;
        color: black;
        font-weight: bold;
        font-size: 24px;
    }


</style>
<body>

<div class="BoatShip">
    <button id="backCard">
        <div class="card">
            <div class="inside">
                <h2 id="cardProducer">${guitar.producer}</h2>
            </div>

            <div id="img">
                <img id="cardImage" src="${guitar.image}">
            </div>

            <div class="inside">
                <h3 id="cardName">${guitar.name}</h3>
            </div>

            <div class="inside">
                <p id="cardPrice" class="gPrice">€ ${guitar.price}0</p>
            </div>
        </div>
    </button>
</div>

<div id="customProd">


    <form method="post" action="CustomGuitarControl" onsubmit="return GuitarValidation()"-->

    <ul id="customGuitarUL">


            <li class="Field">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${guitar.name}" maxlength="50" minlength="1" required onchange="valName()">
                <span class="errorMessage" id="errorName"></span>
            </li>


            <li class="Field">
                <label for="producer">Producer:</label>
                <input type="text" name="producer" maxlength="20" id="producer" required value="${guitar.producer}" onchange="valProducer()">
                <span class="errorMessage" id="errorProd"></span>
            </li>


            <li class="Field">
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" value="${guitar.price}" required onchange="valPrice()">
                <span class="errorMessage" id="errorPrice"></span>
            </li>

            <li class="Field">
                <label for="description">Description:</label>
                <textarea rows="20" cols="60" id="description" name="description" onchange="valDescription()">
                    ${guitar.description}
                </textarea>
                <span class="errorMessage" id="errorDescription"></span>
            </li>

        <li class="Field">
            <label for="category" >GUITAR TYPE:</label>

            <select id="category"  name="category">
                <option>electric</option>
                <option>classic</option>
                <option>semiAcoustic</option>
            </select>   <br> <br>

            <span class="errorMessage"></span><br>
        </li>


        <li class="Field">
                <label for="color">Color:</label>
                <input type="text" id="color" name="color" required maxlength="20" onchange="valColor()" value="${guitar.color}">
                <span id="errorColor" class="errorMessage"></span>
            </li>


            <li class="Field">
                <label for="disp">Disponibility:</label>
                <input type="number" id="disp" name="disp" value="${guitar.disponibility}" required min="1" onchange="valDisp()">
                <span class="errorMessage" id="errorDisp"></span>
            </li>


        <li class="Field">
            <label for="visibility"  >VISIBILITY:</label>
            <select id="visibility"  name="visibility">
                <option>yes</option>
                <option>no</option>
            </select>   <br> <br>
            <span class="errorMessage"></span><br>
        </li>


        <li class="Field">
                <label for="YTsrc">GUITAR VIDEO</label>
                <iframe width="400" height="250" id="YTsrc" allow="autoplay" src="${guitar.sound}">
                </iframe>
                <label for="sound">Paste new URL:</label>
                <input type="text" onchange="videoLoad()"  value="${guitar.sound}" id="sound" name="sound" required>
                <span class="errorMessage"></span>
            </li>


            <input type="hidden" id="guitarId" name="guitarId" value="${guitar.id}">

            <li class="Field">
                <button  id="invia">APPLY</button><br><br>
            </li>

     </ul>
    </form>


</div>


<script>
    var visibility="${guitar.visibility}";
    var type="${guitar.category}";
</script>

<script>
    function start() {
        checkGuitar();
        checkGuitarVisibility();
    }
    window.onload = start;
</script>






</body>
</html>
