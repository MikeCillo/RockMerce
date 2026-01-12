package DataTier.RockMerceDAO.Customer;

import DataTier.TestDbUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerDAOIntegrationAdditionalTest {

    @Before
    public void setup() throws Exception {
        TestDbUtil.initH2();
    }

    @Test
    public void doCheckEmail_and_username_when_noCustomer()  {
        CustomerDAO dao = new CustomerDAO();
        assertTrue(dao.doCheckEmail("noexist@example.com"));
        assertTrue(dao.doCheckUsername("nousername"));
    }

    @Test
    public void getCustomerByCart_returnsNull_when_noMatch()  {
        CustomerDAO dao = new CustomerDAO();
        assertNull(dao.getCustomerByCart(9999));
    }
}

