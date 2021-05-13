<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Category</title>
<link
			href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
			crossorigin="anonymous"
		/>

		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
			integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
			crossorigin="anonymous"
		></script>

</head>
<body>
<div class="container-fluid" style="width: 1100px;height: 600px;margin: auto;margin-top: 40px; background-color: #b7b7b7;" align = "center">


	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</br>
        <a href="${contextPath}/user/" style = "margin-left: 30px;"><button class="btn btn-danger btn-lg" type ="button">Seller Home</button></a><br/>
 <div style="margin-top: 50px; margin-left: 50px;">
	<h2>Add a New Category</h2>


	<form:form action="${contextPath}/category/add" method="post" commandName="category">

		<table>
			<tr>
				<td>Category Title:</td>
				<td><form:input path="title" size="30" required="required" /> <font color="red"><form:errors path="title" /></font></td>
			</tr>


		</table>
<button type = "submit" class="btn btn-danger btn-lg" style = "width: 200px;"value="Create Category">Create Category</button></td>
	</form:form>
 </div> </div>
</body>
</html>