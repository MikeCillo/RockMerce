package LogicTier.GestioneAdmin.CustomGuitar;

import LogicTier.Entità.Guitar;
import LogicTier.GestioneGuitars.GuitarService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(value = "/CustomGuitarControl")
public class CustomGuitarControl extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        String address="/PresentationTier/AdminGUI/CustomGuitarGUI/CustomGuitar.jsp";

        int guitarId= Integer.parseInt(request.getParameter("guitarId"));

        GuitarService guitarService=new GuitarService();
        Guitar guitar=guitarService.retrieveGuitarByID(guitarId);

        guitar.setName(request.getParameter("name"));
        guitar.setPrice(Double.parseDouble(request.getParameter("price")));
        guitar.setProducer(request.getParameter("producer"));
        guitar.setCategory(request.getParameter("category"));
        guitar.setDisponibility(Integer.parseInt(request.getParameter("disp")));
        guitar.setSound(request.getParameter("sound"));
        guitar.setDescription(request.getParameter("description"));
        guitar.setColor(request.getParameter("color"));
        guitar.setVisibility(request.getParameter("visibility"));



        if(guitarService.customGuitarValidation(guitar)){
            address="/PresentationTier/AdminGUI/AdminHomepage.jsp";
        }
        else {
            request.setAttribute("guitar", guitar);
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

}
