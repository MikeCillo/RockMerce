package LogicTier.Utils.SignUpUtils;

import LogicTier.Entità.CreditCard;
import LogicTier.Entità.Customer;

public interface SignUpInterface {

    Customer SignUp(Customer customer,CreditCard creditCard);
    boolean SignUpValidation(Customer customer, CreditCard creditCard);
    boolean CustomerNameValidation(String name);
    boolean CustomerSurnameValidation(String surname);
    boolean CustomerUsernameValidation(String username);
    boolean CustomerEmailValidation(String email);
    boolean CustomerPasswordValidation(String password);
    boolean CustomerCityValidation(String city);
    boolean CustomerCountryValidation(String country);
    boolean CustomerAddressValidation(String address);
    boolean CustomerPhoneValidation(String phone);
    boolean CreditCardNumberValidation(String cardNumber);
    boolean CreditCardOwnerValidation(String owner);
    boolean CreditCardCvvValidation(int cvv);
    boolean CreditCardExpireDateValidation(String expDate);

}
