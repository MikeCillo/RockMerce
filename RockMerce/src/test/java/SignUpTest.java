

import LogicTier.Entità.CreditCard;
import LogicTier.Entità.Customer;
import LogicTier.GestioneAutenticazione.AutenticazioneService;
import LogicTier.GestioneSignUp.SignUpService;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SignUpTest extends Mockito {

    @Test
    public void TestNameSignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();
        customer.setName("Aee33");
        customer.setSurname("Rossi");
        customer.setUsername("Pablito09");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Torino");
        customer.setCountry("Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("1234567800001234");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(231);

        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);

        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);
        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);

        Assert.assertFalse(signUpService.signUpValidation(customer,creditCard));
    }


    @Test
    public void TestSurnameSignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();
        customer.setName("Mario");
        customer.setSurname("Ro3");
        customer.setUsername("Pablito09");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Torino");
        customer.setCountry("Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("1234567800001234");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(231);

        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);

        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);
        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);

        Assert.assertFalse(signUpService.signUpValidation(customer,creditCard));
    }



    @Test
    public void TestUsernameSignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();

        customer.setName("Mario");
        customer.setSurname("Rossi");
        customer.setUsername("qwertyuiohdgfbnmvkiyd");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Torino");
        customer.setCountry("Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("1234567800001234");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(231);

        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);

        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(false);
        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);

        Assert.assertEquals(false,signUpService.signUpValidation(customer,creditCard));

    }

    @Test
    public void TestEmailSignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();

        customer.setName("Mario");
        customer.setSurname("Rossi");
        customer.setUsername("Pablito09");
        customer.setEmail("prossigmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Torino");
        customer.setCountry("Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("1234567800001234");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(231);

        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);

        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);
        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(false);

        Assert.assertEquals(false,signUpService.signUpValidation(customer,creditCard));
    }

    @Test
    public void TestPasswordSignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();

        customer.setName("Mario");
        customer.setSurname("Rossi");
        customer.setUsername("Pablito09");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("otaliabrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Torino");
        customer.setCountry("Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("1234432112344321");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(231);

        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);


        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);
        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);

        Assert.assertFalse(signUpService.signUpValidation(customer,creditCard));
    }

    @Test
    public void TestPhoneSignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();

        customer.setName("Mario");
        customer.setSurname("Rossi");
        customer.setUsername("Pablito09");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("234567892i");
        customer.setCity("Torino");
        customer.setCountry("Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("1234567800001234");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(231);


        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);


        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);
        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);

        Assert.assertFalse(signUpService.signUpValidation(customer,creditCard));

    }

    @Test
    public void TestCitySignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();

        customer.setName("Mario");
        customer.setSurname("Rossi");
        customer.setUsername("Pablito09");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Tori5o");
        customer.setCountry("Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("1234567800001234");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(231);


        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);

        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);
        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);

        Assert.assertFalse(signUpService.signUpValidation(customer,creditCard));

    }


    @Test
    public void TestCountrySignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();

        customer.setName("Mario");
        customer.setSurname("Rossi");
        customer.setUsername("Pablito09");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Torino");
        customer.setCountry("5Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("1234567800001234");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(231);


        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);

        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);
        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);

        Assert.assertFalse(signUpService.signUpValidation(customer,creditCard));

    }

    @Test
    public void TestAddressSignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();

        customer.setName("Mario");
        customer.setSurname("Rossi");
        customer.setUsername("Pablito09");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Torino");
        customer.setCountry("Italy");
        customer.setAddress("");
        creditCard.setCardNumber("1234567800001234");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(231);

        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);

        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);
        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);

        Assert.assertFalse(signUpService.signUpValidation(customer,creditCard));

    }


    @Test
    public void TestCardNumberSignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();

        customer.setName("Mario");
        customer.setSurname("Rossi");
        customer.setUsername("Pablito09");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Torino");
        customer.setCountry("Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("123456780000123");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(231);


        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);

        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);
        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);

        Assert.assertFalse(signUpService.signUpValidation(customer,creditCard));

    }


    @Test
    public void TestOwnerSignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();

        customer.setName("Mario");
        customer.setSurname("Rossi");
        customer.setUsername("Pablito09");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Torino");
        customer.setCountry("Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("1234567800001234");
        creditCard.setOwner("Piero 6ossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(231);


        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);

        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);
        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);

        Assert.assertFalse(signUpService.signUpValidation(customer,creditCard));

    }



    @Test
    public void TestExpireDateSignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();

        customer.setName("Mario");
        customer.setSurname("Rossi");
        customer.setUsername("Pablito09");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Torino");
        customer.setCountry("Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("1234567800001234");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate(null);
        creditCard.setCvv(231);


        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);

        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);
        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);

        Assert.assertFalse(signUpService.signUpValidation(customer,creditCard));

    }


    @Test
    public void TestCvvSignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();

        customer.setName("Mario");
        customer.setSurname("Rossi");
        customer.setUsername("Pablito09");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Torino");
        customer.setCountry("Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("1234567800001234");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(23);


        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);

        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);
        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);

        Assert.assertFalse(signUpService.signUpValidation(customer,creditCard));

    }

    @Test
    public void TestSignUp(){
        Customer customer=new Customer();
        CreditCard creditCard=new CreditCard();

        customer.setName("Mario");
        customer.setSurname("Rossi");
        customer.setUsername("Pablito09");
        customer.setEmail("prossi@gmail.com");
        customer.setPassword("ItaliaBrasile3.2");
        customer.setPhone("2345678923");
        customer.setCity("Torino");
        customer.setCountry("Italy");
        customer.setAddress("Via Roma 10");
        creditCard.setCardNumber("1234567800001234");
        creditCard.setOwner("Piero Rossi");
        creditCard.setExpireDate("10/2029");
        creditCard.setCvv(231);


        AutenticazioneService autenticazioneServiceMock=Mockito.mock(AutenticazioneService.class);
        SignUpService signUpService=new SignUpService();
        signUpService.setAutenticazioneService(autenticazioneServiceMock);

        when(autenticazioneServiceMock.CheckUsername(customer.getUsername())).thenReturn(true);
        when(autenticazioneServiceMock.CheckEmail(customer.getEmail())).thenReturn(true);
        Assert.assertEquals(true,signUpService.signUpValidation(customer,creditCard));
    }
}