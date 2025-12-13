<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="menu/Menu.jsp" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <title>RockMerceOfficialWebsite</title>
    <script src="https://kit.fontawesome.com/bda5e6e885.js" crossorigin="anonymous"></script>
</head>

<style>
    /* 1. Il contenitore occupa quasi tutta l'altezza dello schermo (80vh) */
    .categories-container {
        display: flex;
        justify-content: center; /* Centra orizzontalmente */
        align-items: center;     /* Centra verticalmente */
        width: 100%;
        min-height: 80vh;        /* Fa sì che le chitarre stiano bene al centro della pagina */
        gap: 8%;                 /* Molto spazio tra le chitarre per riempire la larghezza */
        padding: 20px;
        box-sizing: border-box;  /* Evita che il padding rompa il layout */
    }

    /* 2. Stile del bottone (il contenitore della singola chitarra) */
    .guitar-card {
        background: transparent;
        border: none;
        cursor: pointer;
        text-align: center;
        transition: transform 0.4s ease; /* Animazione fluida */

        /* Assicura che il bottone non sia minuscolo */
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    /* 3. ANIMAZIONE MINIMAL: Ingrandimento leggero + sollevamento */
    .guitar-card:hover {
        transform: scale(1.1) translateY(-10px);
        /* Si ingrandisce del 10% e sale leggermente verso l'alto */
    }

    /* 4. IMMAGINI PIÙ GRANDI */
    .guitar-card img {
        display: block;
        width: 280px;            /* Larghezza molto aumentata (era 150/200) */
        height: auto;            /* L'altezza si adatta in proporzione */
        max-height: 550px;       /* Limite altezza per non uscire dallo schermo */
        object-fit: contain;
        filter: drop-shadow(0 10px 10px rgba(0,0,0,0.3)); /* Ombra elegante dietro la chitarra */
    }

    /* 5. TESTO PIÙ GRANDE */
    .card-title {
        display: block;
        font-family: "Phosphate", sans-serif;
        font-size: 40px;         /* Testo molto più grande e leggibile */
        color: #c6ac8f;
        margin-top: 25px;
        letter-spacing: 2px;     /* Spazia un po' le lettere per stile */
    }

    /* Rimuove stili brutti dei form */
    .clean-btn {
        background: none;
        border: none;
        padding: 0;
        cursor: pointer;
    }

    /* Adattamento per schermi piccoli (Tablet/Laptop piccoli) */
    @media screen and (max-width: 1200px) {
        .guitar-card img {
            width: 200px; /* Si rimpiccioliscono un po' se lo schermo è piccolo */
        }
        .card-title {
            font-size: 30px;
        }
    }
</style>

<body>

<div class="categories-container">

    <form action="CatalogoElectricControl">
        <button class="clean-btn guitar-card" id="CatalogoElectricBUTTON">
            <img src="Images/Elettrica.png" alt="">
            <span class="card-title">ELECTRIC</span>
        </button>
    </form>

    <form action="CatalogoClassicControl">
        <button class="clean-btn guitar-card" id="CatalogoClassicBUTTON">
            <img src="Images/ClassicaPng.png" alt="">
            <span class="card-title">CLASSICAL</span>
        </button>
    </form>

    <form action="CatalogoSemiAcusticControl">
        <button class="clean-btn guitar-card" id="CatalogoSemiAcusticBUTTON">
            <img src="Images/SemiAcousticPng.png" alt="">
            <span class="card-title">SEMI-ACOUSTIC</span>
        </button>
    </form>

</div>

<br><br>
<footer class="site-footer">
    <div id="ecoindex-badge"></div>
</footer>
<script src="https://cdn.jsdelivr.net/gh/cnumr/ecoindex_badge@3/assets/js/ecoindex-badge.js" defer></script>

</body>
</html>