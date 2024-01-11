package LogicTier.GestioneCart.RemoveGuitar;


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

@WebServlet(value = "/RemoveGuitarControl")

public class RemoveGuitarControl extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String address ="/PresentationTier/CartGUI/CartPageGUI/Cart.jsp";

        int id = Integer.parseInt(request.getParameter("idGuitar"));

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

         if(customer !=null){
             CartService cartService =new CartService();
             Cart cart= cartService.removeGuitarFromCart(customer,id);

             request.setAttribute("guitars", cart.getGuitars());
             request.setAttribute("cart", cart);
           }
        else {
            Cart cart= (Cart) session.getAttribute("cart");

                if(cart.getNumGuitars()>=1){
                    cart.removeGuitar(id);
                    request.setAttribute("guitars",cart.getGuitars());
                    request.setAttribute("cart",cart);
                }
            }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
        }

}
