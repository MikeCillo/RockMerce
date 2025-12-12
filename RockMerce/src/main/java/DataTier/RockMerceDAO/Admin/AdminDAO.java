package DataTier.RockMerceDAO.Admin;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {


    public Admin checkAdminLogin(final String emUs, final String password) { // Aggiunto 'final' ai parametri

        // Esteso try-with-resources per includere Connection e PreparedStatement
        try (final Connection con = DbConnection.getConnection(); // Aggiunto 'final' a con
             final PreparedStatement ps = con.prepareStatement( // Aggiunto 'final' a ps e incluso nel try-with-resources
                     "SELECT * FROM Admin where password=? and (email=? or username=?)")) {

            ps.setString(1, password);
            ps.setString(2, emUs);
            ps.setString(3, emUs);

            // Incluso anche il ResultSet nel try-with-resources per chiusura automatica
            try (final ResultSet rs = ps.executeQuery()) { // Aggiunto 'final' a rs

                if (!rs.next()) {
                    return null;
                }
                // return extractAdminFromResultSet(rs);
                return new Admin(); // Placeholder
            }
        } catch (final SQLException e) { // Aggiunto 'final' all'eccezione
            return null;
        }
    }

}
