package DataTier;

import DataTier.DBCONNECTION.DbConnection;
import DataTier.RockMerceDAO.Cart.CartDAO;
import DataTier.RockMerceDAO.CartContent.CartContentDAO;
import DataTier.RockMerceDAO.Customer.CustomerDAO;
import LogicTier.Entità.Customer;
import LogicTier.Entità.CreditCard;
import LogicTier.Entità.Cart;
import LogicTier.Entità.Guitar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.tomcat.jdbc.pool.DataSource;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MockDaoFailureTest {

    private DataSource originalDs;

    @Before
    public void setUp() throws Exception {
        // preserve original datasource
        Field dsField = DbConnection.class.getDeclaredField("datasource");
        dsField.setAccessible(true);
        originalDs = (DataSource) dsField.get(null);
    }

    @After
    public void tearDown() throws Exception {
        // restore original datasource
        Field dsField = DbConnection.class.getDeclaredField("datasource");
        dsField.setAccessible(true);
        dsField.set(null, originalDs);
    }

    @Test
    public void cartCreate_shouldThrowRuntime_whenExecuteUpdateReturnsZero() throws Exception {
        // mock datasource, connection and prepared statement
        DataSource ds = mock(DataSource.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(0);

        // inject mock
        Field dsField = DbConnection.class.getDeclaredField("datasource");
        dsField.setAccessible(true);
        dsField.set(null, ds);

        try {
            new CartDAO().createCart();
            throw new AssertionError("Expected RuntimeException");
        } catch (RuntimeException e) {
            // expected
        }

        // verify interactions
        verify(ps, atLeastOnce()).executeUpdate();
    }

    @Test
    public void removeGuitarFromCartContent_shouldThrowRuntime_whenExecuteUpdateReturnsZero() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(0);

        Field dsField = DbConnection.class.getDeclaredField("datasource");
        dsField.setAccessible(true);
        dsField.set(null, ds);

        try {
            new CartContentDAO().removeGuitarFromCartContent(1, 1);
            throw new AssertionError("Expected RuntimeException");
        } catch (RuntimeException e) {
            // expected
        }

        verify(ps, atLeastOnce()).executeUpdate();
    }

    @Test
    public void removeGuitarsFromCartContent_shouldThrowRuntime_whenAnyExecuteUpdateReturnsZero() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        // first call returns 0 to simulate failure on first guitar
        when(ps.executeUpdate()).thenReturn(0);

        Field dsField = DbConnection.class.getDeclaredField("datasource");
        dsField.setAccessible(true);
        dsField.set(null, ds);

        ArrayList<Guitar> guitars = new ArrayList<>();
        Guitar g = new Guitar(); g.setId(1);
        guitars.add(g);

        try {
            new CartContentDAO().removeGuitarsFromCartContent(guitars, 1);
            throw new AssertionError("Expected RuntimeException");
        } catch (RuntimeException e) {
            // expected
        }

        verify(ps, atLeastOnce()).executeUpdate();
    }

    @Test
    public void doCustomerSave_shouldThrowRuntime_whenExecuteUpdateReturnsZero() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(0);

        Field dsField = DbConnection.class.getDeclaredField("datasource");
        dsField.setAccessible(true);
        dsField.set(null, ds);

        CustomerDAO dao = new CustomerDAO();
        Customer customer = new Customer();
        customer.setUsername("u");
        customer.setEmail("e");
        customer.setName("n");
        customer.setSurname("s");
        customer.setPassword("p");
        customer.setPhone("ph");
        customer.setCountry("c");
        customer.setCity("ci");
        customer.setAddress("ad");
        CreditCard card = new CreditCard(); card.setId(1);
        Cart cart = new Cart(); cart.setId(1);
        customer.setCreditCard(card);
        customer.setCart(cart);

        try {
            dao.doCustomerSave(customer);
            throw new AssertionError("Expected RuntimeException");
        } catch (RuntimeException e) {
            // expected
        }

        verify(ps, atLeastOnce()).executeUpdate();
    }

}

