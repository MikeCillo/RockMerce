package LogicTier.GestioneCart;

import DataTier.RockMerceDAO.Cart.CartDAO;
import DataTier.RockMerceDAO.CartContent.CartContentDAO;
import DataTier.RockMerceDAO.Checkout.CheckoutDAO;
import DataTier.RockMerceDAO.CheckoutContent.CheckoutContentDAO;
import DataTier.RockMerceDAO.Guitar.GuitarDAO;
import LogicTier.Entità.Cart;
import LogicTier.Entità.Checkout;
import LogicTier.Entità.Customer;
import LogicTier.Entità.Guitar;

import java.util.ArrayList;

public class CheckOutService implements CheckOutInterface{
    @Override
    public Checkout confirmCheckOut(Customer customer) {
        //RETRIEVE CUSTOMER'S CART
        CartDAO cartDAO = new CartDAO();
        Cart cart = cartDAO.getCartFromDB(customer.getCart().getId());


        //RETRIEVE CUSTOMER'S CART CONTENT
        CartContentDAO cartContentDAO = new CartContentDAO();
        ArrayList<Guitar> guitars = cartContentDAO.getCartContent(cart.getId());


        //CHECK IF THERE'S AT LEAST A PRODUCT IN THE CART
        if (cart.getNumGuitars() >= 1 && cart.getTempTotal() > 0) {


            //CREATE A NEW CHECKOUT
            Checkout checkout = new Checkout();
            checkout.setOrderDate();            //set order date
            checkout.setSendDate();             //set send date


            //CREATE A NEW CHECKOUT IN DB
            CheckoutDAO checkoutDAO = new CheckoutDAO();                  //CHECKOUT DB METHODS
            int idCheckout = checkoutDAO.newCheckout(customer.getCart().getId(), checkout.getSendDate(), checkout.getOrderDate());


            checkout.setIdCheckout(idCheckout);

            GuitarDAO guitarDAO = new GuitarDAO();
            CheckoutContentDAO checkoutContentDAO = new CheckoutContentDAO();


            //FOR EACH GUITAR FROM CART CONTENT
            for (Guitar guitar : guitars) {


                //DECREMENT GUITAR'S DISPONIBILITY FROM STOCK
                Guitar dbGuitar = guitarDAO.findGuitar(guitar);
                guitarDAO.decrementGuitar(dbGuitar);


                //REMOVE GUITAR FROM CART CONTENT
                cartContentDAO.removeGuitarFromCartContent(cart.getId(), guitar.getId());


                //ADD THE GUITAR TO CHECKOUT CONTENT
                checkoutContentDAO.addToCheckoutContent(idCheckout, guitar);

            }


            //SET CHECKOUT FINAL PRICE
            checkout.setTotalPrice(cart.getTempTotal());
            checkoutDAO.updateCheckout(checkout.getTotalPrice(), checkout.getIdCheckout());


            // FREE CART
            cart.setTempTotal(0.00);
            cart.setNumGuitars(0);
            cartDAO.upDateCart(cart);


            //PASS CHECKOUT
            return checkout;

        }
        return null;
    }

    @Override
    public ArrayList<Checkout> customersCheckouts(Customer customer) {
        //GETTING CUSTOMER'S CHECKOUTS
        CheckoutDAO checkoutDAO = new CheckoutDAO();
        return checkoutDAO.retrieveCustomersCheckouts(customer.getCart().getId());
    }


    @Override
    public ArrayList<Checkout> AdminOrders() {
        CheckoutDAO checkoutDAO=new CheckoutDAO();
        return  checkoutDAO.retrieveOrders();
    }

    @Override
    public double AdminEarnings(){
        CheckoutDAO checkoutDAO=new CheckoutDAO();
        return  checkoutDAO.retrieveEarnings();
    }
}
