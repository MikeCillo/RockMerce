package LogicTier.GestioneAutenticazione.LogIn;

import DataTier.RockMerceDAO.Customer.CustomerDAO;
import LogicTier.Entità.Customer;
import LogicTier.GestioneAutenticazione.AutenticazioneService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

public class LoginTest2 {

    @Test
    public void TestEmailSignUp() {
        AutenticazioneService logInService;
        CustomerDAO customerDaoMock;
        String emUs = "buonasera";
        String password = "ciao12.12Ciao";
        customerDaoMock = Mockito.mock(CustomerDAO.class);
        logInService = new AutenticazioneService();
        logInService.setCustomerDAO(customerDaoMock);
        when(customerDaoMock.doCheckEmail(emUs)).thenReturn(false);
        Assert.assertEquals(null, logInService.CustomerLogIn(emUs, password));
    }

    @Test
    public void TestUsernameSignUp() {
        AutenticazioneService logInService;
        CustomerDAO customerDaoMock;
        String emUs = "buonasera";
        String password = "ciao12.12Ciao";
        customerDaoMock = Mockito.mock(CustomerDAO.class);
        logInService = new AutenticazioneService();
        logInService.setCustomerDAO(customerDaoMock);
        when(customerDaoMock.doCheckEmail(emUs)).thenReturn(false);
        Assert.assertEquals(null, logInService.CustomerLogIn(emUs, password));
    }

    @Test
    public void TestPasswordSignUp() {
        AutenticazioneService logInService;
        CustomerDAO customerDaoMock;
        String emUs = "buonasera";
        // WRONG PASSWORD
        String password = "ciao12.12Ciao";
        customerDaoMock = Mockito.mock(CustomerDAO.class);
        logInService = new AutenticazioneService();
        logInService.setCustomerDAO(customerDaoMock);
        Customer customer = new Customer();
        when(customerDaoMock.doCheckEmail(emUs)).thenReturn(true);
        when(customerDaoMock.doCheckLogin(emUs, password)).thenReturn(null);
        Assert.assertEquals(null, logInService.CustomerLogIn(emUs, password));
    }

    @Test
    public void TestSignUp() {
        AutenticazioneService logInService;
        CustomerDAO customerDaoMock;
        // CORRECT FIELD
        String emUs = "buonasera";
        // CORRECT FIELD
        String password = "ciao12.12Ciao";
        customerDaoMock = Mockito.mock(CustomerDAO.class);
        logInService = new AutenticazioneService();
        logInService.setCustomerDAO(customerDaoMock);
        Customer customer = new Customer();
        when(customerDaoMock.doCheckEmail(emUs)).thenReturn(true);
        when(customerDaoMock.doCheckLogin(emUs, password)).thenReturn(customer);
        Assert.assertEquals(customer, logInService.CustomerLogIn(emUs, password));
    }

    @org.openjdk.jmh.annotations.State(org.openjdk.jmh.annotations.Scope.Thread)
    public static class _Benchmark extends se.chalmers.ju2jmh.api.JU2JmhBenchmark {

        @org.openjdk.jmh.annotations.Benchmark
        public void benchmark_TestEmailSignUp() throws java.lang.Throwable {
            this.createImplementation();
            this.runBenchmark(this.implementation()::TestEmailSignUp, this.description("TestEmailSignUp"));
        }

        @org.openjdk.jmh.annotations.Benchmark
        public void benchmark_TestUsernameSignUp() throws java.lang.Throwable {
            this.createImplementation();
            this.runBenchmark(this.implementation()::TestUsernameSignUp, this.description("TestUsernameSignUp"));
        }

        @org.openjdk.jmh.annotations.Benchmark
        public void benchmark_TestPasswordSignUp() throws java.lang.Throwable {
            this.createImplementation();
            this.runBenchmark(this.implementation()::TestPasswordSignUp, this.description("TestPasswordSignUp"));
        }

        @org.openjdk.jmh.annotations.Benchmark
        public void benchmark_TestSignUp() throws java.lang.Throwable {
            this.createImplementation();
            this.runBenchmark(this.implementation()::TestSignUp, this.description("TestSignUp"));
        }

        private LoginTest2 implementation;

        @java.lang.Override
        public void createImplementation() throws java.lang.Throwable {
            this.implementation = new LoginTest2();
        }

        @java.lang.Override
        public LoginTest2 implementation() {
            return this.implementation;
        }
    }
}
