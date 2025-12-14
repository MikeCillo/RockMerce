package LogicTier.GestioneCart;

import DataTier.DBCONNECTION.DbConnection;
import DataTier.RockMerceDAO.Cart.CartDAO;
import DataTier.RockMerceDAO.CartContent.CartContentDAO;
import LogicTier.Entità.Cart;
import LogicTier.Entità.Customer;
import LogicTier.Entità.Guitar;
import LogicTier.exception.CartContentException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartService implements CartInterface{

    @Override
    public Cart retrieveCustomerCart(Customer customer){
        int cartId=customer.getCart().getId();                  //RETRIEVE ID CART FROM CUSTOMER
        CartDAO cartDAO=new CartDAO();                         //CART DB METHODS
        Cart cart=cartDAO.getCartFromDB(cartId);              //CART FROM DB BY ITS ID


        CartContentDAO cartContentDAO=new CartContentDAO();               //CART CONTENT DB METHODS
        cart.setGuitars(cartContentDAO.getCartContent(cart.getId()));    //RETRIEVE CUSTOMER CART'S GUITAR FROM DB

        return cart;
    }

// CartService.java

    @Override
// Aggiungiamo throws SQLException per gestire i fallimenti della transazione
    public Cart removeGuitarFromCart(Customer customer,int id) throws SQLException {

        Connection con = null;
        Cart cart = null;

        try {
            // --- 1. INIZIO DELLA TRANSAZIONE ---
            con = DbConnection.getConnection();
            con.setAutoCommit(false); // Disattiva l'Auto-Commit

            CartDAO cartDAO = new CartDAO();
            CartContentDAO cartContentDAO = new CartContentDAO();

            // 2. Lettura del Carrello (Usando la Connessione Transazionale)
            // Nota: Si assume che getCartFromDB sia stato aggiornato per accettare 'con'
            cart = cartDAO.getCartFromDB(customer.getCart().getId());

            // Lettura del contenuto, anche questa dovrebbe idealmente usare 'con'
            ArrayList<Guitar> guitars = cartContentDAO.getCartContent(cart.getId());
            cart.setGuitars(guitars);

            if (cart.getNumGuitars() >= 1) {

                Guitar guitar = cart.removeGuitar(id); // Logica in memoria

                // 3. Prima Scrittura: Aggiornamento Carrello (Passando la Connessione)
                // Nota: upDateCart deve essere aggiornato per accettare 'con'
                cartDAO.upDateCart(cart, con);

                // 4. Seconda Scrittura: Rimozione Contenuto (Passando la Connessione)
                // Nota: removeGuitarFromCartContent deve essere aggiornato per accettare 'con'
                cartContentDAO.removeGuitarFromCartContent(cart.getId(), guitar.getId());
            }

            // --- 5. FINE DELLA TRANSAZIONE: SUCCESS ---
            con.commit();

        } catch (final SQLException e) {
            // --- 6. FINE DELLA TRANSAZIONE: FALLIMENTO ---
            if (con != null) {
                try {
                    con.rollback(); // Annulla tutte le operazioni
                } catch (final SQLException ex) {
                    // Gestione errore rollback
                }
            }
            // Rilancia l'eccezione, il chiamante dovrà gestire l'errore 500
            throw e;

        } finally {
            // --- 7. PULIZIA ---
            if (con != null) {
                try {
                    con.setAutoCommit(true); // Ripristina l'auto-commit
                    con.close(); // Chiude la singola connessione
                } catch (final SQLException e) {
                    // Log: Errore nella chiusura della connessione
                }
            }
        }
        return cart;
    }

// CartService.java

    @Override
    public Cart freeCart(Customer customer) throws SQLException {

        Connection con = null;
        Cart cart = null;

        try {
            // --- 1. INIZIO DELLA TRANSAZIONE ---
            con = DbConnection.getConnection();
            con.setAutoCommit(false); // Disattiva l'Auto-Commit

            CartDAO cartDAO = new CartDAO();
            CartContentDAO cartContentDAO = new CartContentDAO();

            // 2. Lettura (Usando la Connessione Transazionale)
            // Nota: Si assume che getCartFromDB sia stato aggiornato per accettare 'con'
            cart = cartDAO.getCartFromDB(customer.getCart().getId());

            // Lettura del contenuto, anche questa dovrebbe idealmente usare 'con'
            ArrayList<Guitar> guitarsCartContent = cartContentDAO.getCartContent(cart.getId());

            if (cart.getNumGuitars() >= 1) {

                // 3. Prima Scrittura: Rimozione del Contenuto (Passando la Connessione)
                // Nota: removeGuitarsFromCartContent deve essere aggiornato per accettare 'con'
                cartContentDAO.removeGuitarsFromCartContent(guitarsCartContent, cart.getId());

                // Aggiornamento dell'oggetto in memoria
                cart.getGuitars().clear();
                cart.setTempTotal(0);
                cart.setNumGuitars(0);

                // 4. Seconda Scrittura: Aggiornamento Carrello (Passando la Connessione)
                // Nota: upDateCart deve essere aggiornato per accettare 'con'
                cartDAO.upDateCart(cart, con);
            }

            // --- 5. FINE DELLA TRANSAZIONE: SUCCESS ---
            con.commit();

        } catch (final SQLException e) {
            // --- 6. FINE DELLA TRANSAZIONE: FALLIMENTO ---
            if (con != null) {
                try {
                    con.rollback(); // Annulla tutte le operazioni
                } catch (final SQLException ex) {
                    // Gestione errore rollback
                }
            }
            // Rilancia l'eccezione, il chiamante dovrà gestire l'errore 500
            throw e;

        } finally {
            // --- 7. PULIZIA ---
            if (con != null) {
                try {
                    con.setAutoCommit(true); // Ripristina l'auto-commit
                    con.close(); // Chiude la singola connessione
                } catch (final SQLException e) {
                    // Log: Errore nella chiusura della connessione
                }
            }
        }
        return cart; // Ritorna l'oggetto Carrello aggiornato
    }

    // CartService.java

    @Override
// Assicurati che il metodo del Service lanci CartContentException (o SQLException)
    public void addGuitarToCart(Customer customer,Guitar guitar) throws CartContentException, SQLException {

        Connection con = null;

        try {
            // --- 1. INIZIO DELLA TRANSAZIONE ---
            con = DbConnection.getConnection();
            con.setAutoCommit(false); // Disattiva l'Auto-Commit

            // Inizializza i DAO all'interno del try, come prima
            CartDAO cartDAO = new CartDAO();
            CartContentDAO cartContentDAO = new CartContentDAO();

            // ERRORE CORRETTO 1: getCartFromDB DEVE USARE LA CONNESSIONE
            // Devi avere un metodo in CartDAO.java con questa firma:
            // public Cart getCartFromDB(int cartId, Connection con)
            Cart cart = cartDAO.getCartFromDB(customer.getCart().getId());

            // --- 2. OPERAZIONI DI SCRITTURA (Atomiche) ---
            // Prima operazione: Aggiornamento Carrello (Cart)
            cart.addGuitar(guitar);
            // ERRORE CORRETTO 2: upDateCart DEVE USARE LA CONNESSIONE
            // Devi avere un metodo in CartDAO.java con questa firma:
            // public void upDateCart(Cart cart, Connection con)
            cartDAO.upDateCart(cart, con);

            // Seconda operazione: Inserimento Contenuto Carrello (CartContent)
            // insertIntoCartContent DEVE USARE LA CONNESSIONE
            // (Hai già modificato questo metodo, quindi la firma è corretta)
            cartContentDAO.insertIntoCartContent(customer.getCart().getId(), guitar, con);

            // --- 3. FINE DELLA TRANSAZIONE: SUCCESS ---
            con.commit();

        } catch (final SQLException e) {
            // ... (Gestione Rollback) ...
            if (con != null) {
                try {
                    con.rollback();
                } catch (final SQLException ex) {
                    // Log: Fallimento del Rollback
                }
            }
            // Rilancia l'eccezione come errore di dominio
            throw new CartContentException("Database error during addGuitarToCart operation: " + e.getMessage());

        } finally {
            // ... (Pulizia) ...
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (final SQLException e) {
                    // Log: Errore nella chiusura della connessione
                }
            }
        }
    }

}
