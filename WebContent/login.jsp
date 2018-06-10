<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <style>.err {color: red} </style>
  <title>Login</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<h1>Input your credentials:</h1>
<div>
  <c:if test="${not empty param.error}">
    <h3 class="err">Invalid login or password!</h3>
  </c:if>
  <form action="<c:url value="/login"/>" method="post">
    <table>
      <tr>
        <td>Login:</td>
        <td><input type="text" name="login"></td>
        <td><span class="err">${errors.login}</span></td>
      </tr>
      <tr>
        <td>Passwrd:</td>
        <td><input type="password" name="pwd"></td>
      </tr>
      <tr>
        <td><input type="reset" value="Reset form"></td>
        <td><input type="submit" value="Login"></td>
      </tr>
    </table>
</div>
</body>
</html>
