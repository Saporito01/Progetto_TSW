<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.easygames.model.bean.Account,it.easygames.model.bean.Ordine"%>

<%
ArrayList<String> accountList = (ArrayList<String>) request.getAttribute("accounts");
if(accountList == null) {
	response.sendRedirect("../searchOrder");
	return;
}
%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png" href="./images/logo.png"/>
<style>
table, th, td {
  border:1px solid black;
}
</style>
<title>Ordini</title>
</head>
<body>

<form action="searchOrder" method="get">
<input type="date" name="data1">
<input type="date" name="data2">
<select name="account">
<option value="tutto">Tutti</option>

<%
for(int i=0; i<accountList.size(); i++){
%>
<option value="<%=accountList.get(i)%>"><%=accountList.get(i)%></option>
<%
}
%>
</select>
<input type="submit" value="Cerca">
</form>

<table style="width:100%">
  <tr>
    <th>Codice</th>
    <th>Data</th>
    <th>Account</th>
  </tr>
  <tr>
 <%
Collection<?> ordini = (Collection<?>) request.getAttribute("orderList");
if(ordini != null && ordini.size() > 0) {
	Iterator<?> it = ordini.iterator(); 
	while(it.hasNext()) {
		Ordine item = (Ordine) it.next();
%>
    <td><%=item.getCodice()%></td>
    <td><%=item.getData()%></td>
    <td><%=item.getAccount()%></td>
  </tr>
<%
	}
}
%>
</table>

</body>
</html>