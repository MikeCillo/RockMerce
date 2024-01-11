package LogicTier.GestioneCart;

import LogicTier.Entità.Checkout;
import LogicTier.Entità.Customer;

import java.util.ArrayList;

public interface CheckOutInterface {
    Checkout confirmCheckOut(Customer customer);
    ArrayList<Checkout> customersCheckouts(Customer customer);
    ArrayList<Checkout> AdminOrders();
    double AdminEarnings();

}
