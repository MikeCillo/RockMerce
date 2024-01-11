package LogicTier.GestioneCart.CheckOuts;

import LogicTier.Entità.Checkout;
import LogicTier.Entità.Customer;
import LogicTier.GestioneCart.CheckOutService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/CheckoutsControl")
public class CheckOutsControl extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String address = "/PresentationTier/CartGUI/Checkouts.jsp";

        //GET CUSTOMER FROM SESSION
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        //CHECK IF THERE'S THE CUSTOMER IN THE SESSION
        if (customer != null) {

            CheckOutService checkOutService=new CheckOutService();
            ArrayList<Checkout> checkouts= checkOutService.customersCheckouts(customer);

            //BEFORE SET THE ARRAY CHECK IF THERE'S AT LEAST 1 ELEMENT
            if(checkouts.size()>=1){
                request.setAttribute("checkouts",checkouts);

            }

        }
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }

