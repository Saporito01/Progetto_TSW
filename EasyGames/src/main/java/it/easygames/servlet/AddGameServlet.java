package it.easygames.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.easygames.GameDAODriverMan;
import it.easygames.IGameDAO;
import it.easygames.CoverControl;
import it.easygames.model.Game;

@WebServlet("/AddGameServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static IGameDAO gameDAO = new GameDAODriverMan();

    public AddGameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		String nome = (String) request.getParameter("nome");
		String descrizione = (String) request.getParameter("descrizione");
		String piattaforma = (String) request.getParameter("piattaforma");
		String quantita = (String) request.getParameter("quantita");
		String prezzo = (String) request.getParameter("prezzo");
		
		try
		{
			//if (name != null && !name.equals("") && surname != null && !surname.equals("")) 
			//{
				Game model = new Game();
				model.setId(id);
				model.setName(nome);
				model.setDesc(descrizione);
				model.setPlatf(piattaforma);
				model.setQt(Integer.valueOf(quantita));
				model.setPrice(Float.valueOf(prezzo));
				
				gameDAO.doSave(model);
			//}
				
			for (Part part : request.getParts()) {
				String fileName = part.getSubmittedFileName();
				if (fileName != null && !fileName.equals(""))
						CoverControl.updateCover(id, part.getInputStream());
			}
			
			Collection<Game> games = gameDAO.doRetrieveAll("nome");
			request.setAttribute("games", games);
		}
		catch(SQLException e)
		{
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin pages/addGamePage.jsp");
		dispatcher.forward(request, response);
	}

}

/*
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		String nome = (String) request.getParameter("nome");
		String descrizione = (String) request.getParameter("descrizione");
		String piattaforma = (String) request.getParameter("piattaforma");
		String quantita = (String) request.getParameter("quantita");
		String prezzo = (String) request.getParameter("prezzo");
		//if (name != null && !name.equals("") && surname != null && !surname.equals("")) 
		//{
			Game model = new Game();
			model.setId(id);
			model.setName(nome);
			model.setDesc(descrizione);
			model.setPlatf(piattaforma);
			model.setQt(Integer.valueOf(quantita));
			model.setPrice(Float.valueOf(prezzo));
			
			gameDAO.doSave(model);
		//}
			
		for (Part part : request.getParts()) {
			String fileName = part.getSubmittedFileName();
			if (fileName != null && !fileName.equals("")) {
				try {
					CoverControl.updateCover(id, part.getInputStream());
				} catch (SQLException sqlException) {
					System.out.println(sqlException);
				}
			}
		}
		
		Collection<Game> games = gameDAO.doRetrieveAll("nome");
		request.setAttribute("games", games);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin pages/addGamePage.jsp");
		dispatcher.forward(request, response);
	}
 **/
