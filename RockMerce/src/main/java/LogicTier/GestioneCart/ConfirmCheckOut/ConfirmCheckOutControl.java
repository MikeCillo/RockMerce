package LogicTier.GestioneCart.ConfirmCheckOut;

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


@WebServlet(value = "/ConfirmCheckOutControl")


public class ConfirmCheckOutControl extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String address = "/PresentationTier/CartGUI/ConfirmCheckout.jsp";


        //GET CUSTOMER FROM SESSION
        HttpSession session=request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        CheckOutService checkOutService=new CheckOutService();
        Checkout checkout=checkOutService.confirmCheckOut(customer);

        request.setAttribute("checkout",checkout);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }
}
