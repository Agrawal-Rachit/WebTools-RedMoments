<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Item Form</title>
 <link href="<c:url value="/resources/CSS/mvp.css" />" rel="stylesheet">
</head>
<body>
 <div class="container-fluid" style="width: 1100px;height: 1000px;margin: auto;margin-top: 40px; background-color: #b7b7b7;" align = "center">
     <div style="margin-top: 50px; margin-left: 50px;">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

        <a href="${contextPath}/user/"><button type = "button" class="btn btn-danger" style = "width: 200px;">Seller Home</button></a><br/>

	<h2>Add new Item</h2>


	<form:form action="${contextPath}/item/add" method="post"
		commandName="item" enctype="multipart/form-data">

		<table>
			<tr>
				<td>Seller Name:</td>
				<td>${sessionScope.user.firstName}</td>
				<td><form:hidden path="postedBy"
						value="${sessionScope.user.personID}" /></td>
			</tr>

			<tr>
				<td>Select a Category:</td>
				<td><form:select path="categories" items="${categories}"
						multiple="true" required="required" /></td>
			</tr>

			<tr>
				<td>Item Name:</td>
				<td><form:input type="text" path="title" size="30" required="required" name = "title"/>
				<font color="red"><form:errors path="title" /></font></td>
			</tr>



			<tr>
				<td>Item Description:</td>
				<td><form:input type="text" path="message" size="30" required="required" name = "message"/>
				<font color="red"><form:errors path="message" /></font></td>
			</tr>

			<tr>
				<td>Item Cost</td>
				<td><form:input type="number" path="price" size="30" required="required"/>
				<font color="red"><form:errors path="price" /></font></td>
			</tr>

			<tr>
                            <td colspan="2"><button type = "submit" class="btn btn-danger" style = "width: 200px;"value="Post Item">Add Item</button></td>
			</tr>
		</table>

	</form:form>
     </div>   </div>
</body>
</html>