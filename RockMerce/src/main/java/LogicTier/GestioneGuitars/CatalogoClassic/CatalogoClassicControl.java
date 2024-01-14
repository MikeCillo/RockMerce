package LogicTier.GestioneGuitars.CatalogoClassic;


import LogicTier.GestioneGuitars.GuitarService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/CatalogoClassicControl")
public class CatalogoClassicControl extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String address ="/PresentationTier/GuitarsGUI/CatalogoGUI/GuitarShop.jsp";

        GuitarService guitarService=new GuitarService();
        request.setAttribute("guitars",guitarService.retrieveGuitarsByCategory("classic"));

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}
