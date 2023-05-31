<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png" href="../images/logo.png"/>
<title>Pagina Admin</title>
</head>
<body>

<form action="../addGame" enctype="multipart/form-data" method="post">

Id: <input type="text" name="id" required><br><br>

Nome: <input type="text" name="nome" required><br><br>

Descrizione: <textarea name="descrizione" required></textarea><br><br>

Piattaforma: <select name="piattaforma" required>
	<option value="">Tutto</option>
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
	</select><br><br>
	
Quantità: <input type="number" name="quantita" min="1" required><br><br>

Prezzo: <input type="text" name="prezzo" required><br><br>

Carica copertina:
<input class="file" type="file" name="copertina" value="" maxlength="255" required><br><br>

<input type="submit" value="Upload">
<input type="reset" value="Reset">

</form>

</body>
</html>