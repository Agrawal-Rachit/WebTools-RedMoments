<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign Up Form</title>
 <link href="<c:url value="/resources/CSS/mvp.css" />" rel="stylesheet">
</head>
<body>

      <div class="container-fluid" style="width: 1100px;height: 1200px;margin: auto;margin-top: 40px; background-color: #b7b7b7;" align = "center">
      <div style="margin-top: 10px; margin-right: 1200px;">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
       <a href="${contextPath}"><button type = "submit" class="btn btn-danger" style = "width: 200px;"value="Go Back">Go Back</button></a><br/>
</div></br>
	<h2>Enter your details to Sign Up</h2><br>

	<form:form action="${contextPath}/user/register" commandName="user"
		method="post">
		<table>
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" required="required" />
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" required="required" />
					<font color="red"><form:errors path="lastName" /></font></td>
			</tr>

			<tr>
				<td>User Name:</td>
				<td><form:input path="username" size="30" required="required" />
					<font color="red"><form:errors path="username" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"
						required="required" /> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td><form:input path="email.emailAddress" size="30"
						type="email" required="required" /> <font color="red"><form:errors
							path="email.emailAddress" /></font></td>
			</tr>

			<tr>
				<td>User Type:</td>
				<td><form:select path="usertype" required="required">
					  <form:option value="NONE" label="--- Select ---" />
					  <form:options items="${usertype}" />
				      </form:select>
					  <font color="red"><form:errors path="usertype" /></font></td>
			</tr>

			<tr>
                             <td colspan="2"><button type = "submit" class="btn btn-info" style = "width: 200px;"value="Register User">Register User</button></td>
			</tr>
		</table>
	</form:form>
    </div>
</body>
</html>