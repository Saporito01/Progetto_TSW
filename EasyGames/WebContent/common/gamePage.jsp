<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.easygames.model.bean.Game"%>
<!DOCTYPE html>

<%
Game game = (Game) request.getAttribute("game");
%>

<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png" href="images/logo.png"/>
<title><%=game.getName()%></title>
</head>
<body>

<%@ include file="/fragment/header.jsp"%>
<br>

<h1><%=game.getName()%></h1>
<img src="getCover?id=<%=game.getId()%>">
<br>
<h2>Prezzo: <%=game.getPrice()%></h2>
<h2>Descrizione:</h2>
<p><%=game.getDesc()%></p>

<br>
<%@ include file="/fragment/footer.jsp"%>

</body>
</html>