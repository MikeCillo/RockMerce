package DataTier.RockMerceDAO.Checkout;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.Checkout;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CheckoutDAOTest {

    private DataSource originalDs;

    @BeforeEach
     void setUp() throws Exception {
        Field dsField = DbConnection.class.getDeclaredField("datasource");
        dsField.setAccessible(true);
        originalDs = (DataSource) dsField.get(null);
    }

    @AfterEach
     void tearDown() throws Exception {
        Field dsField = DbConnection.class.getDeclaredField("datasource");
        dsField.setAccessible(true);
        dsField.set(null, originalDs);
    }

    private void injectDataSource(DataSource ds) throws Exception {
        Field dsField = DbConnection.class.getDeclaredField("datasource");
        dsField.setAccessible(true);
        dsField.set(null, ds);
    }

    @Test
   void newCheckout_shouldReturnId_whenInsertSucceeds() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString(), org.mockito.ArgumentMatchers.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);
        when(ps.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(55);

        injectDataSource(ds);
        CheckoutDAO dao = new CheckoutDAO();
        int id = dao.newCheckout(1,"s","o");
        assertEquals(55,id);
    }

    @Test
     void updateCheckout_shouldThrowWhenZeroAffected() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(0);

        injectDataSource(ds);
        CheckoutDAO dao = new CheckoutDAO();
        assertThrows(RuntimeException.class, () -> dao.updateCheckout(100.0, 2));
    }

    @Test
    void retrieveCustomersCheckouts_and_retrieveOrders_and_retrieveEarnings() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // simulate one row
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(1)).thenReturn(8);
        when(rs.getDouble(2)).thenReturn(33.3);
        when(rs.getString(3)).thenReturn("s");
        when(rs.getString(4)).thenReturn("o");
        when(rs.getInt(5)).thenReturn(3);

        injectDataSource(ds);
        CheckoutDAO dao = new CheckoutDAO();

        ArrayList<Checkout> byCart = dao.retrieveCustomersCheckouts(1);
        assertNotNull(byCart);

        ArrayList<Checkout> orders = dao.retrieveOrders();
        assertNotNull(orders);

        Double earnings = dao.retrieveEarnings();
        assertNotNull(earnings);
    }
}

