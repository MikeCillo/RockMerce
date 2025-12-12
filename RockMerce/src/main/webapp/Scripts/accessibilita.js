document.addEventListener("DOMContentLoaded", function () {
    // 1. Creazione del pulsante singolo
    const toolbar = document.createElement("div");
    toolbar.id = "accessibilita-toolbar";

    const contrastBtn = document.createElement("button");
    contrastBtn.innerHTML = "👁️ Contrasto"; // Puoi cambiare il testo qui
    contrastBtn.id = "btn-contrast";
    contrastBtn.setAttribute("aria-label", "Attiva o disattiva alto contrasto");

    // Azione al click
    contrastBtn.onclick = function() {
        document.body.classList.toggle("high-contrast");
        // Salva la scelta dell'utente per il futuro
        const isActive = document.body.classList.contains("high-contrast");
        localStorage.setItem("highContrast", isActive);
    };

    toolbar.appendChild(contrastBtn);
    document.body.appendChild(toolbar);

    // 2. Controllo all'avvio: l'utente aveva attivato il contrasto l'ultima volta?
    if (localStorage.getItem("highContrast") === "true") {
        document.body.classList.add("high-contrast");
    }
});