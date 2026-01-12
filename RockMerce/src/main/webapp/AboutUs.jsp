<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Supporto & Chi Siamo - RockMerce</title>
    <%@include file="menu/Menu.jsp" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
</head>

<style>
    /* --- STILI GENERALI --- */
    body {
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
    }

    .support-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        min-height: 80vh;
        color: #c6ac8f;
        text-align: center;
        padding: 20px;
    }

    /* --- INTRODUZIONE --- */
    .brand-intro {
        max-width: 800px;
        margin-bottom: 60px;
    }

    .brand-intro h1 {
        font-size: 48px;
        text-transform: uppercase;
        letter-spacing: 4px;
        margin-bottom: 20px;
        font-family: "Phosphate", sans-serif;
    }

    .brand-intro p {
        font-size: 20px;
        line-height: 1.6;
        font-weight: 300;
        color: #e0d0c0;
    }

    /* --- SEZIONE SOSTENIBILITÀ --- */
    .sustainability-section {
        margin-bottom: 60px;
        width: 100%;
        max-width: 1000px;
        border-top: 1px solid #c6ac8f;
        border-bottom: 1px solid #c6ac8f;
        padding: 40px 0;
    }

    .sust-title {
        font-family: "Phosphate", sans-serif;
        font-size: 32px;
        text-transform: uppercase;
        margin-bottom: 10px;
        letter-spacing: 3px;
    }

    .sust-subtitle {
        font-size: 16px;
        font-style: italic;
        margin-bottom: 40px;
        opacity: 0.8;
    }

    .sust-grid {
        display: flex;
        justify-content: space-around;
        flex-wrap: wrap;
        gap: 30px;
    }

    .sust-card {
        flex: 1;
        min-width: 250px;
        text-align: center;
        padding: 10px;
    }

    .sust-card i {
        font-size: 40px;
        margin-bottom: 15px;
        color: #c6ac8f;
    }

    .sust-card h3 {
        font-size: 18px;
        text-transform: uppercase;
        margin-bottom: 10px;
        letter-spacing: 1px;
    }

    .sust-card p {
        font-size: 16px;
        line-height: 1.4;
        font-weight: 300;
        color: #e0d0c0;
    }

    /* --- CONTATTI --- */
    .contact-grid {
        display: flex;
        gap: 80px;
        flex-wrap: wrap;
        justify-content: center;
        margin-bottom: 60px;
    }

    .contact-card {
        display: flex;
        flex-direction: column;
        align-items: center;
        transition: transform 0.3s ease;
    }

    .contact-card:hover {
        transform: translateY(-10px);
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
        color: #fff;
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

    /* --- NUOVA SEZIONE: ASSISTENZA DINAMICA --- */
    .quick-support-area {
        margin-top: 20px;
        width: 100%;
        max-width: 600px;
        text-align: center;
        padding: 20px;
        border: 1px dashed #c6ac8f; /* Bordo tratteggiato elegante */
        border-radius: 10px;
    }

    .btn-assistenza {
        background-color: #c6ac8f;
        color: #0a0908;
        border: none;
        padding: 15px 30px;
        font-size: 18px;
        font-family: "Phosphate", sans-serif;
        cursor: pointer;
        transition: background 0.3s;
        border-radius: 5px;
    }

    .btn-assistenza:hover {
        background-color: #e0d0c0;
    }

    /* Form Nascosto inizialmente */
    #form-reso {
        display: none; /* NASCOSTO DI DEFAULT */
        margin-top: 20px;
    }

    .input-group {
        display: flex;
        flex-direction: column;
        gap: 15px;
        margin-bottom: 15px;
    }

    .custom-input {
        padding: 12px;
        border: 2px solid #c6ac8f;
        background-color: transparent;
        color: #c6ac8f;
        font-size: 16px;
        border-radius: 5px;
        outline: none;
    }

    .custom-input::placeholder {
        color: #c6ac8f;
        opacity: 0.6;
    }

    .success-message {
        display: none; /* NASCOSTO DI DEFAULT */
        color: #4CAF50; /* Verde successo (o usa beige se preferisci) */
        font-size: 20px;
        font-weight: bold;
        margin-top: 20px;
        padding: 20px;
        border: 1px solid #4CAF50;
    }

    /* --- ALTO CONTRASTO --- */
    body.high-contrast .support-container h1,
    body.high-contrast .contact-value,
    body.high-contrast .icon-box,
    body.high-contrast .sust-title,
    body.high-contrast .sust-card h3,
    body.high-contrast .sust-card i {
        color: #ffffff !important;
    }
    body.high-contrast .contact-label,
    body.high-contrast .brand-intro p,
    body.high-contrast .sust-card p,
    body.high-contrast .sust-subtitle {
        color: #ffff00 !important;
    }
    body.high-contrast .sustainability-section,
    body.high-contrast .quick-support-area,
    body.high-contrast .custom-input {
        border-color: #ffffff !important;
        color: #ffffff !important;
    }
    body.high-contrast .btn-assistenza {
        background-color: #ffff00 !important;
        color: #000000 !important;
        font-weight: bold;
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

    <div class="sustainability-section">
        <h2 class="sust-title">Our Green Vision</h2>
        <p class="sust-subtitle">Ambiente • Società • Economia</p>

        <div class="sust-grid">
            <div class="sust-card">
                <i class="fa-solid fa-leaf" aria-hidden="true"></i>
                <h3>Low Carbon Impact</h3>
                <p>Collaboriamo con brand che riducono la Carbon Footprint e utilizziamo materiali certificati.</p>
            </div>
            <div class="sust-card">
                <i class="fa-solid fa-handshake-simple" aria-hidden="true"></i>
                <h3>Filiera Etica</h3>
                <p>Trasparenza totale sui fornitori per garantire il rispetto dei diritti dei lavoratori.</p>
            </div>
            <div class="sust-card">
                <i class="fa-solid fa-recycle" aria-hidden="true"></i>
                <h3>Re-Commerce</h3>
                <p>Il nostro programma di ricondizionamento dà una seconda vita agli strumenti usati.</p>
            </div>
        </div>
    </div>

    <div class="contact-grid">
        <div class="contact-card">
            <div class="icon-box">
                <i class="fa-solid fa-envelope" aria-hidden="true"></i>
            </div>
            <span class="contact-label">Hai bisogno di informazioni?</span>
            <a href="mailto:support@rockmerce.com" class="contact-value" aria-label="Invia mail all'assistenza clienti">support@rockmerce.com</a>
        </div>
        <div class="contact-card">
            <div class="icon-box">
                <i class="fa-solid fa-phone" aria-hidden="true"></i>
            </div>
            <span class="contact-label">Parla con un esperto</span>
            <a href="tel:+390876357821" class="contact-value" aria-label="Chiama assistenza clienti">+39 0876 357 821</a>
        </div>
    </div>

    <div class="quick-support-area">

        <button id="btnOpenSupport" class="btn-assistenza" onclick="apriForm()">
            <i class="fa-solid fa-headset"></i> Assistenza o Reso
        </button>

        <div id="form-reso">
            <p style="margin-bottom: 15px; color: #c6ac8f;">Compila i dati per aprire un ticket rapido:</p>
            <div class="input-group">
                <input type="email" id="userEmail" class="custom-input" placeholder="La tua Email" required>
                <input type="text" id="userOrder" class="custom-input" placeholder="Numero Ordine (es. #12345)" required>
            </div>
            <button class="btn-assistenza" onclick="inviaRichiesta()">Invia Informazioni</button>
            <p id="errorMsg" style="color: #D10C0C; display: none; margin-top: 10px;">Per favore, compila entrambi i campi.</p>
        </div>

        <div id="successMsg" class="success-message">
            <i class="fa-solid fa-check-circle"></i><br>
            Richiesta effettuata! <br>
            <span style="font-size: 16px; font-weight: normal; color: inherit;">
                Verrai contattato alla tua mail personale da uno dei nostri collaboratori.
            </span>
        </div>

    </div>

</div>

<br><br>
<footer class="site-footer">
    <div id="ecoindex-badge"></div>
</footer>

<script src="https://cdn.jsdelivr.net/gh/cnumr/ecoindex_badge@3/assets/js/ecoindex-badge.js" defer></script>
<script src="./Scripts/accessibilita.js"></script>

<script>
    function apriForm() {
        // Nasconde il bottone principale
        document.getElementById("btnOpenSupport").style.display = "none";
        // Mostra il form
        document.getElementById("form-reso").style.display = "block";
    }

    function inviaRichiesta() {
        // Prende i valori
        var email = document.getElementById("userEmail").value;
        var order = document.getElementById("userOrder").value;
        var errorMsg = document.getElementById("errorMsg");

        // Controllo semplice: se sono vuoti mostra errore
        if (email === "" || order === "") {
            errorMsg.style.display = "block";
        } else {
            // Se pieni, nasconde il form e mostra il successo
            document.getElementById("form-reso").style.display = "none";
            document.getElementById("successMsg").style.display = "block";
        }
    }
</script>

</body>
</html>