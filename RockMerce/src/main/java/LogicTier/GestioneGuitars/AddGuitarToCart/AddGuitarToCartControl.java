package LogicTier.GestioneGuitars.AddGuitarToCart;

import DataTier.RockMerceDAO.Guitar.GuitarDAO;
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

@WebServlet(value = "/AddGuitarToCartControl")
public class AddGuitarToCartControl extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

      String address="index.jsp";

        int guitarId= Integer.parseInt(request.getParameter("guitarId"));
        GuitarDAO guitarDAO=new GuitarDAO();
        Guitar guitarDB=guitarDAO.doRetrieveGuitarById(guitarId);

        //SET THE GUITAR BEFORE PUT IT IN THE CART
        int quantity= Integer.parseInt(request.getParameter("quant"));

        Guitar guitar=new Guitar();
        guitar.setId(guitarDB.getId());
        guitar.setName(guitarDB.getName());
        guitar.setDisponibility(quantity);
        guitar.setPrice(quantity*guitarDB.getPrice());
        guitar.setProducer(guitarDB.getProducer());
        guitar.setCategory(guitarDB.getCategory());
        guitar.setColor(guitarDB.getColor());


        HttpSession session=request.getSession(true);
        Customer customer = (Customer) session.getAttribute("customer");


        //LOGGED CUSTOMER
        if(customer !=null) {
            CartService cartService =new CartService();
            cartService.addGuitarToCart(customer,guitar);
        }


        //GUEST
        else {

            Cart cart= (Cart) session.getAttribute("cart");

            //CART WITH 1 GUITAR AT LEAST
            if(cart!=null){
                cart.addGuitar(guitar);
                session.setAttribute("cart",cart);
            }

            //NEW CART

            else {
                Cart newCart=new Cart();
                newCart.addGuitar(guitar);
                session.setAttribute("cart",newCart);
            }


        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

}