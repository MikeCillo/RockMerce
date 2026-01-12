package LogicTier.GestioneCart;

import LogicTier.Entità.Checkout;
import LogicTier.Entità.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CheckOutInterface {
    Checkout confirmCheckOut(Customer customer) throws SQLException;
    ArrayList<Checkout> customersCheckouts(Customer customer);
    ArrayList<Checkout> adminOrders();
    double adminEarnings();

}
