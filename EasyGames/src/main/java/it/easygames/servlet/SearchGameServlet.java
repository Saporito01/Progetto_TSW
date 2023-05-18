package it.easygames.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import it.easygames.SearchGame;
import it.easygames.model.Game;

@WebServlet("/SearchGameServlet")
public class SearchGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchGameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("search");
		String piattaforma = (String) request.getParameter("piattaforma");
		
		List<Game> gameList = SearchGame.searchBarGame(nome, piattaforma);
		
		request.setAttribute("gameSearch", gameList);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/search.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
