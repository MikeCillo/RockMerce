package DataTier.RockMerceDAO.Guitar;

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

class GuitarDAOTest {

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

    // Helper to inject mocks
    private void injectDataSource(DataSource ds) throws Exception {
        Field dsField = DbConnection.class.getDeclaredField("datasource");
        dsField.setAccessible(true);
        dsField.set(null, ds);
    }

    @Test
    void doRetrieveGuitars_shouldReturnList_whenResultSetHasRows() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(1)).thenReturn(1);
        when(rs.getString(2)).thenReturn("name");
        when(rs.getDouble(3)).thenReturn(10.0);
        when(rs.getString(4)).thenReturn("producer");
        when(rs.getString(5)).thenReturn("category");
        when(rs.getInt(6)).thenReturn(5);
        when(rs.getString(7)).thenReturn("sound");
        when(rs.getString(8)).thenReturn("image");
        when(rs.getString(9)).thenReturn("desc");
        when(rs.getString(10)).thenReturn("yes");
        when(rs.getString(11)).thenReturn("color");

        injectDataSource(ds);

        GuitarDAO dao = new GuitarDAO();
        ArrayList<Guitar> list = dao.doRetrieveGuitars();
        assertNotNull(list);
        assertEquals(1, list.size());
        Guitar g = list.get(0);
        assertEquals(1, g.getId());
        assertEquals("name", g.getName());
    }

    @Test
    void doRetrieveGuitarsByCategory_shouldReturnEmpty_whenNoRows() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(false);

        injectDataSource(ds);

        GuitarDAO dao = new GuitarDAO();
        ArrayList<Guitar> list = dao.doRetrieveGuitarsByCategory("cat");
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    void doRetrieveGuitarById_shouldReturnGuitar_whenFound_andNullWhenNotFound() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // first call: found
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(1)).thenReturn(2);
        when(rs.getString(2)).thenReturn("g2");
        when(rs.getFloat(3)).thenReturn(20f);
        when(rs.getString(4)).thenReturn("p");
        when(rs.getString(5)).thenReturn("c");
        when(rs.getInt(6)).thenReturn(3);
        when(rs.getString(7)).thenReturn("s");
        when(rs.getString(8)).thenReturn("i");
        when(rs.getString(9)).thenReturn("d");
        when(rs.getString(10)).thenReturn("yes");
        when(rs.getString(11)).thenReturn("col");

        injectDataSource(ds);

        GuitarDAO dao = new GuitarDAO();
        Guitar g = dao.doRetrieveGuitarById(2);
        assertNotNull(g);
        assertEquals(2, g.getId());

        // simulate not found: rs.next() false
        reset(rs);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);
        Guitar g2 = dao.doRetrieveGuitarById(99);
        assertNull(g2);
    }

    @Test
    void decrementGuitar_shouldHandleDifferentDisponibilityBranches_andThrowOnBadUpdate() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement psUpdate = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(con);
        // prepareStatement called multiple times; return same mock
        when(con.prepareStatement(anyString())).thenReturn(psUpdate);

        // setup executeUpdate to return 1 (success)
        when(psUpdate.executeUpdate()).thenReturn(1);

        injectDataSource(ds);

        GuitarDAO dao = new GuitarDAO();
        Guitar g2 = new Guitar(); g2.setId(1); g2.setDisponibility(2);
        dao.decrementGuitar(g2); // disponibility >=2

        Guitar g1 = new Guitar(); g1.setId(1); g1.setDisponibility(1);
        dao.decrementGuitar(g1); // disponibility ==1

        Guitar g0 = new Guitar(); g0.setId(1); g0.setDisponibility(0);
        dao.decrementGuitar(g0); // disponibility ==0

        // now simulate failure: executeUpdate returns 0 -> RuntimeException
        when(psUpdate.executeUpdate()).thenReturn(0);
        Guitar bf = new Guitar(); bf.setId(1); bf.setDisponibility(3);
        try {
            dao.decrementGuitar(bf);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            // expected
        }
    }

    @Test
    void checkGuitar_shouldReturnTrueFalse() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        Guitar g = new Guitar(); g.setName("n"); g.setProducer("p"); g.setCategory("c");

        // available
        when(rs.next()).thenReturn(true);
        when(rs.getInt(6)).thenReturn(2);
        injectDataSource(ds);
        GuitarDAO dao = new GuitarDAO();
        assertTrue(dao.checkGuitar(g));

        // not available
        reset(rs);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt(6)).thenReturn(0);
        assertFalse(dao.checkGuitar(g));

        // no row
        reset(rs);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);
        assertFalse(dao.checkGuitar(g));
    }

    @Test
    void findGuitar_shouldReturnGuitarOrNull() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        Guitar in = new Guitar(); in.setName("n"); in.setProducer("p"); in.setCategory("c"); in.setColor("col");

        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(7);
        when(rs.getString(2)).thenReturn("gname");
        when(rs.getFloat(3)).thenReturn(33f);
        when(rs.getString(4)).thenReturn("prod");
        when(rs.getString(5)).thenReturn("cat");
        when(rs.getInt(6)).thenReturn(4);
        when(rs.getString(7)).thenReturn("s");
        when(rs.getString(8)).thenReturn("i");
        when(rs.getString(9)).thenReturn("d");
        when(rs.getString(10)).thenReturn("yes");
        when(rs.getString(11)).thenReturn("colorx");

        injectDataSource(ds);
        GuitarDAO dao = new GuitarDAO();
        Guitar out = dao.findGuitar(in);
        assertNotNull(out);
        assertEquals(7, out.getId());

        // not found
        reset(rs);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);
        Guitar out2 = dao.findGuitar(in);
        assertNull(out2);
    }

    @Test
    void deleteUpdateInsert_doUpdateAndInsertReturnBooleanOrThrowOnBadUpdate() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString(), org.mockito.ArgumentMatchers.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
        when(con.prepareStatement(anyString())).thenReturn(ps);

        when(ps.executeUpdate()).thenReturn(1);

        injectDataSource(ds);
        GuitarDAO dao = new GuitarDAO();

        Guitar g = new Guitar();
        g.setId(10);
        g.setName("n");
        g.setPrice(5.5);
        g.setProducer("p");
        g.setCategory("c");
        g.setDisponibility(2);
        g.setSound("s");
        g.setDescription("d");
        g.setVisibility("yes");
        g.setColor("col");

        // update should not throw
        dao.doUpdateGuitar(g);

        // delete should not throw
        dao.deleteGuitar(g);

        // insert returns true
        boolean inserted = dao.doInsertNewGuitar(g);
        assertTrue(inserted);

        // simulate bad update -> throw
        when(ps.executeUpdate()).thenReturn(0);
        try {
            dao.doUpdateGuitar(g);
            fail("Expected RuntimeException on bad update");
        } catch (RuntimeException e) {
            // expected
        }

        try {
            dao.deleteGuitar(g);
            fail("Expected RuntimeException on bad delete");
        } catch (RuntimeException e) {
            // expected
        }

        try {
            dao.doInsertNewGuitar(g);
            fail("Expected RuntimeException on bad insert");
        } catch (RuntimeException e) {
            // expected
        }
    }

    @Test
    void admindoRetrieveGuitars_shouldReturnList() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(1)).thenReturn(4);
        when(rs.getString(2)).thenReturn("nm");
        when(rs.getDouble(3)).thenReturn(7.7);
        when(rs.getString(4)).thenReturn("prod");
        when(rs.getString(5)).thenReturn("cat");
        when(rs.getInt(6)).thenReturn(1);
        when(rs.getString(7)).thenReturn("s");
        when(rs.getString(8)).thenReturn("i");
        when(rs.getString(9)).thenReturn("d");
        when(rs.getString(10)).thenReturn("yes");
        when(rs.getString(11)).thenReturn("c");

        injectDataSource(ds);
        GuitarDAO dao = new GuitarDAO();
        ArrayList<Guitar> list = dao.admindoRetrieveGuitars();
        assertNotNull(list);
        assertEquals(1, list.size());
    }
}
