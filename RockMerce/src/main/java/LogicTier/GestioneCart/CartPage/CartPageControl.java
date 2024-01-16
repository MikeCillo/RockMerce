package LogicTier.GestioneCart.CartPage;

import LogicTier.Entità.Cart;
import LogicTier.Entità.Customer;
import LogicTier.GestioneCart.CartService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(value = "/CartPageControl")

public class CartPageControl extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String address ="/PresentationTier/CartGUI/CartPageGUI/Cart.jsp";          //CART PAGE

        HttpSession session=request.getSession();                                //GET CUSTMER'SESSION
        Customer customer = (Customer) session.getAttribute("customer");     //GET THE CUSTOMER FROM THE SESSION
        session.removeAttribute("guitars");


        if(customer!=null){                                          //CUSTOMER
            CartService cartService =new CartService();
            Cart cart= cartService.retrieveCustomerCart(customer);

            request.setAttribute("guitars", cart.getGuitars());
            request.setAttribute("cart",cart);

        }

        else {                                                          // GUEST

            Cart guestCart= (Cart) session.getAttribute("cart");


            if(guestCart!=null){                                    // WITH A CART
                request.setAttribute("guitars",guestCart.getGuitars());
            }


            else {                                              // WITHOUT A CART
                Cart cart=new Cart();
                session.setAttribute("cart",cart);
                request.setAttribute("guitars",cart.getGuitars());
            }

        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }
}
