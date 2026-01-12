package LogicTier.GestioneCart;

import LogicTier.Entità.Cart;
import LogicTier.Entità.Customer;
import LogicTier.Entità.Guitar;

import java.sql.SQLException;


public interface CartInterface {

    Cart retrieveCustomerCart(Customer customer);

    Cart removeGuitarFromCart(Customer customer,int id) throws SQLException;

    Cart freeCart(Customer customer) throws SQLException;

    void addGuitarToCart(Customer customer,Guitar guitar) throws SQLException;


}
