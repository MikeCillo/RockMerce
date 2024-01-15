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

    private static final  Logger logger = Logger.getLogger(SignUpService.class.getName());

    private AutenticazioneService autenticazioneService=new AutenticazioneService();


    public AutenticazioneService getAutenticazioneService() {
        return autenticazioneService;
    }

    public void setAutenticazioneService(AutenticazioneService autenticazioneService) {
        this.autenticazioneService = autenticazioneService;
    }

    @Override
    public Customer SignUp(Customer customer,CreditCard creditCard) {
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
    public boolean SignUpValidation(Customer customer, CreditCard creditCard){
       if(CustomerNameValidation(customer.getName()) && CustomerSurnameValidation(customer.getSurname())
       && CustomerEmailValidation(customer.getEmail()) && CustomerUsernameValidation(customer.getUsername())
       && CustomerCityValidation(customer.getCity()) && CustomerAddressValidation(customer.getAddress())
       && CustomerCountryValidation(customer.getCountry()) && CustomerPhoneValidation(customer.getPhone())
       && CustomerPasswordValidation(customer.getPassword()) && CreditCardOwnerValidation(creditCard.getOwner())
       && CreditCardNumberValidation(creditCard.getCardNumber()) && CreditCardCvvValidation(creditCard.getCvv())
       && CreditCardExpireDateValidation(creditCard.getExpireDate())){
           return true;
       }
        return false;
    }


    @Override
    public boolean CustomerNameValidation(String name) {
        //CUSTOMER VALIDATION
        String reg = "[a-zA-Z]{1,20}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: NAME");
            return false;
        } else {
            return true;
        }
    }


    @Override
    public boolean CustomerSurnameValidation(String surname) {
        //SURNAME VALIDATION
        String reg = "[a-zA-Z]{1,20}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(surname);
        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: SURNAME ");
            return false;
        } else {
            return true;
        }
    }


    @Override
    public boolean CustomerUsernameValidation(String username) {
        //USERNAME VALIDATION
        String reg = "[a-zA-Z0-9]{1,20}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches() || !this.autenticazioneService.CheckUsername(username)) {
            logger.log(Level.SEVERE, "ERROR: USERNAME ");
            return false;
        } else {
            return true;
        }


    }

    @Override
    public boolean CustomerEmailValidation(String email){
        //EMAIL VALIDATION

        String emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
        Pattern pattern = Pattern.compile(emailReg);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches() || !this.autenticazioneService.CheckEmail(email)) {
            logger.log(Level.SEVERE, "ERROR: EMAIL ");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean CustomerPasswordValidation(String password) {
        //PASSWORD VALIDATION
        String passwordReg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[?!@#$%^&+=.,])(?=\\S+$).{8,}";
        Pattern pattern = Pattern.compile(passwordReg);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: PASSWORD");
            return false;
        } else {
            return true;
        }
    }


    @Override
    public boolean CustomerCityValidation(String city) {
        //CITY VALIDATION
        String reg = "[a-zA-Z]{1,20}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(city);

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: CITY");
            return false;
        } else {
            return true;
        }
    }


    @Override
    public boolean CustomerCountryValidation(String country){
        //COUNTRY VALIDATION
        String reg = "[a-zA-Z]{1,20}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(country);

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: COUNTRY");
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean CustomerAddressValidation(String address){
        //ADDRESS VALIDATION
        String reg = "[A-Za-z0-9'.,\\-\\s]{1,30}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(address);

            if (!matcher.matches()) {
                logger.log(Level.SEVERE, "ERROR: ADDRESS");
                return false;
            }else {
                return true;
            }
    }


    @Override
    public boolean CustomerPhoneValidation(String phone){
        //PHONE VALIDATION
        String reg = "[0-9]{10}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(phone);

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: PHONE ");
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean CreditCardNumberValidation(String cardNumber){
        //CREDIT CARD VALIDATION
        String reg="[0-9]{16}";
        Pattern pattern=Pattern.compile(reg);
        Matcher matcher = pattern.matcher(cardNumber);

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: CARD NUMBER ");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean CreditCardOwnerValidation(String owner){
        //CREDIT CARD OWNER VALIDATION
        String reg="[a-zA-Z\\s]{1,30}";
        Pattern pattern=Pattern.compile(reg);
        Matcher matcher = pattern.matcher(owner);

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: OWNER ");
            return false;
        } else {
            return true;
        }
    }


    @Override
    public boolean CreditCardCvvValidation(int cvv) {
        //CREDIT CARD CVV VALIDATION
        String reg = "[0-9]{3}";
        String code= String.valueOf(cvv);
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(code);

        if (!matcher.matches()) {
            logger.log(Level.SEVERE, "ERROR: CVV ");
            return false;
        } else {
            return true;
        }
    }


    @Override
    public boolean CreditCardExpireDateValidation(String expDate){
        //CREDIT CARD EXPIRE DATE VALIDATION
        if (expDate ==null) {
            logger.log(Level.SEVERE, "ERROR: EXPIRE DATE ");
            return false;
        } else {
            return true;
        }
    }


}
