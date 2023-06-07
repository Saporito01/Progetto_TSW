package it.easygames.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/common/Logout")
public class Logout extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
		if (isAdmin == null){	
		    response.sendRedirect(request.getContextPath() + "/login.jsp"); 
		    return;
		}
		
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/login.jsp");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private static final long serialVersionUID = 1L;
	
}
