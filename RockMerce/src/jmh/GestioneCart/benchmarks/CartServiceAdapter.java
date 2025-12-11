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
    public Cart retrieveCustomerCart(Customer customer){
        int cartId=customer.getCart().getId();

        Cart cart = this.mockCartDAO.getCartFromDB(cartId);

        cart.setGuitars(this.mockCartContentDAO.getCartContent(cart.getId()));
        return cart;
    }

    @Override
    public Cart removeGuitarFromCart(Customer customer,int id) {

        Cart cart = this.mockCartDAO.getCartFromDB(customer.getCart().getId());

        if (cart.getNumGuitars() >= 1) {
            ArrayList<Guitar> guitars = this.mockCartContentDAO.getCartContent(cart.getId());
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
    public Cart freeCart(Customer customer) {

        Cart cart = this.mockCartDAO.getCartFromDB(customer.getCart().getId());
        ArrayList<Guitar> guitarsCartContent = this.mockCartContentDAO.getCartContent(cart.getId());

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
    public void addGuitarToCart(Customer customer,Guitar guitar){
        Cart cart = this.mockCartDAO.getCartFromDB(customer.getCart().getId());
        cart.addGuitar(guitar);
        this.mockCartDAO.upDateCart(cart);
        this.mockCartContentDAO.insertIntoCartContent(customer.getCart().getId(),guitar);
    }

}