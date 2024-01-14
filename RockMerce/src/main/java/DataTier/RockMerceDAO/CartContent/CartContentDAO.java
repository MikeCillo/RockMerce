package DataTier.RockMerceDAO.CartContent;
import LogicTier.Entità.Cart;
import DataTier.RockMerceDAO.Cart.CartDAO;
import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.Guitar;
import DataTier.RockMerceDAO.Guitar.GuitarDAO;
import java.sql.*;
import java.util.ArrayList;

public class CartContentDAO {
    public void insertIntoCartContent(int cartId, Guitar guitar) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM CartContent WHERE cart=? and id=?");

            ps.setInt(1, cartId);
            ps.setInt(2,guitar.getId());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                guitar.setDisponibility(guitar.getDisponibility()+rs.getInt(3));
                guitar.setPrice(guitar.getPrice()+rs.getInt(4));

                ps = con.prepareStatement ("update CartContent set price=?,quantity=? where id=? and cart=?");
                guitar.setDisponibility(guitar.getDisponibility());
                ps.setDouble(1, guitar.getPrice());
                ps.setInt(2,guitar.getDisponibility());
                ps.setInt(3,guitar.getId());
                ps.setInt(4,cartId);
            }

            else {
                ps = con.prepareStatement(
                        "INSERT INTO CartContent(id,cart,quantity,price) VALUES(?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);

                ps.setInt(1,guitar.getId());
                ps.setInt(2,cartId);
                ps.setInt(3, guitar.getDisponibility());
                ps.setDouble(4, guitar.getPrice());
            }


            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("THE GUITAR CANNOT BE ADDED TO CART");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public ArrayList<Guitar> getCartContent(int cartId) {


        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM CartContent WHERE cart=?");

            ps.setInt(1, cartId);

            ResultSet rs = ps.executeQuery();

            ArrayList<Guitar> guitars= new ArrayList<>();
            GuitarDAO guitarDAO=new GuitarDAO();
            CartDAO cartDAO=new CartDAO();
            Cart cart=cartDAO.getCartFromDB(cartId);
            while (rs.next()) {
                Guitar guitar=guitarDAO.doRetrieveGuitarById(rs.getInt(1));
                guitar.setPrice(rs.getDouble(4));
                guitar.setDisponibility(rs.getInt(3));

                if(guitarDAO.checkGuitar(guitar)) {
                    guitars.add(guitar);
                }
                else if (!guitarDAO.checkGuitar(guitar)){
                    removeGuitarFromCartContent(cartId,guitar.getId());
                    cart.setTempTotal(cart.getTempTotal()- guitar.getPrice());
                    cart.setNumGuitars(cart.getNumGuitars()-guitar.getDisponibility());
                    cartDAO.upDateCart(cart);
                }


            }
            return guitars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void removeGuitarFromCartContent(int cartId,int guitarId) {
        try (Connection con = DbConnection.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM CartContent " +
                            "WHERE cart =? and id=?");

            ps.setInt(1, cartId);
            ps.setInt(2, guitarId);


            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("GUITAR CANNOT BE REMOVED FROM CART CONTENT");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void removeGuitarsFromCartContent(ArrayList<Guitar> guitars,int cartId) {
        for(Guitar guitar:guitars)
            try (Connection con = DbConnection.getConnection()) {

                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM CartContent " +
                                "WHERE cart =? and id=?");

                ps.setInt(1, cartId);
                ps.setInt(2, guitar.getId());


                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("GUITARS CANNOT BE REMOVED FROM CART CONTENT");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}





