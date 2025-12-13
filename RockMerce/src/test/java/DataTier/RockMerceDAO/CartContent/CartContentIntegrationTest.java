package DataTier.RockMerceDAO.CartContent;

import DataTier.TestDbUtil;
import DataTier.RockMerceDAO.Cart.CartDAO;
import LogicTier.Entità.Guitar;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CartContentIntegrationTest {

    @Before
    public void setup() throws Exception {
        TestDbUtil.initH2();
    }

    @Test
    public void insertAndRetrieve_and_remove_flow() throws Exception {
        CartDAO cartDAO = new CartDAO();
        int cartId = cartDAO.createCart();

        // insert guitar into Guitar table
        try (java.sql.Connection con = DataTier.DBCONNECTION.DbConnection.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement("INSERT INTO Guitar (name,price,producer,category,disponibility,sound,image,description,visibility,color) values ('GC1',120.0,'P','electric',1,'https://www.youtube.com/watch?v=abcdefghijklmnopqrstuvwxyz','img.png','d','yes','red')", java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.executeUpdate();
        }

        CartContentDAO dao = new CartContentDAO();
        Guitar g = new Guitar(); g.setId(1); g.setPrice(120.0); g.setDisponibility(1);
        dao.insertIntoCartContent(cartId, g);

        ArrayList<Guitar> content = dao.getCartContent(cartId);
        assertNotNull(content);
        assertTrue(content.size() >= 1);

        // remove
        dao.removeGuitarFromCartContent(cartId, 1);

        // after removal, getCartContent should be empty
        ArrayList<Guitar> after = dao.getCartContent(cartId);
        assertTrue(after.isEmpty());
    }
}

