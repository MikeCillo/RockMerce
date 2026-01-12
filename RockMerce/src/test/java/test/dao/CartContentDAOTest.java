package test.dao;

import DataTier.RockMerceDAO.CartContent.CartContentDAO;
import DataTier.RockMerceDAO.Guitar.GuitarDAO;
import DataTier.RockMerceDAO.Cart.CartDAO;
import LogicTier.Entità.Guitar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.utils.TestDatabaseUtil;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CartContentDAOTest {

    @BeforeEach
    void setUp() throws Exception {
        TestDatabaseUtil.init();
    }

    @AfterEach
    void tearDown() throws Exception {
        TestDatabaseUtil.cleanAll();
    }

    @Test
    void testInsertAndGetAndRemove() throws SQLException {
        CartDAO cartDAO = new CartDAO();
        int cartId = cartDAO.createCart();

        GuitarDAO guitarDAO = new GuitarDAO();
        Guitar g = new Guitar();
        g.setName("TestG");
        g.setPrice(50.0);
        g.setProducer("P");
        g.setCategory("C");
        g.setDisponibility(5);
        g.setSound("S");
        g.setImage("I");
        g.setDescription("D");
        g.setVisibility("yes");
        g.setColor("Red");

        guitarDAO.doInsertNewGuitar(g);
        Guitar stored = guitarDAO.findGuitar(g);
        assertNotNull(stored);

        CartContentDAO cartContentDAO = new CartContentDAO();
        cartContentDAO.insertIntoCartContent(cartId, stored,null);

        ArrayList<Guitar> content = cartContentDAO.getCartContent(cartId);
        assertNotNull(content);
        assertFalse(content.isEmpty());

        // remove
        cartContentDAO.removeGuitarFromCartContent(cartId, stored.getId());
    }
}
