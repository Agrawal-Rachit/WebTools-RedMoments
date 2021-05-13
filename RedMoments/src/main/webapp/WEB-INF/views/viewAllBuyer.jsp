<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View All Items</title>
<link href="<c:url value="/resources/CSS/mvp.css" />" rel="stylesheet">
</head>
<body>
    <div class="container-fluid" style="width: 1100px;height: 1200px;margin: auto;margin-top: 40px; background-color: #b7b7b7;" align = "center">
<div style="margin-top: 10px; margin-right: 1200px;">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/"><button type="submit" class="btn btn-info" value="Logout">Logout</button></a><br/><br>
	 </div>

          <div style="margin-top: 10px; margin-left: 50px;">
              <a href="${contextPath}/user/buyer"><button type = "submit" class="btn btn-danger" style = "width: 200px;"value="Customer Home">Buyer Home</button></a><br/>
        <br>

        	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>Item Name</b></td>
			<td><b>Item Description</b></td>
			<td><b>Seller Name</b></td>
			<td><b>Category</b></td>
			<td><b>Price</b></td>
                        <td><b>Quantity<b></td>
			<td><b>Options</b></td>
		</tr>



      <c:forEach var="adv" items="${items}">
	<form:form action="${contextPath}/cart/insert/${adv.id}.htm" method="post" commandName="cart">

				<td>${adv.title}</td>
				<td>${adv.message}</td>
				<td>${adv.user.firstName}</td>
				<td><c:forEach var="categ" items="${adv.categories}">
                    	${categ}
                    </c:forEach></td>
 <%--               <td><img height="150" width="150" src="${pageContext.request.contextPath}/${adv.filename}" /></td> --%>
                <td>${adv.price}</td>

                <td><form:select path="quantity" required="required">
                        <%-- <form:option value="NONE" label="--- Select ---" /> --%>
					  <form:options items="${quantity}" />
				      </form:select>
                </td>


<!--                <td><input type="text" name="quantity" value="${cart.quantity}"/></td>-->
                <td><button type = "submit" class="btn btn-info" style = "width: 200px;"value="Add to Cart">Add to Cart</button></td>
			</tr>


	</form:form>
        </c:forEach>
                        </table>
          </div></div>
</body>
</html>