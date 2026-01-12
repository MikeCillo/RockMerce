package LogicTier.Entità;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

 class CartTest {

    @Test
   void addAndRemoveGuitar_work_and_updateTotals() {
        Cart cart = new Cart();
        Guitar g1 = new Guitar();
        g1.setId(1);
        g1.setPrice(100.0);
        g1.setDisponibility(1);

        Guitar g2 = new Guitar();
        g2.setId(2);
        g2.setPrice(200.0);
        g2.setDisponibility(2);

        cart.addGuitar(g1);
        cart.addGuitar(g2);

        assertEquals(2, cart.getGuitars().size());
        assertEquals(300.0, cart.getTempTotal(), 0.001);
        assertEquals(3, cart.getNumGuitars());

        // remove by user position
        Guitar removed = cart.removeGuitarUser(0);
        assertNotNull(removed);
        assertEquals(1, cart.getGuitars().size());
        assertEquals(200.0, cart.getTempTotal(), 0.001);

        // remove by id
        Guitar removedById = cart.removeGuitar(2);
        assertNotNull(removedById);
        assertEquals(0, cart.getGuitars().size());
        assertEquals(0.0, cart.getTempTotal(), 0.001);

        // invalid removals
        assertNull(cart.removeGuitarUser(-1));
        assertNull(cart.removeGuitar(999));
    }

    @Test
    void settersAndGetters_work() {
        Cart cart = new Cart();
        cart.setId(7);
        cart.setTempTotal(55.5);
        cart.setNumGuitars(4);
        ArrayList<Guitar> list = new ArrayList<>();
        cart.setGuitars(list);

        assertEquals(7, cart.getId());
        assertEquals(55.5, cart.getTempTotal(), 0.001);
        assertEquals(4, cart.getNumGuitars());
        assertSame(list, cart.getGuitars());
    }
}

