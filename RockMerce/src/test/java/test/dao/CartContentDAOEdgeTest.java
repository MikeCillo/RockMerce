package test.dao;

import DataTier.RockMerceDAO.CartContent.CartContentDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.utils.TestDatabaseUtil;

import static org.junit.jupiter.api.Assertions.*;

class CartContentDAOEdgeTest {

    @BeforeEach
    void setUp() throws Exception {
        TestDatabaseUtil.init();
    }

    @AfterEach
    void tearDown() throws Exception {
        TestDatabaseUtil.cleanAll();
    }

    @Test
     void testRemoveNonExistingGuitarThrows() {
        CartContentDAO dao = new CartContentDAO();
        assertThrows(RuntimeException.class, () -> dao.removeGuitarFromCartContent(9999, 12345));
    }

}

