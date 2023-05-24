package it.easygames.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.util.Collection;

import it.easygames.GameDAODriverMan;
import it.easygames.IGameDAO;
import it.easygames.model.Game;

@WebServlet("/GetOrderedGames")
public class GetOrderedGames extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static IGameDAO gameDAO = new GameDAODriverMan();
       
    public GetOrderedGames() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			Collection<Game> games = gameDAO.doRetrieveAll("nome");
			request.setAttribute("orderedGames", games);
		}
		catch(SQLException e)
		{
			System.out.println("Error:" + e.getMessage());
		}
		
		
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin/viewGames.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
