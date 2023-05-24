package it.easygames.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.easygames.GameDAODriverMan;
import it.easygames.IGameDAO;
import it.easygames.model.Game;

@WebServlet("/GetGameServlet")
public class GetGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static IGameDAO gameDAO = new GameDAODriverMan();
       
    public GetGameServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			Collection<Game> games = gameDAO.doRetrieveAll("nome");
			request.setAttribute("games", games);
		}
		catch(SQLException e)
		{
			System.out.println("Error:" + e.getMessage());
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/home_page.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
