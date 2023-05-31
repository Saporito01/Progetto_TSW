<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.easygames.model.bean.Account,it.easygames.model.bean.Ordine"%>

<%
Collection<?> accountList = (Collection<?>) request.getAttribute("accounts");
if(accountList == null) {
	request.getRequestDispatcher("/account").forward(request, response);
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png" href="../images/logo.png"/>
<style>
table, th, td {
  border:1px solid black;
}
</style>
<title>Ordini</title>
</head>
<body>

<form action="../searchOrder" method="get">
<input type="date" name="data1">
<input type="date" name="data2">
<select name="account">
<option value="tutto">Tutti</option>
<%
if(accountList != null && accountList.size() > 0) {
		Iterator<?> it = accountList.iterator(); 
		while(it.hasNext()) {
			Account item = (Account)it.next();
%>
<option value="<%=item.getNickname()%>"><%=item.getNickname()%></option>
<%
		}
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
<%
Collection<?> ordini = (Collection<?>) request.getAttribute("ordini");
if(ordini != null && ordini.size() > 0) {
		Iterator<?> it = ordini.iterator(); 
		while(it.hasNext()) {
			Ordine item = (Ordine) it.next();
%>
  <tr>
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