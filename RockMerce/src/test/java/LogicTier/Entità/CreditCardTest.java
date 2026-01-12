package LogicTier.Entità;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

  class CreditCardTest {

    @Test
     void gettersAndSetters_work() {
        CreditCard cc = new CreditCard();
        cc.setId(10);
        cc.setCardNumber("1234-5678-9012-3456");
        cc.setOwner("Mario Rossi");
        cc.setExpireDate("12/25");
        cc.setCvv(123);

        assertEquals(10, cc.getId());
        assertEquals("1234-5678-9012-3456", cc.getCardNumber());
        assertEquals("Mario Rossi", cc.getOwner());
        assertEquals("12/25", cc.getExpireDate());
        assertEquals(123, cc.getCvv());
    }
}

