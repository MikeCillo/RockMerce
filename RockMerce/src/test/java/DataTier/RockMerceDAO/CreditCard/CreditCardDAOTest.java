package DataTier.RockMerceDAO.CreditCard;

import DataTier.DBCONNECTION.DbConnection;
import LogicTier.Entità.CreditCard;
import LogicTier.exception.CrediCardException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

 class CreditCardDAOTest {

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
    void doCreditCardSave_shouldReturnIdAndSetOnCard() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rsKeys = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString(), org.mockito.ArgumentMatchers.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);
        when(ps.getGeneratedKeys()).thenReturn(rsKeys);
        when(rsKeys.next()).thenReturn(true);
        when(rsKeys.getInt(1)).thenReturn(77);

        injectDataSource(ds);
        CreditCard card = new CreditCard();
        card.setCardNumber("1234");

        CreditCardDAO dao = new CreditCardDAO();
        int id = dao.doCreditCardSave(card);
        assertEquals(77, id);
        assertEquals(77, card.getId());
    }

    @Test
    void retrieveCreditCardById_shouldReturnCardOrNull() throws Exception {
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
        when(rs.getString(2)).thenReturn("1111");
        when(rs.getString(3)).thenReturn("owner");
        when(rs.getString(4)).thenReturn("10/2030");
        when(rs.getInt(5)).thenReturn(123);

        injectDataSource(ds);
        CreditCardDAO dao = new CreditCardDAO();
        CreditCard c = dao.retrieveCreditCardById(5);
        assertNotNull(c);
        assertEquals(5, c.getId());

        // not found
        reset(rs);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);
        CreditCard c2 = dao.retrieveCreditCardById(99);
        assertNull(c2);
    }

    @Test
    void doCreditCardSave_shouldThrowWhenZeroRowsAffected() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString(), org.mockito.ArgumentMatchers.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(0);

        injectDataSource(ds);
        CreditCard card = new CreditCard();

        CreditCardDAO dao = new CreditCardDAO();
        assertThrows(CrediCardException.class, () -> dao.doCreditCardSave(card));
    }

    @Test
    void doCreditCardSave_shouldThrowWhenNoGeneratedKey() throws Exception {
        DataSource ds = mock(DataSource.class);
        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rsKeys = mock(ResultSet.class);

        when(ds.getConnection()).thenReturn(con);
        when(con.prepareStatement(anyString(), org.mockito.ArgumentMatchers.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);
        when(ps.getGeneratedKeys()).thenReturn(rsKeys);
        when(rsKeys.next()).thenReturn(false);

        injectDataSource(ds);
        CreditCard card = new CreditCard();

        CreditCardDAO dao = new CreditCardDAO();
        assertThrows(CrediCardException.class, () -> dao.doCreditCardSave(card));
    }

    @Test
    void doCreditCardSave_shouldWrapSqlException() throws Exception {
        DataSource ds = mock(DataSource.class);
        when(ds.getConnection()).thenThrow(new SQLException("boom"));

        injectDataSource(ds);
        CreditCardDAO dao = new CreditCardDAO();
        assertThrows(CrediCardException.class, () -> dao.doCreditCardSave(new CreditCard()));
    }
}
