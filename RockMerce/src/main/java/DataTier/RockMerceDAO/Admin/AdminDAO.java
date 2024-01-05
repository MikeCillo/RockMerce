package DataTier.RockMerceDAO.Admin;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {


    public Admin checkAdminLogin(String emUs, String password) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Admin where password=? and (email=? or username=?)");
            ps.setString(1,password);
            ps.setString(2,emUs);
            ps.setString(3,emUs);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }
            else {
                Admin admin=new Admin();
                admin.setEmail(rs.getString("email"));
                admin.setUsername(rs.getString("username"));
                admin.setName(rs.getString("name"));
                admin.setSurname(rs.getString("surname"));
                admin.setPassword(rs.getString("password"));
                return admin;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
