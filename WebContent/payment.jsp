<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Selected items (cart)</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<h2>Your items to calculate</h2>
    <table border="1">
      <tr><th>№</th><th>Name</th><th>Count</th></tr>
      <c:forEach items="${cart.items}" var="v" varStatus="status">
        <tr>
          <td>${status.index+1}</td>
          <td>${v.operation.name}</td>
          <td>${v.operand}</td>
        </tr>
      </c:forEach>
    </table>

<div>
  <br>
  Please specify destination address and payment type:
  <br>
  <form action="<c:url value="/payment"/>" method="post">
    <table>
      <tr><td>Delivery address:</td><td><input type="text" name="addr"></td></tr>
      <tr><td>Credit card number:</td><td><input type="text" name="credit_card"></td></tr>
      <tr><td>&nbsp;</td><td><input type="submit" value="Next"></td></tr>
    </table>
  </form>
</div>
</body>
</html>
