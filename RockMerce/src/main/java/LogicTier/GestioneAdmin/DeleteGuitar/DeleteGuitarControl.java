package LogicTier.GestioneAdmin.DeleteGuitar;

import LogicTier.Entità.Guitar;
import LogicTier.Utils.GuitarUtils.GuitarService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(value = "/DeleteGuitarControl")
public class DeleteGuitarControl extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String address="/PresentationTier/AdminGUI/DeleteGuitarGUI/DeleteGuitarChoose.jsp";
        int idGuitar= Integer.parseInt(request.getParameter("guitarId"));

        if(idGuitar>=0) {
            GuitarService guitarService=new GuitarService();
            Guitar guitar = guitarService.retrieveGuitarByID(idGuitar);

            if(guitar!=null){
                guitarService.deleteGuitar(guitar);
                address="/PresentationTier/AdminGUI/AdminHomepage.jsp";
            }

        }

        RequestDispatcher dispatcher= request.getRequestDispatcher(address);
        dispatcher.forward(request,response);

    }
}
