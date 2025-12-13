package LogicTier.Entità;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

 class CheckoutTest {

    @Test
    void addGuitar_updatesTotalPrice_andList() {
        Checkout co = new Checkout();
        Guitar g = new Guitar();
        g.setPrice(150.0);
        co.addGuitar(g);

        assertEquals(1, co.getGuitars().size());
        assertEquals(150.0, co.getTotalPrice(), 0.001);
    }

    @Test
     void setOrderDate_and_setSendDate_produceNonNullStrings() {
        Checkout co = new Checkout();
        co.setOrderDate();
        assertNotNull(co.getOrderDate());
        co.setSendDate();
        assertNotNull(co.getSendDate());
    }

    @Test
    void settersAndGetters_work() {
        Checkout co = new Checkout();
        co.setId(3);
        co.setCartId(9);
        co.setTotalPrice(12.5);
        ArrayList<Guitar> list = new ArrayList<>();
        co.setGuitars(list);
        co.setSendDate("01/01/2024");
        co.setOrderDate("02/02/2024");

        assertEquals(3, co.getId());
        assertEquals(9, co.getCartId());
        assertEquals(12.5, co.getTotalPrice(), 0.001);
        assertSame(list, co.getGuitars());
        assertEquals("01/01/2024", co.getSendDate());
        assertEquals("02/02/2024", co.getOrderDate());
    }
}

