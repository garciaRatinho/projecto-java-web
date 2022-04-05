<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tela de erro</title>
</head>
<body>
<h1>Mensagem de erro, entre em contacto com a equipe do sistema!</h1>
<%
  out.println(request.getAttribute("msg"));
%>
</body>
</html>