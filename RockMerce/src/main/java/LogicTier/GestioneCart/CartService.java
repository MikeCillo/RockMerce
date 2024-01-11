package LogicTier.GestioneCart;

import DataTier.RockMerceDAO.Cart.CartDAO;
import DataTier.RockMerceDAO.CartContent.CartContentDAO;
import LogicTier.Entità.Cart;
import LogicTier.Entità.Customer;
import LogicTier.Entità.Guitar;

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

    @Override
    public Cart removeGuitarFromCart(Customer customer,int id) {
        CartDAO cartDAO = new CartDAO();
        Cart cart = cartDAO.getCartFromDB(customer.getCart().getId());
        CartContentDAO cartContentDAO = new CartContentDAO();

        if (cart.getNumGuitars() >= 1) {
            ArrayList<Guitar> guitars = cartContentDAO.getCartContent(cart.getId());
            cart.setGuitars(guitars);
            Guitar guitar = cart.removeGuitar(id);
            cartDAO.upDateCart(cart);

            cartContentDAO.removeGuitarFromCartContent(cart.getId(), guitar.getId());
        }
        return cart;
    }

    @Override
    public Cart freeCart(Customer customer) {
        CartDAO cartDAO = new CartDAO();
        Cart cart = cartDAO.getCartFromDB(customer.getCart().getId());


        CartContentDAO cartContentDAO = new CartContentDAO();
        ArrayList<Guitar> guitarsCartContent = cartContentDAO.getCartContent(cart.getId());

        if (cart.getNumGuitars() >= 1) {
            cartContentDAO.removeGuitarsFromCartContent(guitarsCartContent, cart.getId());
            cart.getGuitars().clear();
            cart.setTempTotal(0);
            cart.setNumGuitars(0);
            cartDAO.upDateCart(cart);
        }
        return cart;
    }


    @Override
    public void addGuitarToCart(Customer customer,Guitar guitar){
        CartDAO cartDAO=new CartDAO();
        Cart cart= cartDAO.getCartFromDB(customer.getCart().getId());
        cart.addGuitar(guitar);
        cartDAO.upDateCart(cart);

        CartContentDAO cartContentDAO=new CartContentDAO();
        cartContentDAO.insertIntoCartContent(customer.getCart().getId(),guitar);
    }

}
