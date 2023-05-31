package it.easygames.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.easygames.model.bean.Ordine;
import it.easygames.model.dao.OrderControl;


@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			if(request.getParameter("data") != null)
			{
				String data = (String) request.getParameter("data");
				String ora = (String) request.getParameter("ora");
				String account = (String) request.getParameter("account");
				
				Ordine ordine = new Ordine();
				ordine.setData(data);
				ordine.setOra(ora);
				ordine.setAccount(account);
						
				OrderControl.doSave(ordine);

				response.sendRedirect("");
			}
			else
			{
				Collection<Ordine> ordini = OrderControl.load();
				request.setAttribute("ordini", ordini);
				
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
