package test.dao;

import DataTier.RockMerceDAO.Checkout.CheckoutDAO;
import DataTier.RockMerceDAO.CheckoutContent.CheckoutContentDAO;
import DataTier.RockMerceDAO.Customer.CustomerDAO;
import DataTier.RockMerceDAO.Cart.CartDAO;
import LogicTier.Entità.Checkout;
import LogicTier.Entità.Guitar;
import LogicTier.Entità.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.utils.TestDatabaseUtil;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

 class CheckoutDAOMoreTest {

    @BeforeEach
    void setUp() throws Exception {
        TestDatabaseUtil.init();
    }

    @AfterEach
     void tearDown() throws Exception {
        TestDatabaseUtil.cleanAll();
    }

    @Test
    void testUpdateCheckoutUpdatesTotal() {
        CartDAO cartDAO = new CartDAO();
        int cartId = cartDAO.createCart();

        CheckoutDAO checkoutDAO = new CheckoutDAO();
        int checkoutId = checkoutDAO.newCheckout(cartId, "2025-02-01", "2025-02-02");
        assertTrue(checkoutId > 0);

        // update total
        checkoutDAO.updateCheckout(150.75, checkoutId);

        // retrieve orders and find the checkout
        ArrayList<Checkout> orders = checkoutDAO.retrieveOrders();
        boolean found = false;
        for (Checkout c : orders) {
            if (c.getId() == checkoutId) {
                assertEquals(150.75, c.getTotalPrice(), 0.001);
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    void testRetrieveCustomersCheckoutsWithMultipleEntries() {
        CartDAO cartDAO = new CartDAO();
        int cartId = cartDAO.createCart();

        CheckoutDAO checkoutDAO = new CheckoutDAO();
        int id1 = checkoutDAO.newCheckout(cartId, "2025-03-01", "2025-03-02");
        int id2 = checkoutDAO.newCheckout(cartId, "2025-04-01", "2025-04-02");

        CheckoutContentDAO contentDAO = new CheckoutContentDAO();
        Guitar g = new Guitar();
        g.setName("Gmulti");
        g.setDisponibility(2);
        g.setPrice(30.0);
        g.setProducer("P");
        g.setCategory("C");
        g.setColor("Blue");

        contentDAO.addToCheckoutContent(id1, g);
        contentDAO.addToCheckoutContent(id2, g);

        ArrayList<Checkout> checkouts = checkoutDAO.retrieveCustomersCheckouts(cartId);
        assertEquals(2, checkouts.size());
        for (Checkout c : checkouts) {
            assertNotNull(c.getGuitars());
            assertFalse(c.getGuitars().isEmpty());
        }
    }

    @Test
    void testRetrieveOrdersIncludesCustomer() {
        // prepare customer with cart
        CartDAO cartDAO = new CartDAO();
        int cartId = cartDAO.createCart();

        LogicTier.Entità.CreditCard card = new LogicTier.Entità.CreditCard();
        card.setCardNumber("9999");
        card.setOwner("Owner");
        card.setExpireDate("01/30");
        card.setCvv(321);
        Customer cust = new Customer();
        cust.setUsername("cust1");
        cust.setEmail("cust1@example.com");
        cust.setPassword("p");
        cust.setName("N");
        cust.setSurname("S");
        cust.setCreditCard(card);
        LogicTier.Entità.Cart c = new LogicTier.Entità.Cart();
        c.setId(cartId);
        cust.setCart(c);

        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.doCustomerSave(cust);

        // create checkout linked to cart
        CheckoutDAO checkoutDAO = new CheckoutDAO();
        int checkoutId = checkoutDAO.newCheckout(cartId, "2025-05-01", "2025-05-02");

        ArrayList<Checkout> orders = checkoutDAO.retrieveOrders();
        boolean found = false;
        for (Checkout o : orders) {
            if (o.getId() == checkoutId) {
                assertNotNull(o.getCustomer());
                assertEquals("cust1", o.getCustomer().getUsername());
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    void testRetrieveEarningsSumMultipleCheckouts() {
        CartDAO cartDAO = new CartDAO();
        int cartA = cartDAO.createCart();
        int cartB = cartDAO.createCart();

        CheckoutDAO checkoutDAO = new CheckoutDAO();
        int idA = checkoutDAO.newCheckout(cartA, "2025-06-01", "2025-06-02");
        int idB = checkoutDAO.newCheckout(cartB, "2025-06-03", "2025-06-04");

        // update totals
        checkoutDAO.updateCheckout(100.0, idA);
        checkoutDAO.updateCheckout(200.5, idB);

        Double earnings = checkoutDAO.retrieveEarnings();
        assertEquals(300.5, earnings, 0.001);
    }
}

