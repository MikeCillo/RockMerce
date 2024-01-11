package DataTier.RockMerceDAO.Checkout;


import DataTier.DBCONNECTION.DbConnection;
import DataTier.RockMerceDAO.CheckoutContent.CheckoutContentDAO;
import DataTier.RockMerceDAO.Customer.CustomerDAO;
import LogicTier.Entità.Checkout;

import java.sql.*;
import java.util.ArrayList;

public class CheckoutDAO {

    public int newCheckout(int cartId,String sendDate,String orderDate){
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Checkout (total,sendDate,orderDate,cartId) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, 0.00);
            ps.setString(2, sendDate);
            ps.setString(3, orderDate);
            ps.setInt(4, cartId);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("FAILED CHECKOUT CREATION");
            }

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void updateCheckout(double total,int checkoutId){

        try (Connection con = DbConnection.getConnection()) {
            Statement st = con.createStatement();
            String query = "update Checkout set  total=" +total + " where id=" + checkoutId + ";";
            st.executeUpdate(query);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Checkout> retrieveCustomersCheckouts(int cartId) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,total,sendDate,orderDate FROM Checkout WHERE cartId=?");

            ps.setInt(1,cartId);

            ResultSet rs = ps.executeQuery();
            ArrayList<Checkout> checkouts=new ArrayList<>();

            CheckoutContentDAO checkoutContentDAO=new CheckoutContentDAO();
            while (rs.next()) {
                Checkout checkout=new Checkout();
                checkout.setIdCheckout(rs.getInt(1));
                checkout.setTotalPrice(rs.getDouble(2));
                checkout.setSendDate(rs.getString(3));
                checkout.setOrderDate(rs.getString(4));
                checkout.setGuitars(checkoutContentDAO.retrieveCheckoutContent(checkout.getIdCheckout()));
                checkouts.add(checkout);
            }
            return checkouts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Checkout> retrieveOrders() {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,total,sendDate,orderDate,cartId FROM Checkout");


            ResultSet rs = ps.executeQuery();
            ArrayList<Checkout> checkouts=new ArrayList<>();
            CheckoutContentDAO checkoutContentDAO=new CheckoutContentDAO();

            CustomerDAO customerDAO =new CustomerDAO();
            while (rs.next()) {
                Checkout checkout=new Checkout();
                checkout.setIdCheckout(rs.getInt(1));
                checkout.setTotalPrice(rs.getDouble(2));
                checkout.setSendDate(rs.getString(3));
                checkout.setOrderDate(rs.getString(4));
                checkout.setCartId(rs.getInt(5));
                checkout.setCustomer(customerDAO.getCustomerByCart(checkout.getCartId()));
                checkout.setGuitars(checkoutContentDAO.retrieveCheckoutContent(checkout.getIdCheckout()));
                checkouts.add(checkout);
            }
            return checkouts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Double retrieveEarnings() {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT total FROM Checkout ");


            ResultSet rs = ps.executeQuery();

            double earnings=0.00;
            while (rs.next()) {
              earnings+=rs.getDouble(1);
            }
            return earnings;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
