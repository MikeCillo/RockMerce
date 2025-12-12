package DataTier.RockMerceDAO.Checkout;


import DataTier.DBCONNECTION.DbConnection;
import DataTier.RockMerceDAO.CheckoutContent.CheckoutContentDAO;
import DataTier.RockMerceDAO.Customer.CustomerDAO;
import LogicTier.Entità.Checkout;

import java.sql.*;
import java.util.ArrayList;

public class CheckoutDAO {

    public int newCheckout(final int cartId, final String sendDate, final String orderDate) {

        final String insertSql = "INSERT INTO Checkout (total,sendDate,orderDate,cartId) VALUES(?,?,?,?)";

        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, 0.00);
            ps.setString(2, sendDate);
            ps.setString(3, orderDate);
            ps.setInt(4, cartId);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("FAILED CHECKOUT CREATION: Zero rows affected by insert.");
            }

            // Incluso ResultSet nel try-with-resources
            try (final ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new RuntimeException("FAILED CHECKOUT CREATION: Database did not return the generated key.");
                }
            }

        } catch (final SQLException e) {
            throw new RuntimeException("Database error during new checkout creation for Cart ID: " + cartId, e);
        }
    }


    public void updateCheckout(final double total, final int checkoutId) {

        final String updateSql = "UPDATE Checkout SET total=? WHERE id=?";

        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(updateSql)) {

            ps.setDouble(1, total);
            ps.setInt(2, checkoutId);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Checkout update failed for ID: " + checkoutId + ". 0 rows affected.");
            }

        } catch (final SQLException e) {
            throw new RuntimeException("Database error during checkout update for ID: " + checkoutId, e);
        }
    }


    public ArrayList<Checkout> retrieveCustomersCheckouts(final int cartId) {

        final String selectSql = "SELECT id,total,sendDate,orderDate FROM Checkout WHERE cartId=?";

        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(selectSql)) {

            ps.setInt(1, cartId);

            try (final ResultSet rs = ps.executeQuery()) {

                final ArrayList<Checkout> checkouts = new ArrayList<>();
                final CheckoutContentDAO checkoutContentDAO = new CheckoutContentDAO();

                while (rs.next()) {
                    final Checkout checkout = new Checkout();
                    checkout.setId(rs.getInt(1));
                    checkout.setTotalPrice(rs.getDouble(2));
                    checkout.setSendDate(rs.getString(3));
                    checkout.setOrderDate(rs.getString(4));
                    checkout.setGuitars(checkoutContentDAO.retrieveCheckoutContent(checkout.getId()));

                    checkouts.add(checkout);
                }
                return checkouts;

            }

        } catch (final SQLException e) {
            throw new RuntimeException("Database error retrieving checkouts for Cart ID: " + cartId, e);
        }
    }


    public ArrayList<Checkout> retrieveOrders() {

        final String selectSql = "SELECT id,total,sendDate,orderDate,cartId FROM Checkout";

        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(selectSql)) {

            try (final ResultSet rs = ps.executeQuery()) {

                final ArrayList<Checkout> checkouts = new ArrayList<>();
                final CheckoutContentDAO checkoutContentDAO = new CheckoutContentDAO();
                final CustomerDAO customerDAO = new CustomerDAO();

                while (rs.next()) {
                    final Checkout checkout = new Checkout();

                    checkout.setId(rs.getInt(1));
                    checkout.setTotalPrice(rs.getDouble(2));
                    checkout.setSendDate(rs.getString(3));
                    checkout.setOrderDate(rs.getString(4));
                    checkout.setCartId(rs.getInt(5));


                    checkout.setCustomer(customerDAO.getCustomerByCart(checkout.getCartId()));
                    checkout.setGuitars(checkoutContentDAO.retrieveCheckoutContent(checkout.getId()));

                    checkouts.add(checkout);
                }
                return checkouts;

            }

        } catch (final SQLException e) {
            throw new RuntimeException("Database error retrieving all orders.", e);
        }
    }


    public Double retrieveEarnings() {

        final String selectSql = "SELECT total FROM Checkout";


        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(selectSql)) {


            try (final ResultSet rs = ps.executeQuery()) {

                double earnings = 0.00; // La variabile locale deve rimanere non final perché viene riassegnata (earnings += ...)

                while (rs.next()) {
                    earnings += rs.getDouble(1);
                }
                return earnings;

            }

        } catch (final SQLException e) {
            throw new RuntimeException("Database error retrieving total earnings.", e);
        }
    }

}
