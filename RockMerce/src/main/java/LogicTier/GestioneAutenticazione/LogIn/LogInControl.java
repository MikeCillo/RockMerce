package LogicTier.GestioneAutenticazione.LogIn;

import LogicTier.Entità.Admin;
import LogicTier.Entità.Customer;
import LogicTier.GestioneAutenticazione.AutenticazioneService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(value = "/LogInControl")
public class LogInControl extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        //NEXT JSP IF FIELD ARE INCORRECT
        String address ="/PresentationTier/AutenticazioneGUI/LogInGUI/LogIn.jsp";


        // CHECK IF FIELD ARE EMPTY
        if (request.getParameter("emUs").length() == 0 || request.getParameter("password").length() == 0) {
            request.setAttribute("error", "FILL UP ALL REQUIRED FIELDS");
        }

        //GET EMAIL/USERNAME AND PASSWORD
        else {

            AutenticazioneService autenticazioneService=new AutenticazioneService();
            Customer customer = autenticazioneService.CustomerLogIn(request.getParameter("emUs"), request.getParameter("password"));
            if (customer!=null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("customer", customer);
                address = "index.jsp";
            } else {

                Admin admin = autenticazioneService.AdminLogIn(request.getParameter("emUs"), request.getParameter("password"));
                if (admin!=null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("admin", admin);


                    address = "/PresentationTier/AdminGUI/AdminHomepage.jsp";
                } else {
                    request.setAttribute("emUs", request.getParameter("emUs"));
                    request.setAttribute("password", request.getParameter("password"));
                    request.setAttribute("error", "INVALID EMAIL/USERNAME OR PASSWORD");
                }
            }
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}