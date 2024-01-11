package DataTier.RockMerceDAO.CartContent;


import DataTier.DBCONNECTION.DbConnection;
import DataTier.RockMerceDAO.Cart.CartDAO;
import DataTier.RockMerceDAO.Guitar.GuitarDAO;
import LogicTier.Entità.Cart;
import LogicTier.Entità.Guitar;

import java.sql.*;
import java.util.ArrayList;

public class CartContentDAO {

    public void insertIntoCartContent(int cartId, Guitar guitar) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO CartContent (cart,name,quantity,price,producer,category,color) VALUES(?,?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);


            ps.setInt(1,cartId);
            ps.setString(2, guitar.getName());
            ps.setInt(3, guitar.getDisponibility());
            ps.setDouble(4, guitar.getPrice());
            ps.setString(5, guitar.getProducer());
            ps.setString(6, guitar.getCategory());
            ps.setString(7,guitar.getColor());

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
                    con.prepareStatement("SELECT id,name,quantity,price,producer,category,color FROM CartContent WHERE cart=?");

            ps.setInt(1, cartId);

            ResultSet rs = ps.executeQuery();

            ArrayList<Guitar> guitars= new ArrayList<>();
            GuitarDAO guitarDAO=new GuitarDAO();          //db guitar's methods
            CartDAO cartDAO=new CartDAO();
            Cart cart=cartDAO.getCartFromDB(cartId);
            while (rs.next()) {
                Guitar guitar=new Guitar();
                guitar.setId(rs.getInt(1));
                guitar.setName(rs.getString(2));
                guitar.setDisponibility(rs.getInt(3));
                guitar.setPrice(rs.getDouble(4));
                guitar.setProducer(rs.getString(5));
                guitar.setCategory(rs.getString(6));
                guitar.setColor(rs.getString(7));
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

