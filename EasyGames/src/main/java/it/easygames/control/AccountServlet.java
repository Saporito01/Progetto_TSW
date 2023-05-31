package it.easygames.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

import it.easygames.model.bean.Account;
import it.easygames.model.dao.AccountControl;


@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			if(request.getParameter("nickname") != null)
			{
				String nickname = (String) request.getParameter("nickname");
				String nome = (String) request.getParameter("nome");
				String cognome = (String) request.getParameter("cognome");
				String dataNascita = (String) request.getParameter("dataNascita");
				String email = (String) request.getParameter("email");
				String password = (String) request.getParameter("password");
				
				Account account = new Account();
				account.setNickname(nickname);
				account.setNickname(nome);
				account.setCognome(cognome);
				account.setDataNascita(dataNascita);
				account.setEmail(email);
				account.setPassword(password);
						
				AccountControl.doSave(account);

				response.sendRedirect("");
			}
			else
			{
				Collection<Account> accounts = AccountControl.load();
				request.setAttribute("accounts", accounts);
				
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin/ordersPage.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error:" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

