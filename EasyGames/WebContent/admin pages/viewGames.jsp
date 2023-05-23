<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.easygames.model.Game"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png" href="images/logo.png"/>
<title>Gestione giochi</title>
</head>
<body>

<form action="../SearchGameServlet" method="get">
	<select name="piattaforma">
	<option value="tutto">Tutto</option>
	<option value="origin">Origin</option>
	<option value="steam">Steam</option>
	<option value="battle.net">Battle.net</option>
	<option value="ubisoft">Ubisoft</option>
	<option value="xbox one">XBOX One</option>
	<option value="xbox x/s">XBOX Series X/S</option>
	<option value="ps4">PS4</option>
	<option value="ps5">PS5</option>
	<option value="nintendo switch">Nintendo Switch</option>
	<option value="epic games">Epic Games</option>
	</select>
	<input type="text" name="adminSearch">
	<input type="submit" value="Cerca">
</form>

<%
	Collection<?> model = (Collection<?>) request.getAttribute("orderedGames");
	if(model == null) {
		request.getRequestDispatcher("../GetOrderedGames").forward(request, response);
		return;
	}
	
	if(model != null && model.size() > 0) {
		Iterator<?> it = model.iterator(); 
		while(it.hasNext()) {
			Game item = (Game)it.next();
%>

<a href="editGames.jsp"><img src="../GetCoverServlet?id=<%=item.getId()%>" width="350" height="200">
<%=item.getName()%>
</a>


<%
		}
	}
%>
</body>
</html>