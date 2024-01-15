package LogicTier.GestioneAdmin.Orders;

import LogicTier.Entità.Checkout;
import LogicTier.GestioneCart.CheckOutService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(value = "/OrdersControl")
public class OrdersControl extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String address = "/PresentationTier/AdminGUI/OrdersGUI/Orders.jsp";

        //GETTING CHECKOUTS
        CheckOutService checkOutService =new CheckOutService();
        ArrayList<Checkout> checkouts=checkOutService.adminOrders();

        //BEFORE SET THE ARRAY CHECK IF THERE'S AT LEAST 1 ELEMENT
        if(checkouts.size()>=1){
            request.setAttribute("checkouts",checkouts);
        }

    RequestDispatcher dispatcher =
            request.getRequestDispatcher(address);
    dispatcher.forward(request, response);
    }

}

