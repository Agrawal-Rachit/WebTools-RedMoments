<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of All Users</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
			crossorigin="anonymous"/>

		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
			integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
			crossorigin="anonymous"
		></script>
</head>
<body>
<div class="container-fluid" style="width: 1100px;height: 600px;margin: auto;margin-top: 40px; background-color: #b7b7b7;" align = "center">
    <div style="margin-top: 50px; margin-right: 1200px;">
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
 <a href="${contextPath}/"><button type="submit" class="btn btn-danger btn-lg" value="Logout">Logout</button></a><br/><br>
</div>

<div style="margin-top: 10px; margin-right: 50px;">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<a href="${contextPath}/admin/"><button type = "button" class="btn btn-danger btn-lg" style = "width:200px;">Admin Home</button></a><br/>
	<br>


	<table border="1" cellpadding="2" cellspacing="2">
		<tr>
			<td><b>First Name</b></td>
			<td><b>Last Name</b></td>
			<td><b>UserName</b></td>
			<td><b>Email</b></td>
			<td><b>Type</b></td>
			<td><b>Status</b></td>
                        <td><b>Option</b></td>

		</tr>



		<c:forEach var="user" items="${users}">
                    <form action="${contextPath}/admin/remove/${user.personID}.htm" method="GET">
			<tr>

                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.username}</td>
                            <td>${user.email.emailAddress}</td>
                            <td>${user.usertype}</td>
                            <td>${user.active}</td>
                            <td><button value="remove" class="btn btn-danger btn-lg" type="submit">Remove</button></td>
			</tr>
                        </form>

		</c:forEach>
	</table>
</div>
</div>
</body>
</html>