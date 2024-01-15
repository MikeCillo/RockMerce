package LogicTier.GestioneAdmin;

import LogicTier.Entità.Guitar;
import LogicTier.Utils.GuitarUtils.GuitarService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/AdminGuitars-Servlet")
public class AdminGuitarsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String address ="/PresentationTier/AdminGUI/AdminGuitars.jsp";

        GuitarService guitarService=new GuitarService();
        ArrayList<Guitar> guitars=guitarService.adminRetrieveGuitars();

        request.setAttribute("guitars",guitars);                       //PASSING ARRAYLIST OF GUITARS
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}
