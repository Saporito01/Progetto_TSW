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
		String piattaforma = (String) request.getParameter("piattaforma");
		String nome = (String) request.getParameter("search");
		String nome1 = (String) request.getParameter("adminSearch");
		
		RequestDispatcher dispatcher;
		
		if(nome!=null)
		{
			List<Game> gameList = SearchGame.searchBarGame(nome, piattaforma);
			
			request.setAttribute("gameSearch", gameList);
			dispatcher = this.getServletContext().getRequestDispatcher("/search.jsp");
		}
		else
		{
			List<Game> gameList = SearchGame.searchBarGame(nome1, piattaforma);
			
			request.setAttribute("gameSearch", gameList);
			dispatcher = this.getServletContext().getRequestDispatcher("/admin pages/searchView.jsp");
		}
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
