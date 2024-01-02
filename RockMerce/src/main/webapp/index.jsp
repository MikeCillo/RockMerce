
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="menu/Menu.jsp" %>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <title>RockMerceOfficialWebsite</title>
        <script src="https://kit.fontawesome.com/bda5e6e885.js" crossorigin="anonymous"></script>
    </head>


<%-- jasnd --%>
    <style>


        .guitar{
            list-style-type: none;
            justify-items: center;
            margin-left: 18%;
        }


        .guitar li {
            float: left;
            margin: 50px;
            width: 200px;
            height: 350px;
            position: relative;
            transition: color 1.5s, width 1.5s, height 1.5s;
        }


        .guitar button{
            background-color:transparent;
            border: none;
        }


        .guitar button img{
            width: 200px;
            height: 350px;
            border: none;
            list-style-image: none;
            transition: background-color 1.5s, width 1.5s, height 1.5s,border 0.5s;
        }

        .guitar img:hover{
            width: 250px;
            height:  400px;
            transition:  background-color 1.5s, width 1.5s, height 1.5s,border 0.5s;
            border: black 2px solid;
        }

        .guitar button img:hover{
            width: 250px;
            height:  400px;
            transition:  background-color 1.5s, width 1.5s, height 1.5s,border 0.5s;
            border: black 2px solid;
        }

        #electric:hover{
            background-color: #FFC12C;
        }

        #classic:hover{
            background-color: #1C889E;
        }

        #semi:hover{
            background-color: #D10C0C;
        }



        .guitar a{
            font-family: "Phosphate";
            font-size: 30px;
        }
        a:visited{
            text-decoration-line: none;
            color: black;
        }


        a:link{
            background-color: transparent;
            text-decoration-line: none;
            color: black;
        }


        li:hover #electric {
            width: 250px;
            height:  400px;
            transition:  background-color 1.5s, width 1.5s, height 1.5s,border 0.5s;
            border: black 2px solid;
            background-color: #FFC12C;

        }

        li:hover #classic {
            width: 250px;
            height:  400px;
            transition:  background-color 1.5s, width 1.5s, height 1.5s,border 0.5s;
            border: black 2px solid;
            background-color: #1C889E;

        }

        li:hover #semi {
            width: 250px;
            height:  400px;
            transition:  background-color 1.5s, width 1.5s, height 1.5s,border 0.5s;
            border: black 2px solid;
            background-color: #D10C0C;

        }

    </style>



<body>



<ul class="guitar">

    <form action="CatalogoElectric-Servlet">
        <li>
            <button id="CatalogoElectricBUTTON">    <!--BOUNDARY OBJECT !-->
                <img id="electric" src="Images/elettrica.png" width=150" height="330">
                <a id="elettrica" href="CatalogoElectric-Servlet">ELECTRIC</a>
            </button>
        </li>
    </form>



    <form action="CatalogoClassic-Servlet">
        <li>
            <button id="CatalogoClassicBUTTON">     <!--BOUNDARY OBJECT !-->
                <img id="classic" src="Images/cla.png" width="150" height="330">
                <a id="classica" href="CatalogoClassic-Servlet">CLASSICAL</a>
            </button>
        </li>
    </form>



    <form action="CatalogoSemiAcustic-Servlet">
        <li>
            <button id="CatalogoSemiAcusticBUTTON" >    <!--BOUNDARY OBJECT !-->
                <img id="semi" src="Images/semi.png" width="150" height="330">
                <a id="semiAc" href="CatalogoSemiAcustic-Servlet">SEMI ACOUSTIC</a>
            </button>
        </li>
    </form>


</ul>
</body>



</html>
