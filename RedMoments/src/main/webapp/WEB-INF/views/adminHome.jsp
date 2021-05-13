<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
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
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<form:form action="${contextPath}/user/logout" method="post" commandName="item">
    <div style="margin-top: 50px; margin-right: 1200px;"></br>
      <button type="submit" class="btn btn-danger btn-lg" value="Logout">Logout</button>
    </div>
</form:form>
 <div style="margin-top: 10px; margin-left: 50px;">
    <h1>Admin Home </h1><br/><br/>

    <div class="col-sm-12">
    <a href="${contextPath}/admin/viewUsers" ><button type = "button" class="btn btn-danger btn-lg" style = "width: 250px;">View all Users</button></a> <br />
    </div>
    <br/><br/>
    <div class="col-sm-12">
    <a href="${contextPath}/admin/pendingSellers"><button type = "button" class="btn btn-danger btn-lg" style = "width: 250px;">View Pending Sellers</button></a> <br />
    </div>
    <br/><br/>

    <div class="col-sm-12">
    <a href="${contextPath}/admin/rejectedSellers"><button type = "button" class="btn btn-danger btn-lg" style = "width: 250px;">View Rejected Sellers</button></a> <br />
    </div>
    <br/><br/>

    <div class="col-sm-12">
    <a href="${contextPath}/admin/itemsList"><button type = "button" class="btn btn-danger btn-lg" style = "width: 250px;">View All Items</button></a> <br />
    </div>
 </div>
 </div>
</body>
</html>