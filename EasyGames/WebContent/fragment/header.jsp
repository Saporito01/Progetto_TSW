<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<a href="home_page.jsp"><img src="images/logo.png" width="300" height="98" alt="Logo"></a>

<form action="searchGame" method="get">
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
	<input type="text" name="search">
	<input type="submit" value="Cerca">
</form>

<a href=""><img src="images/profilo.png" width="30" height="30" alt="Account"></a>
<a href=""><img src="images/carrello.png" width="30" height="30" alt="Carrello"></a>
</body>
</html>