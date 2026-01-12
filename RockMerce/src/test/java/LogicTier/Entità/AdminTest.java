package LogicTier.Entità;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 class AdminTest {

    @Test
     void adminFields_setAndGet() {
        Admin a = new Admin();
        a.setEmail("admin@example.com");
        a.setUsername("admin");
        a.setPassword("secret");
        a.setName("AdminName");
        a.setSurname("AdminSurname");

        assertEquals("admin@example.com", a.getEmail());
        assertEquals("admin", a.getUsername());
        assertEquals("secret", a.getPassword());
        assertEquals("AdminName", a.getName());
        assertEquals("AdminSurname", a.getSurname());
    }
}
