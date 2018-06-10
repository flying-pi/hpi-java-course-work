<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Review order</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<h1>Your order <b>${order.orderNumber}</b> has been started to process!</h1>
<h2>Your items to calculate</h2>
<table border="1">
  <tr><th>№</th><th>Name</th><th>Count</th></tr>
  <c:forEach items="${order.items}" var="v" varStatus="status">
    <tr>
      <td>${status.index+1}</td>
      <td>${v.operation.name}</td>
      <td>${v.operand}</td>
    </tr>
  </c:forEach>
</table>

<div>
  <br>
  Billing information:
  <br>
    <table>
      <tr><td>Delivery address:</td><td>${order.address}</td></tr>
      <tr><td>Credit card number:</td><td>${order.card}</td></tr>
    </table>
</div>
</body>
</html>
