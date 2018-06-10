<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Items to calculate</title>
    <script src='<c:url value="jquery-1.11.3.js"/>'></script>
    <script>
        function addToCart(item_id, btn){
            opr = $(btn).parents("tr").find(".operand").val();
            $.ajax({
                type:"POST",
                url: '<c:url value="/add_to_cart"/>',
                data: {
                    id:item_id,
                    operand:opr
                },
                success: function (msg){
                    $("#itemCnt").text(msg);
                }
            })
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:if test="${not empty param.error}">
    <h2 style="color:red">${param.error}</h2>
</c:if>
<table border="1">
<tr><th>№</th><th>Name</th><th>Count</th><th>Add</th></tr>
<c:forEach items="${tovars}" var="v" varStatus="status">
<tr>
    <td>${status.index+1}</td>
    <td>${v.name}</td>
    <td><input type="text" name="operand" value="" class="operand"></td>
    <td><input type="button" value="add" onclick="addToCart(${v.id}, this)"></td>
</tr>
</c:forEach>
</table>
</body>
</html>