package LogicTier.GestioneGuitars.GuitarPage;



import LogicTier.GestioneGuitars.GuitarService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/GuitarPageControl")
public class GuitarPageControl extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String address ="/PresentationTier/GuitarsGUI/GuitarPageGUI/GuitarPage.jsp";

        int id= Integer.parseInt(request.getParameter("guitarId"));                  //get id of guitar

        GuitarService guitarService=new GuitarService();
        request.setAttribute("guitar",guitarService.retrieveGuitarByID(id));        //PASSING guitar

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}
