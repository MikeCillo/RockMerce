package DataTier.RockMerceDAO.CreditCard;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.CreditCard;

import java.sql.*;

public class CreditCardDAO {

    public int doCreditCardSave(CreditCard card) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO CreditCard (cardNumber, owner, expireDate, cvv) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, card.getNumber());
            ps.setString(2, card.getOwner());
            ps.setString(3, card.getDate());
            ps.setInt(4, card.getCvv());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            card.setId(id);
            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public CreditCard retrieveCreditCardById(int id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM CreditCard WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CreditCard card=new CreditCard();
                card.setId(Integer.parseInt(rs.getString(1)));
                card.setNumber(rs.getString(2));
                card.setOwner(rs.getString(3));
                card.setDate(rs.getString(4));
                card.setCvv(Integer.parseInt(rs.getString(5)));
                return card;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
