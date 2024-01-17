import DataTier.RockMerceDAO.Customer.CustomerDAO;
import LogicTier.Entità.Customer;
import LogicTier.GestioneAutenticazione.AutenticazioneService;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class LoginTest {


    @Test
    public void TestEmailSignUp(){
        AutenticazioneService logInService;
        CustomerDAO customerDaoMock;

        String emUs="buonasera";
        String password="ciao12.12Ciao";

        customerDaoMock=Mockito.mock(CustomerDAO.class);
        logInService=new AutenticazioneService();
        logInService.setCustomerDAO(customerDaoMock);

        when(customerDaoMock.doCheckEmail(emUs)).thenReturn(false);
        Assert.assertEquals(null,logInService.CustomerLogIn(emUs,password));
    }

    @Test
    public void TestUsernameSignUp(){
        AutenticazioneService logInService;
        CustomerDAO customerDaoMock;

        String emUs="buonasera";
        String password="ciao12.12Ciao";
        customerDaoMock=Mockito.mock(CustomerDAO.class);
        logInService=new AutenticazioneService();
        logInService.setCustomerDAO(customerDaoMock);

        when(customerDaoMock.doCheckEmail(emUs)).thenReturn(false);
        Assert.assertEquals(null,logInService.CustomerLogIn(emUs,password));
    }

    @Test
    public void TestPasswordSignUp(){
        AutenticazioneService logInService;
        CustomerDAO customerDaoMock;

        String emUs="buonasera";
        String password="ciao12.12Ciao";        //WRONG PASSWORD
        customerDaoMock=Mockito.mock(CustomerDAO.class);
        logInService=new AutenticazioneService();
        logInService.setCustomerDAO(customerDaoMock);

        Customer customer=new Customer();
        when(customerDaoMock.doCheckEmail(emUs)).thenReturn(true);
        when(customerDaoMock.doCheckLogin(emUs,password)).thenReturn(null);
        Assert.assertEquals(null,logInService.CustomerLogIn(emUs,password));
    }


    @Test
    public void TestSignUp(){
        AutenticazioneService logInService;
        CustomerDAO customerDaoMock;

        String emUs="buonasera";                 //CORRECT FIELD
        String password="ciao12.12Ciao";        //CORRECT FIELD
        customerDaoMock=Mockito.mock(CustomerDAO.class);
        logInService=new AutenticazioneService();
        logInService.setCustomerDAO(customerDaoMock);

        Customer customer=new Customer();
        when(customerDaoMock.doCheckEmail(emUs)).thenReturn(true);
        when(customerDaoMock.doCheckLogin(emUs,password)).thenReturn(customer);
        Assert.assertEquals(customer,logInService.CustomerLogIn(emUs,password));
    }

}