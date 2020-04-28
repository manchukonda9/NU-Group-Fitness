<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>waitlist</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style>
div.a {
	text-align: center;
}
.center {
  margin: auto;
  width: 50%;

  padding: 10px;
}

a:link, a:visited {
	background-color: #f44336;
	color: white;
	padding: 14px 25px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
}


a:hover, a:active {
	background-color: red;
}
</style>
</head>
<body class="container p-3 my-3 bg-dark text-white">
	<div class="a">

		<h2 >NU Group Fitness</h2>
	</div>
	<br>
	<br>
	<hr>
	<h2>User waitlist</h2>
<table class="table table-striped table-dark table-hover">
				<tr>
					
					<th>First Name</th>
					<th>Last Name</th>
					<th>User Name</th>
					<th>Action</th>
					
					
				</tr>
		
					<c:forEach var="tempStudent" items="${waitlisted}">
				
				
					<c:url var="addClassLink" value="/admin/enableuser">
						<c:param name="userId" value="${tempStudent.id}" />
					</c:url>
				
				
				
					
	
					
					<tr>
						<td> ${tempStudent.firstName} </td>
						<td> ${tempStudent.lastName} </td>
						<td> ${tempStudent.userName} </td>
						<td Style="color:blue"> <a href="${addClassLink}">Register</a> </td>
		
						
						
					
						
					</tr>
				
				</c:forEach>
						
			</table>
			<a href="${pageContext.request.contextPath}/login">Back
				to home</a>
</body>
</html>