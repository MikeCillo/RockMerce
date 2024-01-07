package LogicTier.Utils.AutenticazioneUtils;

import LogicTier.Entità.Admin;
import LogicTier.Entità.Customer;

public interface AutenticazioneInterface {


    boolean CheckUsername(String us);

    boolean CheckEmail(String email);

    Customer CustomerLogIn(String emUs, String password);

    Admin AdminLogIn(String emUs, String password);

}
