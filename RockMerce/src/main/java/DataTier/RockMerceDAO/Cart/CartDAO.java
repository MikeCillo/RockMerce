package DataTier.RockMerceDAO.Cart;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.Cart;

import java.sql.*;

public class CartDAO {


    public int createCart() {
        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO Cart (tempTotal,numGuitars) VALUES(?,?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, 0.00);
            ps.setInt(2, 0);

            if (ps.executeUpdate() != 1) {
                throw new Exception("CART CREATION FAILED: Zero or multiple rows affected by insert.");
            }

            // Recupera la chiave generata
            try (final ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Ritorna l'ID del carrello
                } else {
                    throw new Exception("CART CREATION FAILED: Database did not return the generated key.");
                }
            }

        } catch (final Exception e) { 
            throw new RuntimeException("Database error during cart creation.", e);
        }
    }


    public void upDateCart(Cart cart){

        try (Connection con = DbConnection.getConnection()) {
            Statement st = con.createStatement();
            String query = "update Cart set tempTotal='" + cart.getTempTotal() + "', numGuitars=" + cart.getNumGuitars()+ " where id=" + cart.getId() + ";";
            st.executeUpdate(query);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Cart getCartFromDB(int idCart) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,tempTotal,numGuitars FROM Cart WHERE id=?");
            ps.setInt(1, idCart);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cart cart=new Cart();
                cart.setId(rs.getInt(1));
                cart.setTempTotal(rs.getDouble(2));
                cart.setNumGuitars(rs.getInt(3));
                return cart;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
