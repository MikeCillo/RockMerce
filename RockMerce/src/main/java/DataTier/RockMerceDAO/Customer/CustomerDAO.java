package DataTier.RockMerceDAO.Customer;

import DataTier.DBCONNECTION.DbConnection;
import DataTier.RockMerceDAO.Cart.CartDAO;
import DataTier.RockMerceDAO.CreditCard.CreditCardDAO;
import LogicTier.Entità.Cart;
import LogicTier.Entità.CreditCard;
import LogicTier.Entità.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
   public void doCustomerSave(Customer customer) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Customer (username, email, name, surname, password, phone , country, city ,address,cardId,cartId) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
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
                throw new RuntimeException("NEW CUSTOMER REGISTRATION FAILED");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doCheckEmail(String email) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Customer where  email=? ");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean doCheckUsername(String username) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Customer where  username=? ");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public Customer doCheckLogin(String emUs, String password) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Customer where password=? and (email=? or username=?)");
            ps.setString(1,password);
            ps.setString(2,emUs);
            ps.setString(3,emUs);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }
            else {
                Customer customer =new Customer();
                customer.setUsername(rs.getString("username"));
                customer.setEmail(rs.getString("email"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setPassword(rs.getString("password"));
                customer.setPhone(rs.getString("phone"));
                customer.setCountry(rs.getString("country"));
                customer.setCity(rs.getString("city"));
                customer.setAddress(rs.getString("address"));

                CreditCardDAO creditCardDAO=new CreditCardDAO();
                CreditCard creditCard=creditCardDAO.retrieveCreditCardById(rs.getInt("cardId"));
                customer.setCreditCard(creditCard);

                CartDAO cartDAO=new CartDAO();
                Cart cart=cartDAO.getCartFromDB(rs.getInt("cartId"));
                customer.setCart(cart);
                return customer;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    public Customer getCustomerByCart(int idCart) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Customer where cartId=?");
            ps.setInt(1,idCart);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }
            else {
                Customer customer =new Customer();
                customer.setUsername(rs.getString("username"));
                customer.setEmail(rs.getString("email"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setPassword(rs.getString("password"));
                customer.setPhone(rs.getString("phone"));
                customer.setCountry(rs.getString("country"));
                customer.setCity(rs.getString("city"));
                customer.setAddress(rs.getString("address"));

                CreditCardDAO creditCardDAO=new CreditCardDAO();
                CreditCard creditCard=creditCardDAO.retrieveCreditCardById(rs.getInt("cardId"));
                customer.setCreditCard(creditCard);

                CartDAO cartDAO=new CartDAO();
                Cart cart=cartDAO.getCartFromDB(rs.getInt("cartId"));
                customer.setCart(cart);
                return customer;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

