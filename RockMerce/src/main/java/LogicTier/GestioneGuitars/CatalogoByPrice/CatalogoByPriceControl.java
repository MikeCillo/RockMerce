package LogicTier.GestioneGuitars.CatalogoByPrice;

import LogicTier.Entità.Guitar;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@WebServlet(value ="/CatalogoByPriceControl")

public class CatalogoByPriceControl extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String address ="/PresentationTier/GuitarsGUI/CatalogoGUI/GuitarShop.jsp";

        String select = request.getParameter("orderBy");
        HttpSession session=request.getSession();
        ArrayList<Guitar> guitars = (ArrayList<Guitar>) session.getAttribute("guitars");

        session.removeAttribute("guitars");

        if (guitars != null) {          //there's at least one Guitar

            if (select.equals("MAX PRICE")) {
                List<Guitar> products = guitars;
                products.sort((a, b) -> (int) (a.getPrice() - b.getPrice()));
                Collections.reverse(products);
            }
            else   if (select.equals("MIN PRICE")){
                List<Guitar> products = guitars;
                products.sort((a, b) -> (int) (a.getPrice() - b.getPrice()));
            }

            request.setAttribute("guitars",guitars);
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
