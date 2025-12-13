package DataTier.RockMerceDAO.Customer;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.Customer;
import LogicTier.Entità.CreditCard;
import LogicTier.Entità.Cart;
import LogicTier.exception.CustomerException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    @Test
    void doCustomerSave_shouldThrowWhenZeroRowsOrSqlError() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(0);

        injectDataSource(ds);

        Customer customer = new Customer();
        customer.setUsername("u");
        customer.setEmail("e");
        customer.setName("n");
        customer.setSurname("s");
        customer.setPassword("pw");
        customer.setPhone("ph");
        customer.setCountry("ct");
        customer.setCity("ci");
        customer.setAddress("ad");
        CreditCard cc = new CreditCard(); cc.setId(1); customer.setCreditCard(cc);
        Cart cart = new Cart(); cart.setId(2); customer.setCart(cart);

        CustomerDAO dao = new CustomerDAO();
        assertThrows(CustomerException.class, () -> dao.doCustomerSave(customer));

        // SQLException wrap
        DataSource ds2 = mock(DataSource.class);
        when(ds2.getConnection()).thenThrow(new SQLException("boom"));
        injectDataSource(ds2);
        assertThrows(CustomerException.class, () -> dao.doCustomerSave(customer));
    }

    @Test
    void doCheckEmail_shouldWrapSqlException() throws Exception {
        DataSource ds = mock(DataSource.class);
        when(ds.getConnection()).thenThrow(new SQLException("boom"));
        injectDataSource(ds);
        CustomerDAO dao = new CustomerDAO();
        assertThrows(CustomerException.class, () -> dao.doCheckEmail("a@b.com"));
    }

    @Test
    void doCheckUsername_shouldWrapSqlException() throws Exception {
        DataSource ds = mock(DataSource.class);
        when(ds.getConnection()).thenThrow(new SQLException("boom"));
        injectDataSource(ds);
        CustomerDAO dao = new CustomerDAO();
        assertThrows(CustomerException.class, () -> dao.doCheckUsername("userx"));
    }

    @Test
    void doCheckLogin_shouldWrapSqlException() throws Exception {
        DataSource ds = mock(DataSource.class);
        when(ds.getConnection()).thenThrow(new SQLException("boom"));
        injectDataSource(ds);
        CustomerDAO dao = new CustomerDAO();
        assertThrows(CustomerException.class, () -> dao.doCheckLogin("e","pw"));
    }

    @Test
    void getCustomerByCart_shouldWrapSqlException() throws Exception {
        DataSource ds = mock(DataSource.class);
        when(ds.getConnection()).thenThrow(new SQLException("boom"));
        injectDataSource(ds);
        CustomerDAO dao = new CustomerDAO();
        assertThrows(CustomerException.class, () -> dao.getCustomerByCart(3));
    }
}
