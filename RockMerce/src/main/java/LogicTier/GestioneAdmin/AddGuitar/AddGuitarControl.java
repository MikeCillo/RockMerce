package LogicTier.GestioneAdmin.AddGuitar;

import LogicTier.Entità.Guitar;
import LogicTier.Utils.GuitarUtils.GuitarService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(value = "/AddGuitarControl")

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

public class AddGuitarControl extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String address = "/PresentationTier/AdminGUI/AddGuitarGUI/AddGuitar.jsp";
        Guitar guitar = new Guitar();


        guitar.setImage(request.getParameter("image"));
        guitar.setName(request.getParameter("name"));
        guitar.setProducer(request.getParameter("producer"));
        guitar.setPrice(Double.parseDouble(request.getParameter("price")));
        guitar.setDescription(request.getParameter("description"));
        guitar.setColor(request.getParameter("color"));
        guitar.setDisponibility(Integer.parseInt(request.getParameter("disp")));
        guitar.setSound(request.getParameter("sound"));
        guitar.setCategory(request.getParameter("category"));
        guitar.setVisibility(request.getParameter("visibility"));

            GuitarService guitarService = new GuitarService();
            if (guitarService.addGuitarValidation(guitar)) {
                address = "/PresentationTier/AdminGUI/AdminHomepage.jsp";

            } else {
                request.setAttribute("guitar", guitar);
            }

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(address);
            dispatcher.forward(request, response);


        }

    }
