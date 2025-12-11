package GestioneAutenticazione.benchmarks;

import LogicTier.Entità.Cart;
import LogicTier.Entità.Customer;
import java.util.concurrent.TimeUnit;

// Simula la CustomerDAO per il benchmark JMH
public class MockCustomerDAO {

    // Simula un costo combinato di I/O + Logica di Hashing/Verifica (150 microsecondi)
    private static final long MOCK_DB_LATENCY_NS = 150_000;

    public boolean doCheckEmail(String emUs) {
        // Simulazione di una lookup veloce nel DB
        try {
            TimeUnit.NANOSECONDS.sleep(MOCK_DB_LATENCY_NS / 3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return true; // Simula che l'utente esista
    }

    public boolean doCheckUsername(String emUs) {
        // Simulazione di una lookup veloce nel DB
        try {
            TimeUnit.NANOSECONDS.sleep(MOCK_DB_LATENCY_NS / 3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return false; // Simula che la ricerca si basi su email
    }

    // Metodo principale chiamato dopo i check iniziali
    public Customer doCheckLogin(String emUs, String password) {
        // Simulazione del costo di I/O + recupero dell'oggetto Customer completo
        try {
            TimeUnit.NANOSECONDS.sleep(MOCK_DB_LATENCY_NS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simula la creazione dell'oggetto Customer (incluso Cart e CreditCard)
        Customer customer = new Customer();
        customer.setUsername(emUs);
        customer.setPassword(password);
        customer.setCart(new Cart());

        return customer;
    }
}