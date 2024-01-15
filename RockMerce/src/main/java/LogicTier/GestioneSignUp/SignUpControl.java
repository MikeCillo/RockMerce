package LogicTier.GestioneSignUp;

import LogicTier.Entità.CreditCard;
import LogicTier.Entità.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/SignUpControl")
public class SignUpControl extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String address ="/PresentationTier/SignUpGUI/SignUp.jsp";  //if validation is not completed return to caller jsp
        SignUpService signUpService=new SignUpService();


        Customer customer =new Customer();
        customer.setName(request.getParameter("name"));
        customer.setSurname(request.getParameter("surname"));
        customer.setUsername(request.getParameter("username"));
        customer.setEmail(request.getParameter("email"));
        customer.setPassword(request.getParameter("password"));
        customer.setPhone(request.getParameter("phone"));
        customer.setCity(request.getParameter("city"));
        customer.setCountry(request.getParameter("country"));
        customer.setAddress(request.getParameter("address"));

        CreditCard creditCard=new CreditCard();
        creditCard.setCardNumber(request.getParameter("card"));
        creditCard.setOwner(request.getParameter("owner"));
        creditCard.setExpireDate(request.getParameter("expMonth")+"/"+request.getParameter("expYear"));
        creditCard.setCvv(Integer.parseInt(request.getParameter("cvv")));



        if(signUpService.signUpValidation(customer,creditCard)){
            HttpSession session=request.getSession(true);
            customer= signUpService.signUp(customer,creditCard);
            session.setAttribute("customer", customer);
            address="/PresentationTier/AutenticazioneGUI/LogInGUI/LogIn.jsp";

        }else {
            request.setAttribute("customer", customer);
            request.setAttribute("creditCard",creditCard);
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

}
