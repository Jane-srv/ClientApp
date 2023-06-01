<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Client App</title>
</head>
<body>
<center>
<h2><%= "Добро пожаловать!" %></h2>
<br>
<form action="HelloServlet" method="post">
  <input type="submit" value="Добавить нового клиента">
</form><br>
<form action="HelloServlet" method="get">
  <input type="submit" value="Просмотреть всех клиентов">
  </form></td>
</center>
</body>
</html>