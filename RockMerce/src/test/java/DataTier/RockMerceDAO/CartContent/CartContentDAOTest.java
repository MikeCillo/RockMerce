package DataTier.RockMerceDAO.CartContent;

import DataTier.DBCONNECTION.DbConnection;
import DataTier.RockMerceDAO.Guitar.GuitarDAO;
import LogicTier.Entità.Guitar;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CartContentDAOTest {

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
     void insertIntoCartContent_shouldUpdate_whenRowExists_elseInsert() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement psSelect = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        PreparedStatement psUpdate = mock(PreparedStatement.class);
        PreparedStatement psInsert = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(contains("SELECT quantity"))).thenReturn(psSelect);
        when(psSelect.executeQuery()).thenReturn(rs);

        // case: exists -> update
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(2);
        when(rs.getDouble(2)).thenReturn(10.0);
        when(con.prepareStatement(contains("UPDATE CartContent"))).thenReturn(psUpdate);
        when(psUpdate.executeUpdate()).thenReturn(1);

        injectDataSource(ds);
        CartContentDAO dao = new CartContentDAO();
        Guitar g = new Guitar(); g.setId(3); g.setPrice(5.0);
        dao.insertIntoCartContent(1, g);

        verify(psUpdate, times(1)).executeUpdate();

        reset(rs);
        when(psSelect.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);
        when(con.prepareStatement(contains("INSERT INTO CartContent"))).thenReturn(psInsert);
        when(psInsert.executeUpdate()).thenReturn(1);

        dao.insertIntoCartContent(1, g);

        verify(psInsert, times(1)).executeUpdate();
    }

    @Test
     void getCartContent_shouldReturnFilteredList_andRemoveUnavailable() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(1)).thenReturn(4);
        when(rs.getDouble(2)).thenReturn(20.0);
        when(rs.getInt(3)).thenReturn(1);

        GuitarDAO guitarDao = mock(GuitarDAO.class);
        Guitar g = new Guitar(); g.setId(4); g.setPrice(20.0); g.setDisponibility(1);
        when(guitarDao.doRetrieveGuitarById(4)).thenReturn(g);
        when(guitarDao.checkGuitar(any())).thenReturn(true);


        CartContentDAO dao = spy(new CartContentDAO());

        injectDataSource(ds);

        ArrayList<Guitar> list = dao.getCartContent(1);
        assertNotNull(list);
    }

    @Test
     void removeGuitarFromCartContent_shouldThrowWhenNoRowsAffected() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(contains("DELETE FROM CartContent"))).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(0);

        injectDataSource(ds);
        CartContentDAO dao = new CartContentDAO();
        assertThrows(RuntimeException.class, () -> dao.removeGuitarFromCartContent(1, 2));
    }

    @Test
     void removeGuitarsFromCartContent_shouldRemoveAllOrThrow() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        injectDataSource(ds);
        CartContentDAO dao = new CartContentDAO();
        ArrayList<Guitar> guitars = new ArrayList<>();
        Guitar g = new Guitar(); g.setId(9);
        guitars.add(g);

        // should not throw
        dao.removeGuitarsFromCartContent(guitars, 1);

        // simulate failure
        when(ps.executeUpdate()).thenReturn(0);
        assertThrows(RuntimeException.class, () -> dao.removeGuitarsFromCartContent(guitars, 1));
    }
}
