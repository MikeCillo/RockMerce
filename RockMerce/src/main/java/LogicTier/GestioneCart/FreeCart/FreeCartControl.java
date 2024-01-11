package LogicTier.GestioneCart.FreeCart;

import LogicTier.Entità.Cart;
import LogicTier.Entità.Customer;
import LogicTier.Entità.Guitar;
import LogicTier.GestioneCart.CartService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/FreeCartControl")

public class FreeCartControl extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String address = "/PresentationTier/CartGUI/CartPageGUI/Cart.jsp";


        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer != null) {
            CartService cartService =new CartService();
            Cart cart= cartService.freeCart(customer);

            request.setAttribute("guitars", cart.getGuitars());
            request.setAttribute("cart", cart);

        } else {
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart.getNumGuitars() >= 1) {
                ArrayList<Guitar> guitars=cart.getGuitars();
                guitars.clear();
                cart.setNumGuitars(0);
                cart.setTempTotal(0.00);
                }
                request.setAttribute("guitars", cart.getGuitars());
                request.setAttribute("cart", cart);
            }

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }

    }

