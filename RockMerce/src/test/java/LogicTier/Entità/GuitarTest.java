package LogicTier.Entità;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuitarTest {

    @Test
    void gettersAndSetters_work() {
        Guitar g = new Guitar();
        g.setId(5);
        g.setName("Fender Strat");
        g.setPrice(999.99);
        g.setProducer("Fender");
        g.setDisponibility(2);
        g.setSound("bright");
        g.setImage("img.png");
        g.setDescription("A nice guitar");
        g.setCategory("electric");
        g.setVisibility("public");
        g.setColor("red");

        assertEquals(5, g.getId());
        assertEquals("Fender Strat", g.getName());
        assertEquals(999.99, g.getPrice(), 0.001);
        assertEquals("Fender", g.getProducer());
        assertEquals(2, g.getDisponibility());
        assertEquals("bright", g.getSound());
        assertEquals("img.png", g.getImage());
        assertEquals("A nice guitar", g.getDescription());
        assertEquals("electric", g.getCategory());
        assertEquals("public", g.getVisibility());
        assertEquals("red", g.getColor());
    }
}

