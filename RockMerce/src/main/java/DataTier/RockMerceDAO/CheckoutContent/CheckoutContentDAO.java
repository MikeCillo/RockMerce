package DataTier.RockMerceDAO.CheckoutContent;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.Guitar;

import java.sql.*;
import java.util.ArrayList;

public class CheckoutContentDAO {

    public ArrayList<Guitar> retrieveCheckoutContent(int checkoutId) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT idGuitar,name,quantity,price,producer,category,color FROM CheckoutContent WHERE idCheckout=?");

            ps.setInt(1,checkoutId);
            ResultSet rs = ps.executeQuery();
            ArrayList<Guitar> guitars= new ArrayList<>();
            while (rs.next()) {
                Guitar guitar=new Guitar();
                guitar.setId(rs.getInt(1));
                guitar.setName(rs.getString(2));
                guitar.setDisponibility(rs.getInt(3));
                guitar.setPrice(rs.getDouble(4));
                guitar.setProducer(rs.getString(5));
                guitar.setCategory(rs.getString(6));
                guitar.setColor(rs.getString(7));
                guitars.add(guitar);
            }
            return guitars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addToCheckoutContent(int idCheckout,Guitar guitar) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO CheckoutContent (name,quantity,price,producer,category,color,idCheckout) VALUES(?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);


            ps.setString(1,guitar.getName());
            ps.setInt(2,guitar.getDisponibility());
            ps.setDouble(3,guitar.getPrice());
            ps.setString(4,guitar.getProducer());
            ps.setString(5,guitar.getCategory());
            ps.setString(6,guitar.getColor());
            ps.setInt(7,idCheckout);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("GUITAR CANNOT BE ADDED TO CHECKOUT CONTENT");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}


