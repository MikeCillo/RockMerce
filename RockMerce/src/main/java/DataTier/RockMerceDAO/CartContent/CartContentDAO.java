package DataTier.RockMerceDAO.CartContent;
import LogicTier.Entità.Cart;
import DataTier.RockMerceDAO.Cart.CartDAO;
import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.Guitar;
import DataTier.RockMerceDAO.Guitar.GuitarDAO;
import LogicTier.exception.CartContentException;

import java.sql.*;
import java.util.ArrayList;

public class CartContentDAO {
    public void insertIntoCartContent(final int cartId, final Guitar guitar) { // Aggiunto 'final'


        final String selectSql = "SELECT quantity, price FROM CartContent WHERE cart=? AND id=?";
        final String updateSql = "UPDATE CartContent SET quantity=?, price=? WHERE cart=? AND id=?";
        final String insertSql = "INSERT INTO CartContent (cart, id, quantity, price) VALUES (?, ?, ?, ?)";

        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement psSelect = con.prepareStatement(selectSql)) {

            psSelect.setInt(1, cartId);
            psSelect.setInt(2, guitar.getId());

            try (final ResultSet rs = psSelect.executeQuery()) {

                if (rs.next()) {
                    final int newQuantity = 1 + rs.getInt(1);
                    final double newPrice = guitar.getPrice() + rs.getDouble(2);

                    try (final PreparedStatement psUpdate = con.prepareStatement(updateSql)) {
                        psUpdate.setInt(1, newQuantity);
                        psUpdate.setDouble(2, newPrice);
                        psUpdate.setInt(3, cartId);
                        psUpdate.setInt(4, guitar.getId());
                        psUpdate.executeUpdate();
                    }

                } else {

                    try (final PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                        psInsert.setInt(1, cartId);
                        psInsert.setInt(2, guitar.getId());
                        psInsert.setInt(3, 1);
                        psInsert.setDouble(4, guitar.getPrice());
                        psInsert.executeUpdate();
                    }
                }
            }

        } catch (final SQLException e) {
            throw new CartContentException("Database error during insert/update into CartContent.");
        }
    }




    public ArrayList<Guitar> getCartContent(final int cartId) {

        final String selectSql = "SELECT id, price, quantity FROM CartContent WHERE cart=?";

        final ArrayList<Guitar> guitars = new ArrayList<>();
        final GuitarDAO guitarDAO = new GuitarDAO();
        final CartDAO cartDAO = new CartDAO();
        final Cart cart = cartDAO.getCartFromDB(cartId);

        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(selectSql)) {

            ps.setInt(1, cartId);

            try (final ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    final int guitarId = rs.getInt(1);
                    final double unitPrice = rs.getDouble(2);
                    final int quantity = rs.getInt(3);


                    final Guitar guitar = guitarDAO.doRetrieveGuitarById(guitarId);

                    guitar.setPrice(unitPrice);
                    guitar.setDisponibility(quantity);


                    if (guitarDAO.checkGuitar(guitar)) {
                        guitars.add(guitar);
                    } else {
                        removeGuitarFromCartContent(cartId, guitar.getId());

                        cart.setTempTotal(cart.getTempTotal() - unitPrice);
                        cart.setNumGuitars(cart.getNumGuitars() - quantity);

                        cartDAO.upDateCart(cart);
                    }
                }
            }
            return guitars;
        } catch (final SQLException e) {
            throw new CartContentException("Database error retrieving cart content for ID: " + cartId);
        }
    }


    public void removeGuitarFromCartContent(final int cartId, final int guitarId) {
        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(
                     "DELETE FROM CartContent " +
                             "WHERE cart =? and id=?")) {

            ps.setInt(1, cartId);
            ps.setInt(2, guitarId);


            if (ps.executeUpdate() != 1) {

                throw new CartContentException("GUITAR (ID: " + guitarId + ") CANNOT BE REMOVED FROM CART CONTENT (Cart ID: " + cartId + ") - 0 rows affected.");
            }

        } catch (final SQLException e) {
            throw new CartContentException("Database error during removal of guitar " + guitarId + " from cart " + cartId);
        }
    }


    public void removeGuitarsFromCartContent(final ArrayList<Guitar> guitars, final int cartId) {

        final String deleteSql = "DELETE FROM CartContent WHERE cart =? AND id=?";

        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(deleteSql)) {

            ps.setInt(1, cartId);
            for (final Guitar guitar : guitars) {

                ps.setInt(2, guitar.getId());

                if (ps.executeUpdate() != 1) {
                    throw new CartContentException("GUITAR (ID: " + guitar.getId() + ") CANNOT BE REMOVED FROM CART CONTENT (Cart ID: " + cartId + ") - 0 rows affected.");
                }
            }

        } catch (final SQLException e) { // Aggiunto 'final'
            throw new CartContentException("Database error during batch removal of guitars from cart " + cartId);
        }
    }
}





