<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.easygames.model.Game"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png" href="images/logo.png"/>
<title>Easy Games</title>
</head>
<body>


<%@include file="header.jsp" %>


<main>
<div>
<h2>I più venduti</h2>
<%
	Collection<?> model = (Collection<?>) request.getAttribute("game");
	if(model == null) {
		request.getRequestDispatcher("./GetGameServlet").forward(request, response);
		return;
	}
	
	if(model != null && model.size() > 0) {
		Iterator<?> it = model.iterator(); 
		while(it.hasNext()) {
			Game item = (Game)it.next();
%>

<a href="">
<img src="./GetCoverServlet?id=<%=item.getId()%>" width="350" height="200">
<%=item.getName()%>
</a>

<%
		}
	}
%>
</div>

<p>Paragrafo descrittivo per il sito</p>

<div>
<h2>Categorie</h2>
<a href=""><button>Vedi tutti</button></a>
<a href=""><img src="" alt="Avventura"></a>
<a href=""><img src="" alt="Azione"></a>
<a href=""><img src="" alt="FPS"></a>
<a href=""><img src="" alt="Strategia"></a>
<a href=""><img src="" alt="Giocatore Singolo"></a>
<a href=""><img src="" alt="Sportivo"></a>
</div>

<a href="./admin_page.jsp">Add Game</a>
</main>


<%@include file="footer.jsp" %>


</body>
</html>