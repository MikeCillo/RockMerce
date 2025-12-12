package GestioneAutenticazione.benchmarks;


import LogicTier.Entità.Customer;

// Adapter che usa i Mock per isolare l'AutenticazioneService
public class AutenticazioneServiceAdapter {

    private final MockCustomerDAO customerDAO = new MockCustomerDAO();

    // Riflette la logica di AutenticazioneService.CustomerLogIn
    public Customer customerLogIn(String emUs, String password) {

        // La logica di controllo nel service è:
        // if(!customerDAO.doCheckEmail(emUs) && !customerDAO.doCheckUsername(emUs))

        // Eseguiamo il check simulato
        if(!customerDAO.doCheckEmail(emUs) && !customerDAO.doCheckUsername(emUs)){
            return null;
        }

        // Recupera il Customer se la password è corretta (logica gestita dal Mock)
        Customer customer = customerDAO.doCheckLogin(emUs, password);

        if (customer != null) {
            return customer;
        }
        return null;
    }
}