package DataTier.RockMerceDAO.Customer;

import DataTier.TestDbUtil;
import DataTier.RockMerceDAO.Cart.CartDAO;
import LogicTier.Entità.CreditCard;
import LogicTier.Entità.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerDAOIntegrationTest {

    @Before
    public void setup() throws Exception {
        TestDbUtil.initH2();
    }

    @Test
    public void doCustomerSave_and_checks_and_login_flow() {
        // prepare credit card and cart
        CreditCard cc = new CreditCard();
        cc.setCardNumber("9999888877776666");
        cc.setOwner("Test User");
        cc.setCvv(123);
        cc.setExpireDate("12/30");

        CartDAO cartDAO = new CartDAO();
        int cartId = cartDAO.createCart();

        Customer c = new Customer();
        c.setUsername("userint");
        c.setEmail("userint@example.com");
        c.setName("Name");
        c.setSurname("Surname");
        c.setPassword("Abcd1234!");
        c.setPhone("1234567890");
        c.setCountry("Country");
        c.setCity("City");
        c.setAddress("Address");
        c.setCreditCard(cc);
        c.setCart(cartDAO.getCartFromDB(cartId));

        CustomerDAO dao = new CustomerDAO();
        dao.doCustomerSave(c);

        assertFalse(dao.doCheckEmail(c.getEmail())); // doCheckEmail returns true if email available, so false means it's taken
        assertFalse(dao.doCheckUsername(c.getUsername()));

        Customer logged = dao.doCheckLogin(c.getEmail(), c.getPassword());
        assertNotNull(logged);

        Customer byCart = dao.getCustomerByCart(cartId);
        assertNotNull(byCart);
    }
}

