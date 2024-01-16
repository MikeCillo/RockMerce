package LogicTier.GestioneSignUp;
import DataTier.RockMerceDAO.Cart.CartDAO;
import DataTier.RockMerceDAO.CreditCard.CreditCardDAO;
import DataTier.RockMerceDAO.Customer.CustomerDAO;
import LogicTier.Entità.Cart;
import LogicTier.Entità.CreditCard;
import LogicTier.Entità.Customer;
import LogicTier.GestioneAutenticazione.AutenticazioneService;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpService implements SignUpInterface {

    private static final Logger logger = Logger.getLogger(SignUpService.class.getName());

    private AutenticazioneService autenticazioneService = new AutenticazioneService();


    public AutenticazioneService getAutenticazioneService() {
        return autenticazioneService;
    }

    public void setAutenticazioneService(AutenticazioneService autenticazioneService) {
        this.autenticazioneService = autenticazioneService;
    }

    @Override
    public Customer signUp(Customer customer, CreditCard creditCard) {
        CreditCardDAO creditCardDAO = new CreditCardDAO();         //credit card db methods
        customer.setCreditCard(creditCard);                       //save card id into customer
        creditCardDAO.doCreditCardSave(creditCard);

        //CART
        CartDAO cartDAO = new CartDAO();                          //cart db methods
        int id = cartDAO.createCart();                            //create a cart for new customer
        Cart cart = cartDAO.getCartFromDB(id);
        customer.setCart(cart);                                   //save cart id into customer

        //Customer
        CustomerDAO customerDAO = new CustomerDAO();              //Customer db methods
        customerDAO.doCustomerSave(customer);                     //save new customer

        return customer;
    }


    @Override
    public boolean signUpValidation(Customer customer, CreditCard creditCard) {

        String reg = "[a-zA-Z\\s]{1,20}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(customer.getName());

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: NAME");
            return false;
        }

        reg = "[a-zA-Z\\s]{1,20}";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(customer.getSurname());
        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: SURNAME ");
            return false;
        }

        reg = "[a-zA-Z0-9]{1,20}";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(customer.getUsername());
        if (!matcher.matches() || !this.autenticazioneService.CheckUsername(customer.getUsername())) {
            logger.log(Level.SEVERE, "ERROR: USERNAME ");
            return false;
        }


        String emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
        pattern = Pattern.compile(emailReg);
        matcher = pattern.matcher(customer.getEmail());
        if (!matcher.matches() || !this.autenticazioneService.CheckEmail(customer.getEmail())) {
            logger.log(Level.SEVERE, "ERROR: EMAIL ");
            return false;
        }


        String passwordReg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[?!@#$%^&+=.,])(?=\\S+$).{8,}";
        pattern = Pattern.compile(passwordReg);
        matcher = pattern.matcher(customer.getPassword());
        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: PASSWORD");
            return false;
        }


        reg = "[a-zA-Z\\s]{1,20}";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(customer.getCity());

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: CITY");
            return false;
        }


        reg = "[a-zA-Z\\s]{1,20}";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(customer.getCountry());

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: COUNTRY");
            return false;
        }

        reg = "[A-Za-z0-9'.,\\-\\s]{1,30}";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(customer.getAddress());

        if (customer.getAddress().length()<=0) {
            logger.log(Level.SEVERE, "ERROR: ADDRESS");
            return false;
        }


        reg = "[0-9]{10}";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(customer.getPhone());
        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: PHONE ");
            return false;
        }

        reg = "[0-9]{16}";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(creditCard.getCardNumber());

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: CARD NUMBER ");
            return false;
        }


        reg = "[a-zA-Z\\s]{1,30}";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(creditCard.getOwner());

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: OWNER ");
            return false;
        }

        reg = "[0-9]{3}";
        String code = String.valueOf(creditCard.getCvv());
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(code);

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: CVV ");
            return false;
        }


        if (creditCard.getExpireDate() == null) {
            logger.log(Level.SEVERE, "ERROR: EXPIRE DATE ");
            return false;
        }

        return true;
    }

}
