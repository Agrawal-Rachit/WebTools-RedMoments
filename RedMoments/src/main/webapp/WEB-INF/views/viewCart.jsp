<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Cart</title>
<link href="<c:url value="/resources/CSS/mvp.css" />" rel="stylesheet">
</head>
<body>

   <div class="container-fluid" style="width: 1100px;height: 1200px;margin: auto;margin-top: 40px; background-color: #b7b7b7;" align = "center">
   <div style="margin-top: 10px; margin-right: 1200px;">

    	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

        <a href="${contextPath}/"><button type="submit" class="btn btn-danger" value="Logout">Logout</button></a><br/><br>
    </div>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<a href="${contextPath}/user/buyer"><button type = "button" style = "width:200px;">Customer Home</button></a><br/>
        <br>

 <form:form action="${contextPath}/cart/checkout" method="post" commandName="cart">
      	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>Item Name</b></td>
			<td><b>Cost</b></td>
                        <td><b>Quantity</b></td>
                        <td><b>Final Cost</b></td>
                        <td><b>Delete</b></td>
                        <td><b>Edit</b></td>

		</tr>

            <c:forEach var="c" items="${carts}">
                         <tr>
		<td>${c.title}</td>
<%--                <td><img height="150" width="150" src="${pageContext.request.contextPath}/${c.filename}"/></td> --%>
                <td>${c.totalprice}</td>
               <td>${c.quantity}</td>
                <%-- <td><input type="number" name="quantity" value= "${c.quantity}"/></td>--%>
                <td>${c.totalprice*c.quantity}</td>
                <td><a href="${contextPath}/cart/remove/${c.id}.htm"><button type="button">Remove</button></a></td>
                <td><a href="${contextPath}/cart/edit" ><button type="button">Edit</button></a></td>
			</tr>
               </c:forEach>

	</table>
        <button type = "submit" style = "width:200px;"value="Checkout Items">Purchase</button>
	</form:form>
           </div>
</body>
</html>