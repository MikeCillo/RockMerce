package GestioneAutenticazione.benchmarks;


import LogicTier.Entità.Customer;

public class AutenticazioneServiceAdapter {

    private final MockCustomerDAO customerDAO = new MockCustomerDAO();

    public Customer customerLogIn(final String emUs, final String password) {


        if(!customerDAO.doCheckEmail(emUs) && !customerDAO.doCheckUsername(emUs)){
            return null;
        }
        final Customer customer = customerDAO.doCheckLogin(emUs, password);

        if (customer != null) {
            return customer;
        }
        return null;
    }
}