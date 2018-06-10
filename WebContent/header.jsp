<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table border="1">
  <tr>
  <td><a href="<c:url value="/items"/>">Items</a></td>
  <td><a href="<c:url value="/cart"/>">Cart [<span id="itemCnt">${cart.count}</span>]</a></td>
  <td>
    <c:choose>
      <c:when test="${empty login}">
        <a href="<c:url value="/register"/>">Register</a> | <a href="<c:url value="/login"/>">Login</a>
      </c:when>
      <c:otherwise>
        Hi, ${login}! <form style="display: inline" action="<c:url value="/logout"/>" method="post"><input type="submit" value="Logout"></form>
      </c:otherwise>
    </c:choose>
  </td></tr>
</table>
