package DataTier.RockMerceDAO.Customer;

import DataTier.DBCONNECTION.DbConnection;
import DataTier.RockMerceDAO.Cart.CartDAO;
import DataTier.RockMerceDAO.CreditCard.CreditCardDAO;
import LogicTier.Entità.Cart;
import LogicTier.Entità.CreditCard;
import LogicTier.Entità.Customer;
import LogicTier.exception.CustomerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {


    public void doCustomerSave(final Customer customer) {
        final String insertSql = "INSERT INTO Customer (username, email, name, surname, password, phone , country, city ,address,cardId,cartId) VALUES(?,?,?,?,?,?,?,?,?,?,?)";


        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(insertSql)) {

            ps.setString(1, customer.getUsername());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getName());
            ps.setString(4, customer.getSurname());
            ps.setString(5, customer.getPassword());
            ps.setString(6, customer.getPhone());
            ps.setString(7, customer.getCountry());
            ps.setString(8, customer.getCity());
            ps.setString(9, customer.getAddress());
            ps.setInt(10, customer.getCreditCard().getId());
            ps.setInt(11, customer.getCart().getId());

            if (ps.executeUpdate() != 1) {
                throw new CustomerException("NEW CUSTOMER REGISTRATION FAILED: Zero rows affected by insert.");
            }

        } catch (final SQLException e) {
            throw new CustomerException("Database error during new customer registration for username: " + customer.getUsername());
        }
    }



    public boolean doCheckEmail(final String email) {
        final String selectSql = "SELECT username, email, name, surname, password, phone, country, city, address, cardId, cartId FROM Customer WHERE email = ?";


        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(selectSql)) {

            ps.setString(1, email);


            try (final ResultSet rs = ps.executeQuery()) {

                return !rs.next();

            }

        } catch (final SQLException e) {
            throw new CustomerException("Database error during email check for: " + email);
        }
    }







    public boolean doCheckUsername(final String username) {
        final String selectSql = "SELECT username, email, name, surname, password, phone, country, city, address, cardId, cartId FROM Customer WHERE username = ?";

        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(selectSql)) {

            ps.setString(1, username);

            try (final ResultSet rs = ps.executeQuery()) {
                return !rs.next();

            }

        } catch (final SQLException e) {
            throw new CustomerException("Database error during username check for: " + username);
        }
    }




    public Customer doCheckLogin(String emUs, String password) {


        try (final Connection con = DbConnection.getConnection();
            final  PreparedStatement ps = con.prepareStatement(
                     "SELECT username, email, name, surname, password, phone, country, city, address, cardId, cartId FROM Customer where password=? and (email=? or username=?)")) {

            ps.setString(1, password);
            ps.setString(2, emUs);
            ps.setString(3, emUs);
            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    return null;
                } else {
                    Customer customer = new Customer();
                    customer.setUsername(rs.getString("username"));
                    customer.setEmail(rs.getString("email"));
                    customer.setName(rs.getString("name"));
                    customer.setSurname(rs.getString("surname"));
                    customer.setPassword(rs.getString("password"));
                    customer.setPhone(rs.getString("phone"));
                    customer.setCountry(rs.getString("country"));
                    customer.setCity(rs.getString("city"));
                    customer.setAddress(rs.getString("address"));

                    CreditCardDAO creditCardDAO = new CreditCardDAO();
                    CreditCard creditCard = creditCardDAO.retrieveCreditCardById(rs.getInt("cardId"));
                    customer.setCreditCard(creditCard);

                    CartDAO cartDAO = new CartDAO();
                    Cart cart = cartDAO.getCartFromDB(rs.getInt("cartId"));
                    customer.setCart(cart);
                    return customer;
                }
            } // rs chiuso qui

        } catch (SQLException e) {
            // ps e con chiusi qui
            throw new CustomerException("Database error during customer login for: " + emUs);
        }
    }




    public Customer getCustomerByCart(int idCart) {

        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(
                     "SELECT username, email, name, surname, password, phone, country, city, address, cardId, cartId FROM Customer where cartId=?")) {

            ps.setInt(1, idCart);

            try (final ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    return null;
                }
                else {
                    Customer customer = new Customer();
                    customer.setUsername(rs.getString("username"));
                    customer.setEmail(rs.getString("email"));
                    customer.setName(rs.getString("name"));
                    customer.setSurname(rs.getString("surname"));
                    customer.setPassword(rs.getString("password"));
                    customer.setPhone(rs.getString("phone"));
                    customer.setCountry(rs.getString("country"));
                    customer.setCity(rs.getString("city"));
                    customer.setAddress(rs.getString("address"));

                    CreditCardDAO creditCardDAO = new CreditCardDAO();
                    CreditCard creditCard = creditCardDAO.retrieveCreditCardById(rs.getInt("cardId"));
                    customer.setCreditCard(creditCard);

                    CartDAO cartDAO = new CartDAO();
                    Cart cart = cartDAO.getCartFromDB(rs.getInt("cartId"));
                    customer.setCart(cart);
                    return customer;
                }
            } // rs chiuso qui

        } catch (SQLException e) {
            throw new CustomerException("Database error retrieving customer for Cart ID: " + idCart);
        }
    }

}


