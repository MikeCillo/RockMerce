package DataTier.RockMerceDAO.Customer;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.Customer;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CustomerDAOTest {

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
    void doCheckEmail_shouldReturnTrueWhenNotExistingFalseWhenExists() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(false);
        injectDataSource(ds);

        CustomerDAO dao = new CustomerDAO();
        assertTrue(dao.doCheckEmail("a@b.com"));

        reset(rs);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        assertFalse(dao.doCheckEmail("a@b.com"));
    }

    @Test
    void doCheckUsername_shouldReturnTrueWhenNotExistingFalseWhenExists() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(false);
        injectDataSource(ds);

        CustomerDAO dao = new CustomerDAO();
        assertTrue(dao.doCheckUsername("userx"));

        reset(rs);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        assertFalse(dao.doCheckUsername("userx"));
    }

    @Test
    void doCheckLogin_and_getCustomerByCart_shouldReturnCustomerOrNull() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // login not found
        when(rs.next()).thenReturn(false);
        injectDataSource(ds);

        CustomerDAO dao = new CustomerDAO();
        assertNull(dao.doCheckLogin("x","p"));

        // login found -> need to stub ResultSet getters for fields
        reset(rs);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getString("username")).thenReturn("u");
        when(rs.getString("email")).thenReturn("e");
        when(rs.getString("name")).thenReturn("n");
        when(rs.getString("surname")).thenReturn("s");
        when(rs.getString("password")).thenReturn("pw");
        when(rs.getString("phone")).thenReturn("ph");
        when(rs.getString("country")).thenReturn("ct");
        when(rs.getString("city")).thenReturn("ci");
        when(rs.getString("address")).thenReturn("ad");
        when(rs.getInt("cardId")).thenReturn(0);
        when(rs.getInt("cartId")).thenReturn(0);

        Customer c = dao.doCheckLogin("e","pw");
        assertNotNull(c);

        // getCustomerByCart: not found
        reset(rs);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);
        Customer c2 = dao.getCustomerByCart(5);
        assertNull(c2);
    }
}

