<%--
  Created by IntelliJ IDEA.
  User: Kramer
  Date: 12/06/2019
  Time: 07:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form method="post" action="login">
  <label>Username: </label><br />
  <input type="text" name="username" value="${cookie.user.value}" /><br />
  <label>Password: </label><br />
  <input type="password" name="pwd" /><br />
  <label><input type="checkbox" name="remember" ${cookie.user.value != null ? "checked" : null}> Remember me</label>
  <br />
  <br />
  <input type="submit" value="Login" />
</form>
</body>
</html>
