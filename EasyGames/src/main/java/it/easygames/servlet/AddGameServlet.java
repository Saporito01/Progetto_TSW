package it.easygames.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.easygames.GameControl;
import it.easygames.model.Game;

@WebServlet("/AddGameServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		//if (name != null && !name.equals("") && surname != null && !surname.equals("")) 
		//{
			Game model = new Game();
			model.setId(id);
			model.setName(nome);
			model.setDesc(descrizione);
			model.setPlatf(piattaforma);
			model.setQt(Integer.valueOf(quantita));
			model.setPrice(Float.valueOf(prezzo));
			
			GameControl.addGame(model);
		//}
			
		for (Part part : request.getParts()) {
			String fileName = part.getSubmittedFileName();
			if (fileName != null && !fileName.equals("")) {
				try {
					GameControl.addCover(id, part.getInputStream());
				} catch (SQLException sqlException) {
					System.out.println(sqlException);
				}
			}
		}
		
		List<Game> gamesList = GameControl.loadGame();
		request.setAttribute("games", gamesList);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_page.jsp");
		dispatcher.forward(request, response);
	}

}
