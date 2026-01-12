package DataTier.RockMerceDAO.CreditCard;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.CreditCard;
import LogicTier.exception.CrediCardException;

import java.sql.*;

public class CreditCardDAO {

    public int doCreditCardSave(final CreditCard card) {
        final String insertSql = "INSERT INTO CreditCard (cardNumber, owner, expireDate, cvv) VALUES(?,?,?,?)";

        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, card.getCardNumber());
            ps.setString(2, card.getOwner());
            ps.setString(3, card.getExpireDate());
            ps.setInt(4, card.getCvv());

            if (ps.executeUpdate() != 1) {
                throw new CrediCardException("CreditCard INSERT error: Zero rows affected.");
            }

            try (final ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    final int id = rs.getInt(1);
                    card.setId(id);
                    return id;
                } else {
                    throw new CrediCardException("CreditCard INSERT error: Database did not return the generated key.");
                }
            }

        } catch (final SQLException e) {
            throw new CrediCardException("Database error during credit card save.");
        }

    }


    public CreditCard retrieveCreditCardById(final int id) {
        final String selectSql = "SELECT id, cardNumber, owner, expireDate, cvv FROM CreditCard WHERE id=?";

        try (final Connection con = DbConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement(selectSql)) {

            ps.setInt(1, id);

            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    final CreditCard card = new CreditCard();
                    card.setId(rs.getInt(1));
                    card.setCardNumber(rs.getString(2));
                    card.setOwner(rs.getString(3));
                    card.setExpireDate(rs.getString(4));
                    card.setCvv(rs.getInt(5));
                    return card;
                }
                return null;
            }

        } catch (final SQLException e) {
            throw new CrediCardException("Database error retrieving credit card by ID: " + id);
        }
    }

}