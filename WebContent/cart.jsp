<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Selected items (cart)</title>
</head>
<body>
<jsp:include page="header.jsp"/>

  <c:choose>
     <c:when test="${cart.count eq 0}">
       <h2>Cart is empty</h2>
     </c:when>
     <c:otherwise>
      <table border="1">
      <tr><th>№</th><th>Name</th><th>Count</th><th>.</th></tr>
      <c:forEach items="${cart.items}" var="v" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${v.operation.name}</td>
            <td>${v.operand}</td>
            <td>
              <form action="<c:url value="/remove_from_cart?position=${status.index}"/>" method="post">
                <input type="submit" value="remove">
              </form>
            </td>
        </tr>
      </c:forEach>
      </table>
     </c:otherwise>
  </c:choose>

<div>
<c:if test="${cart.count ne 0}">
  <a href="<c:url value="/payment"/>">Next ...</a>
</c:if>  
</div>
</body>
</html>