<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Items</title>
<link href="<c:url value="/resources/CSS/mvp.css" />" rel="stylesheet">
</head>
<body>
<div class="container-fluid" style="width: 1100px;height: 1200px;margin: auto;margin-top: 40px; background-color: #b7b7b7;" align = "center">
<div style="margin-top: 10px; margin-right: 1200px;">
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
 <a href="${contextPath}/"><button type="submit" class="btn btn-danger" value="Logout">Logout</button></a><br/><br>
</div>

        <div style="margin-top: 10px; margin-left: 50px;">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<a href="${contextPath}/user/"><button type = "button" style = "width:200px;">Seller Home</button></a><br/>
	<br>


	<table border="1" cellpadding="2" cellspacing="2">
		<tr>
			<td><b>Item Name</b></td>
			<td><b>Item Description</b></td>
			<td><b>Seller Name</b></td>
			<td><b>Category</b></td>

			<td><b>Cost</b></td>
                        <td><b>Edit</b></td>
                        <td><b>Delete</b></td>

		</tr>



		<c:forEach var="adv" items="${items}">
                    <form action="${contextPath}/item/edit" method="get">
			<tr>

                            <td>${adv.title}</td>
                            <td>${adv.message}</td>
                            <td>${adv.user.firstName}</td>
                            <td><c:forEach var="categ" items="${adv.categories}">
                    	    ${categ}
                            </c:forEach></td>

                         <td>${adv.price}</td>
                            <td><button value="edit" type="submit">Edit</button></td>
                            <td><a href="${contextPath}/item/remove/${adv.id}.htm" ><button type="button">Remove</button></a></td>
			</tr>
                        </form>

		</c:forEach>
	</table>
        </div>
</div>
</body>
</html>