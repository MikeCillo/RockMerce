package LogicTier.GestioneAdmin;

import LogicTier.GestioneCart.CheckOutService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value ="/AdminHomepage-Servlet")
public class AdminHomepageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

       String address="/PresentationTier/AdminGUI/AdminHomepage.jsp";

        HttpSession session=request.getSession();
        session.removeAttribute("earnings");


        CheckOutService checkOutService=new CheckOutService();
        session.setAttribute("earnings",checkOutService.adminEarnings());

        RequestDispatcher dispatcher= request.getRequestDispatcher(address);
        dispatcher.forward(request,response);

    }

}