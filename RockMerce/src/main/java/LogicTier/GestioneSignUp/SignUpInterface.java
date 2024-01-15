package LogicTier.GestioneSignUp;

import LogicTier.Entità.CreditCard;
import LogicTier.Entità.Customer;

public interface SignUpInterface {

    Customer signUp(Customer customer,CreditCard creditCard);
    boolean signUpValidation(Customer customer, CreditCard creditCard);

}
