package test.dao;

import DataTier.RockMerceDAO.Customer.CustomerDAO;
import DataTier.RockMerceDAO.CreditCard.CreditCardDAO;
import DataTier.RockMerceDAO.Cart.CartDAO;
import LogicTier.Entità.CreditCard;
import LogicTier.Entità.Customer;
import LogicTier.Entità.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.utils.TestDatabaseUtil;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOEdgeTest {

    @BeforeEach
    void setUp() throws Exception {
        TestDatabaseUtil.init();
    }

    @AfterEach
    void tearDown() throws Exception {
        TestDatabaseUtil.cleanAll();
    }

    @Test
    void testDoCheckLoginWithWrongCredentialsReturnsNull() {
        // prepare a customer
        CreditCard card = new CreditCard();
        card.setCardNumber("1234");
        card.setOwner("Me");
        card.setExpireDate("12/30");
        card.setCvv(123);

        CreditCardDAO creditCardDAO = new CreditCardDAO();
        int cardId = creditCardDAO.doCreditCardSave(card);
        assertTrue(cardId > 0);

        CartDAO cartDAO = new CartDAO();
        int cartId = cartDAO.createCart();
        assertTrue(cartId > 0);

        Customer c = new Customer();
        c.setUsername("u_edge");
        c.setEmail("edge@example.com");
        c.setName("N");
        c.setSurname("S");
        c.setPassword("pwd");
        c.setCreditCard(card);
        Cart cc = new Cart(); cc.setId(cartId); c.setCart(cc);

        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.doCustomerSave(c);

        // wrong password
        Customer logged = customerDAO.doCheckLogin("u_edge", "badpwd");
        assertNull(logged);

        // wrong username
        Customer logged2 = customerDAO.doCheckLogin("nonexistent", "pwd");
        assertNull(logged2);
    }

    @Test
    void testGetCustomerByCartReturnsNullWhenNone() {
        CartDAO cartDAO = new CartDAO();
        int cartId = cartDAO.createCart();

        CustomerDAO customerDAO = new CustomerDAO();
        Customer byCart = customerDAO.getCustomerByCart(cartId);
        assertNull(byCart);
    }
}

