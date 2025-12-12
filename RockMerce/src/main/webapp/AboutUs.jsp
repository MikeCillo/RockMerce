<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Supporto & Chi Siamo - RockMerce</title>
    <%@include file="menu/Menu.jsp" %>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
</head>

<style>
    /* 1. Layout Generale */
    body {
        /* Assicura che lo sfondo sia coordinato se non è già nel global.css */
        /* background-color: #1a1a1a;  <-- Scommenta se vedi bianco */
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
    }

    .support-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        min-height: 80vh; /* Centra tutto verticalmente */
        color: #c6ac8f;   /* Il tuo colore beige */
        text-align: center;
        padding: 20px;
    }

    /* 2. Sezione Descrizione Azienda */
    .brand-intro {
        max-width: 800px;
        margin-bottom: 60px;
    }

    .brand-intro h1 {
        font-size: 48px;
        text-transform: uppercase;
        letter-spacing: 4px;
        margin-bottom: 20px;
        font-family: "Phosphate", sans-serif; /* Lo stesso font del menu */
    }

    .brand-intro p {
        font-size: 20px;
        line-height: 1.6;
        font-weight: 300;
        color: #e0d0c0; /* Un beige leggermente più chiaro per leggere meglio */
    }

    /* 3. Griglia Contatti */
    .contact-grid {
        display: flex;
        gap: 80px; /* Spazio tra mail e telefono */
        flex-wrap: wrap;
        justify-content: center;
    }

    /* 4. Card del singolo contatto */
    .contact-card {
        display: flex;
        flex-direction: column;
        align-items: center;
        transition: transform 0.3s ease;
    }

    .contact-card:hover {
        transform: translateY(-10px); /* Leggero movimento verso l'alto */
    }

    .icon-box {
        font-size: 50px;
        margin-bottom: 20px;
        color: #c6ac8f;
    }

    .contact-label {
        font-size: 14px;
        text-transform: uppercase;
        letter-spacing: 2px;
        opacity: 0.8;
        margin-bottom: 10px;
        color: #fff; /* Bianco per contrastare con il beige */
    }

    .contact-value {
        font-size: 24px;
        font-weight: bold;
        color: #c6ac8f;
        text-decoration: none;
        border-bottom: 2px solid transparent;
        transition: border-bottom 0.3s;
    }

    .contact-value:hover {
        border-bottom: 2px solid #c6ac8f;
    }

    /* --- GESTIONE ALTO CONTRASTO --- */
    /* Se l'utente attiva l'accessibilità, tutto diventa leggibile al massimo */
    body.high-contrast .support-container h1,
    body.high-contrast .contact-value,
    body.high-contrast .icon-box {
        color: #ffffff !important;
    }

    body.high-contrast .contact-label,
    body.high-contrast .brand-intro p {
        color: #ffff00 !important; /* Giallo per i testi secondari */
    }
</style>

<body>

<div class="support-container">

    <div class="brand-intro">
        <h1>RockMerce</h1>
        <p>
            Più che un negozio, siamo il punto di riferimento per i musicisti che cercano l'eccellenza.
            Selezioniamo solo i migliori strumenti per garantire che il tuo suono sia esattamente come lo hai immaginato.
            Passione, qualità e supporto continuo: questo è il nostro credo.
        </p>
    </div>

    <div class="contact-grid">

        <div class="contact-card">
            <div class="icon-box">
                <i class="fa-solid fa-envelope"></i>
            </div>
            <span class="contact-label">Hai bisogno di informazioni?</span>
            <a href="mailto:support@rockmerce.com" class="contact-value">support@rockmerce.com</a>
            <span style="font-size: 12px; margin-top: 5px; opacity: 0.7;">Rispondiamo H24</span>
        </div>

        <div class="contact-card">
            <div class="icon-box">
                <i class="fa-solid fa-phone"></i>
            </div>
            <span class="contact-label">Parla con un esperto</span>
            <a href="tel:+390876357821" class="contact-value">+39 0876 357 821</a>
            <span style="font-size: 12px; margin-top: 5px; opacity: 0.7;">Lun-Ven 9:00 - 18:00</span>
        </div>

    </div>

</div>

<script src="./Scripts/accessibilita.js"></script>

</body>
</html>