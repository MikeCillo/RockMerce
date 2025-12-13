package LogicTier.Entità;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 class CustomerTest {

    @Test
    void creditCardAndCart_setAndGet() {
        Customer c = new Customer();
        CreditCard cc = new CreditCard();
        cc.setId(11);
        c.setCreditCard(cc);
        Cart cart = new Cart();
        cart.setId(22);
        c.setCart(cart);

        assertNotNull(c.getCreditCard());
        assertEquals(11, c.getCreditCard().getId());
        assertNotNull(c.getCart());
        assertEquals(22, c.getCart().getId());
    }

    @Test
     void basicFields_setAndGet() {
        Customer c = new Customer();
        c.setUsername("user1");
        c.setEmail("a@b.com");
        c.setPassword("pwd");
        c.setName("Nome");
        c.setSurname("Cognome");
        c.setPhone("12345");
        c.setCity("City");
        c.setCountry("Country");
        c.setAddress("Addr");

        assertEquals("user1", c.getUsername());
        assertEquals("a@b.com", c.getEmail());
        assertEquals("pwd", c.getPassword());
        assertEquals("Nome", c.getName());
        assertEquals("Cognome", c.getSurname());
        assertEquals("12345", c.getPhone());
        assertEquals("City", c.getCity());
        assertEquals("Country", c.getCountry());
        assertEquals("Addr", c.getAddress());
    }
}

