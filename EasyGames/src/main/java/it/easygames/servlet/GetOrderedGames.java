package it.easygames.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.util.List;

import it.easygames.GameControl;
import it.easygames.model.Game;

@WebServlet("/GetOrderedGames")
public class GetOrderedGames extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetOrderedGames() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Game> model = GameControl.loadOrderedGames();
		request.setAttribute("orderedGames", model);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin pages/viewGames.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
