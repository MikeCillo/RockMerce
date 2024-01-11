package LogicTier.GestioneCart;

import LogicTier.Entità.Cart;
import LogicTier.Entità.Customer;
import LogicTier.Entità.Guitar;


public interface CartInterface {

    Cart retrieveCustomerCart(Customer customer);

    Cart removeGuitarFromCart(Customer customer,int id);

    Cart freeCart(Customer customer);

    void addGuitarToCart(Customer customer,Guitar guitar);


}
