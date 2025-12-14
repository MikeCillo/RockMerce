package GestioneCart.benchmarks;

import DataTier.RockMerceDAO.Cart.CartDAO;
import LogicTier.Entità.Cart;

// Estende il DAO originale e sovrascrive i metodi DB
public class MockCartDAO extends CartDAO {

    // Simula il recupero di un carrello dal DB
    @Override
    public Cart getCartFromDB(final int idCart) {
        // Restituisce un oggetto Cart fittizio e subito pronto
       final Cart cart = new Cart();
        cart.setId(idCart);
        // Imposta la dimensione per rendere i test di rimozione realistici
        cart.setNumGuitars(CartState.CART_SIZE);
        cart.setTempTotal(CartState.CART_SIZE * 100.0);
        return cart;
    }

    // Simula l'aggiornamento del carrello nel DB (non fa nulla)
    @Override
    public void upDateCart(final Cart cart, final java.sql.Connection con) {
        // Non fa nulla, è un mock
    }

    // Simula la creazione del carrello
    @Override
    public int createCart() {
        return 1;
    }
}