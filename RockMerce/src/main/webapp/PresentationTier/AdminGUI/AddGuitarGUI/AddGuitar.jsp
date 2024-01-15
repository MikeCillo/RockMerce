<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NEW PRODUCT - ROCKMERCE</title>

    <%@include file="/menu/AdminMenu.jsp" %>
    <link rel="stylesheet" type="text/css" href="./Styles/GuitarCardADMIN.css">
    <link rel="stylesheet" type="text/css" href="./Styles/CustomGuitar.css">

</head>


<body>

<div class="BoatShip">
    <button id="backCard">
        <input type="hidden" name="guitarId" value="${guitar.id}">
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

    <form method="post" action="AddGuitarControl" onsubmit="return GuitarValidation()" >

        <ul id="customGuitarUL">


            <li class="Field">
                <label for="image">INSERT GUITAR IMAGE:</label><span><br>Image must be already saved into Folder</span>
                <input id="image" name="image" type="text" value="${guitar.image}"  onchange="loadGuitarPic()">
                <span class="errorMessage" id="errorImg"></span>
            </li>


            <li class="Field">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${guitar.name}" maxlength="50" minlength="2" required onchange="valName()">
                <span class="errorMessage" id="errorName"></span>
            </li>


            <li class="Field">
                <label for="producer">Producer:</label>
                <input type="text" name="producer" minlength="2" maxlength="20" id="producer" required value="${guitar.producer}" onchange="valProducer()">
                <span class="errorMessage" id="errorProd">${erm.errProducer}</span>
            </li>


            <li class="Field">
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" value="${guitar.price}"  required onchange="valPrice()">
                <span class="errorMessage" id="errorPrice"></span>
            </li>


            <li class="Field">
                <label for="description">Description:</label>
                <textarea rows="20" cols="60" id="description" name="description" maxlength="200" required onchange="valDescription()">
                    ${guitar.description}
                </textarea>
                <span class="errorMessage" id="errorDescription"></span>
            </li>


            <li class="Field">
                <label for="category" >GUITAR CATEGORY:</label>

                <select id="category"  name="category">
                    <option>electric</option>
                    <option>classic</option>
                    <option>semiAcoustic</option>
                </select>   <br> <br>

                <span class="errorMessage"></span><br>
            </li>



            <li class="Field">
                <label for="color">Color:</label>
                <input type="text" id="color" name="color" required maxlength="20" minlength="2" onchange="valColor()" value="${guitar.color}">
                <span id="errorColor" class="errorMessage"></span>
            </li>



            <li class="Field">
                <label for="disp">Disponibility:</label>
                <input type="number" id="disp" name="disp" value="${guitar.disponibility}" required min="1" max="999"  onchange="valDisp()">
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
                <label for="YTsrc">GUITAR VIDEO:</label><br>
                <iframe width="400" height="250" id="YTsrc" allow="autoplay" src="${guitar.sound}">
                </iframe>
                <label for="sound">Paste new URL:</label>
                <input type="text" onfocusout="videoLoad()"  value="${guitar.sound}" id="sound" name="sound" required>
                <span class="errorMessage"></span>
            </li>



            <li class="Field">
                <input type="hidden" id="guitarId" name="guitarId" value="${guitar.id}">
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
<script src="Scripts/AdminJS/UpdateGuitarValidation.js"></script>




</body>
</html>
