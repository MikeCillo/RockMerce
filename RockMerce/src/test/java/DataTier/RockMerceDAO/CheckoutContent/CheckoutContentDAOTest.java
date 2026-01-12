package DataTier.RockMerceDAO.CheckoutContent;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.Guitar;
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

 class CheckoutContentDAOTest {

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
     void retrieveCheckoutContent_shouldReturnList() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(1)).thenReturn(3);
        when(rs.getString(2)).thenReturn("nm");
        when(rs.getInt(3)).thenReturn(2);
        when(rs.getDouble(4)).thenReturn(44.4);
        when(rs.getString(5)).thenReturn("p");
        when(rs.getString(6)).thenReturn("c");
        when(rs.getString(7)).thenReturn("col");

        injectDataSource(ds);
        CheckoutContentDAO dao = new CheckoutContentDAO();
        ArrayList<Guitar> list = dao.retrieveCheckoutContent(1);
        assertNotNull(list);
        assertEquals(1, list.size());
    }

    @Test
    void addToCheckoutContent_shouldThrowOnBadInsert() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString(), org.mockito.ArgumentMatchers.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(0);

        injectDataSource(ds);
        CheckoutContentDAO dao = new CheckoutContentDAO();
        Guitar g = new Guitar(); g.setName("a"); g.setPrice(2.2); g.setDisponibility(1);
        assertThrows(RuntimeException.class, () -> dao.addToCheckoutContent(1, g));
    }
}

