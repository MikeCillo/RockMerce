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

@WebServlet(value = "/AdminGuitarPage-Servlet")
public class AdminGuitarPageServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String address ="/PresentationTier/AdminGUI/AdminGuitarPage.jsp";

        int id= Integer.parseInt(request.getParameter("guitarId"));                     //get id of guitar

        GuitarService guitarService=new GuitarService();
        Guitar guitar=guitarService.retrieveGuitarByID(id);


        request.setAttribute("guitar",guitar);                                    //PASSING guitar

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}
