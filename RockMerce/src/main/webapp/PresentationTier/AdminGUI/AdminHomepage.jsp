<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>${admin.username}</title>
    <%@include file="/menu/AdminMenu.jsp" %>
</head>

<style>
    #WELCOME {
        font-size: 24px;
        font-weight: bold;
        text-transform: uppercase;

    }


    #admButtons{
        margin-top: 50px;
        list-style-type: none;

    }



    .admLi button{
        margin-top: 20px;
        margin-right: 500px;
        width: 250px;
        height: 50px;
        font-size: 20px;
        color: #FFC12C;
        background-color: black;
        border:2px solid #FFC12C;
        transition: border 0.5s, background-color 0.5s, color 0.5s, font-size 0.5s;
    }

    .admLi button:hover{
       font-size: 24px;
       border: 2px solid black;
       color: black;
       background-color: #FFC12C;
       transition: border 0.5s, background-color 0.5s, color 0.5s, font-size 0.5s;
    }



</style>

<body>


<div id="WELCOME">
  <p>WELCOME BACK <span>${admin.name} ${admin.surname} !</span></p>
  <p>YOUR EARNINGS:€ <span>${earnings}0 </span></p>
</div>

<ul id="admButtons">

    <li class="admLi">
        <form action="CustomGuitarChoose-Servlet" method="post">
        <button>
            UPDATE GUITAR
        </button>
        </form>
    </li>


    <li class="admLi">
        <form action="DeleteGuitarServlet" method="post">
        <button>
            DELETE GUITAR
        </button>
        </form>
    </li>


    <li class="admLi">
        <form action="AddGuitar-Servlet" method="post">
        <button>
            ADD GUITAR
        </button>
        </form>
    </li>

</ul>


</body>
</html>
