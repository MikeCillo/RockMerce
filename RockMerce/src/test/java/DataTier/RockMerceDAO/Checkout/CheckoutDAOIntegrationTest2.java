package DataTier.RockMerceDAO.Checkout;

import DataTier.TestDbUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.Assert.*;

public class CheckoutDAOIntegrationTest2 {

    @Before
    public void setup() throws Exception {
        TestDbUtil.initH2();
    }

    @Test
    public void retrieveEarnings_sumMultipleCheckouts() throws Exception {
        try (Connection con = DataTier.DBCONNECTION.DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Checkout (total,sendDate,orderDate,cartId) values (?,?,?,?)")) {
            ps.setDouble(1, 10.0); ps.setString(2,"s"); ps.setString(3,"o"); ps.setInt(4,1); ps.executeUpdate();
        }
        try (Connection con = DataTier.DBCONNECTION.DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Checkout (total,sendDate,orderDate,cartId) values (?,?,?,?)")) {
            ps.setDouble(1, 20.0); ps.setString(2,"s"); ps.setString(3,"o"); ps.setInt(4,1); ps.executeUpdate();
        }

        CheckoutDAO dao = new CheckoutDAO();
        Double earnings = dao.retrieveEarnings();
        assertEquals(30.0, earnings, 0.001);
    }
}

