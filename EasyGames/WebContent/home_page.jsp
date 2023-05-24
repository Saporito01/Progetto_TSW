<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.easygames.model.Game"%>
<%
Collection<?> model = (Collection<?>) request.getAttribute("games");
if(model == null) {
	response.sendRedirect("./GetGameServlet");	
	
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png" href="images/logo.png"/>
<title>Easy Games</title>
</head>
<body>


<%@include file="fragment/header.jsp" %>


<main>
<div>
<h2>I più venduti</h2>
<%
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

</main>


<%@include file="fragment/footer.jsp" %>


</body>
</html>
<!-- request.getRequestDispatcher("./GetGameServlet").forward(request, response); -->