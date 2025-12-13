package DataTier.RockMerceDAO.Cart;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.Cart;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

 class CartDAOTest {

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

   void injectDataSource(DataSource ds) throws Exception {
        Field dsField = DbConnection.class.getDeclaredField("datasource");
        dsField.setAccessible(true);
        dsField.set(null, ds);
    }

    @Test
   void createCart_shouldReturnGeneratedId_whenInsertSucceeds() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rsKeys = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString(), org.mockito.ArgumentMatchers.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);
        when(ps.getGeneratedKeys()).thenReturn(rsKeys);
        when(rsKeys.next()).thenReturn(true);
        when(rsKeys.getInt(1)).thenReturn(42);

        injectDataSource(ds);

        CartDAO dao = new CartDAO();
        int id = dao.createCart();
        assertEquals(42, id);
    }

    @Test
    void createCart_shouldThrowRuntime_whenExecuteUpdateNotOne() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString(), org.mockito.ArgumentMatchers.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(0);

        injectDataSource(ds);

        CartDAO dao = new CartDAO();
        assertThrows(RuntimeException.class, dao::createCart);
    }

    @Test
     void getCartFromDB_shouldReturnCart_whenFound_andNullWhenNotFound() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // found
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(5);
        when(rs.getDouble(2)).thenReturn(12.5);
        when(rs.getInt(3)).thenReturn(2);

        injectDataSource(ds);

        CartDAO dao = new CartDAO();
        Cart c = dao.getCartFromDB(5);
        assertNotNull(c);
        assertEquals(5, c.getId());
        assertEquals(12.5, c.getTempTotal());
        assertEquals(2, c.getNumGuitars());

        // not found
        reset(rs);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);
        Cart c2 = dao.getCartFromDB(99);
        assertNull(c2);
    }

    @Test
     void upDateCart_shouldExecuteUpdate() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        injectDataSource(ds);

        CartDAO dao = new CartDAO();
        Cart c = new Cart();
        c.setId(7);
        c.setTempTotal(3.3);
        c.setNumGuitars(1);

        // should not throw
        dao.upDateCart(c);
        verify(ps, atLeastOnce()).executeUpdate();
    }
}

