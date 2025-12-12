package GestioneCart.benchmarks;

import DataTier.RockMerceDAO.Cart.CartDAO;
import DataTier.RockMerceDAO.CartContent.CartContentDAO;
import LogicTier.Entità.Cart;
import LogicTier.Entità.Customer;
import LogicTier.Entità.Guitar;
import LogicTier.GestioneCart.CartService;
import java.util.ArrayList;


public class CartServiceAdapter extends CartService {


    private final CartDAO mockCartDAO = new MockCartDAO();
    private final CartContentDAO mockCartContentDAO = new MockCartContentDAO();




    @Override
    public Cart retrieveCustomerCart(final Customer customer){
        final int cartId=customer.getCart().getId();

        final Cart cart = this.mockCartDAO.getCartFromDB(cartId);

        cart.setGuitars(this.mockCartContentDAO.getCartContent(cart.getId()));
        return cart;
    }

    @Override
    public Cart removeGuitarFromCart(final Customer customer,final int id) {

       final Cart cart = this.mockCartDAO.getCartFromDB(customer.getCart().getId());

        if (cart.getNumGuitars() >= 1) {
            final ArrayList<Guitar> guitars = this.mockCartContentDAO.getCartContent(cart.getId());
            cart.setGuitars(guitars);
            Guitar guitar = cart.removeGuitar(id);
            // aggiorna comunque il carrello
            this.mockCartDAO.upDateCart(cart);

            // se la rimozione ha trovato una chitarra, sincronizza il contenuto del DB mock
            if (guitar != null) {
                this.mockCartContentDAO.removeGuitarFromCartContent(cart.getId(), guitar.getId());
            }
        }
        return cart;
    }

    @Override
    public Cart freeCart(final Customer customer) {

        final Cart cart = this.mockCartDAO.getCartFromDB(customer.getCart().getId());
        final ArrayList<Guitar> guitarsCartContent = this.mockCartContentDAO.getCartContent(cart.getId());

        if (cart.getNumGuitars() >= 1) {
            this.mockCartContentDAO.removeGuitarsFromCartContent(guitarsCartContent, cart.getId());
            cart.getGuitars().clear();
            cart.setTempTotal(0);
            cart.setNumGuitars(0);
            this.mockCartDAO.upDateCart(cart);
        }
        return cart;
    }


    @Override
    public void addGuitarToCart(final Customer customer,final Guitar guitar){
        final Cart cart = this.mockCartDAO.getCartFromDB(customer.getCart().getId());
        cart.addGuitar(guitar);
        this.mockCartDAO.upDateCart(cart);
        this.mockCartContentDAO.insertIntoCartContent(customer.getCart().getId(),guitar);
    }

}