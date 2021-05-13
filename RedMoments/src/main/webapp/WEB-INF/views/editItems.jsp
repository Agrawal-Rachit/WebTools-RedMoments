<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Items</title>
 <link href="<c:url value="/resources/CSS/mvp.css" />" rel="stylesheet">
</head>
<body>
<div class="container-fluid" style="width: 1100px;height: 1200px;margin: auto;margin-top: 40px; background-color: #b7b7b7;" align = "center">
    <div style="margin-top: 10px; margin-right: 1200px;">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

        <a href="${contextPath}/"><button type="submit" class="btn btn-info" value="Logout">Logout</button></a><br/><br>
        </div>
   <div style="margin-top: 10px; margin-left: 50px;">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<a href="${contextPath}/user/"><button type ="button">Seller Home</button></a><br/>
	<br>


	<table border="1" cellpadding="2" cellspacing="2">
		<tr>
			<td><b>Item Name</b></td>
			<td><b>Item Description</b></td>
			<td><b>Seller Name</b></td>
			<td><b>Category</b></td>
			<td><b>Price</b></td>
                        <td><b>Save</b></td>

		</tr>



		<c:forEach var="adv" items="${items}">
                    <form action="${contextPath}/item/update/${adv.id}.htm" method="POST">
			<tr>
                           <td><input type="text" name="title" value="${adv.title}" required="required"/></td>
                            <td><input type="text" name="description" value="${adv.message}" required="required"/></td>
                            <td>${adv.user.username}</td>
                            <td><c:forEach var="categ" items="${adv.categories}">
                    	    ${categ}
                            </c:forEach></td>
 <%--                               <td><img height="150" width="150" src="${pageContext.request.contextPath}/${adv.filename}"/></td> --%>
                            <td><input type="text" name="price" required="required" value= "${adv.price}"/></td>
                            <td><button value="edit" type="submit">Save</button></td>
                            </tr>
                        </form>

		</c:forEach>
	</table>
   </div>   </div>
</body>
</html>