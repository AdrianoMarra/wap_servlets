<%--
  Created by IntelliJ IDEA.
  User: 987109
  Date: 6/11/2019
  Time: 2:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Support</title>
</head>
<body>

    <form method="post" action="cstechsupport">
        <label>Name: </label><br />
        <input type="text" name="name" /><br />
        <label>E-mail Address: </label><br />
        <input type="email" name="email" /><br />
        <label>Problem: </label><br />
        <input type="text" name="problem" /><br />
        <label>Problem description:</label><br />
        <textarea cols="80" rows="10"></textarea><br />
        <input type="submit" value="Help" />
    </form>

</body>
</html>
