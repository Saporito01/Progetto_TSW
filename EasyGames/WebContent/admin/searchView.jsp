<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.easygames.model.bean.Game"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png" href="../images/logo.png"/>
<title>Risultati della ricerca</title>
</head>
<body>

<div>
<%
Collection<?> model = (Collection<?>) request.getAttribute("gameSearch");
	if(model != null && model.size() > 0) {
		Iterator<?> it = model.iterator(); 
		while(it.hasNext()) {
			Game item = (Game)it.next();
%>

<a href="getGame?id=<%=item.getId()%>"><img src="getCover?id=<%=item.getId()%>" width="350" height="200">
<%=item.getName()%>
</a>

<%
		}
	}
%>
</div>

</body>
</html>