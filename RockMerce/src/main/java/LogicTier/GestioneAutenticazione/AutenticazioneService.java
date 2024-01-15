package LogicTier.GestioneAutenticazione;

import DataTier.RockMerceDAO.Admin.AdminDAO;
import DataTier.RockMerceDAO.Customer.CustomerDAO;
import LogicTier.Entità.Admin;
import LogicTier.Entità.Customer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AutenticazioneService implements AutenticazioneInterface {


    private CustomerDAO customerDAO=new CustomerDAO();

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    private static final Logger logger = Logger.getLogger(AutenticazioneService.class.getName());


    @Override
    public boolean CheckUsername(String us){
        CustomerDAO customerDAO=new CustomerDAO();
        if(customerDAO.doCheckUsername(us)) {
            return true;
        }else {
            return false;
        }

    }
    @Override
    public boolean CheckEmail(String email){
        if(this.customerDAO.doCheckEmail(email)) {
            return true;
        }else {
            this.logger.log(Level.SEVERE, "ERROR: EMAIL NOT AVAILABLE");
            return false;
        }

    }
    @Override
    public Customer CustomerLogIn(String emUs, String password) {
        if(!customerDAO.doCheckEmail(emUs) && !customerDAO.doCheckUsername(emUs)){
            logger.log(Level.SEVERE, "ERROR: EMAIL OR USERNAME ");
            return null;
        }
        Customer customer=customerDAO.doCheckLogin(emUs, password);
        if (customer!=null){
            return customer;
        }
        else {
            logger.log(Level.SEVERE, "ERROR: PASSWORD ");
            return null;
        }
    }

    @Override
    public Admin AdminLogIn(String emUs, String password) {
        //CHECK IF ADMIN IS IN DB
        AdminDAO adminDAO = new AdminDAO();
        return adminDAO.checkAdminLogin(emUs, password);

    }

}
