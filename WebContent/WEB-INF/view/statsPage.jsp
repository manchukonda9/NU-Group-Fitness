
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statistics page</title>
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

<table class="table table-striped table-dark table-hover">
				<tr>
					<th>Cult Name</th>
					<th>Time</th>
					<th>Instructor</th>
					<th>Location</th>
					<th>Day</th>
					<th>Duration</th>
					<th>Status</th>
					
				</tr>
		
				<c:forEach var="tempClass" items="${totalCs}">
				
				
					<c:url var="addClassLink" value="/user/registerclass">
						<c:param name="classId" value="${tempClass.id}" />
					</c:url>	
					
	
					
					<tr>
						<td> ${tempClass.name} </td>
						<td> ${tempClass.time} </td>
						<td> ${tempClass.instructor} </td>
						<td> ${tempClass.location} </td>
						<td> ${tempClass.day} </td>
						<td> ${tempClass.duration} </td>
						
						
						<td>
							<!-- display the update link -->
							<p style="color:green">Active</p>
					
						</td>
						
					</tr>
				
				</c:forEach>
						
			</table>

<p>Total number of Active classes  <span style="color:red">${activeC}</span></p>



<table class="table table-striped table-dark table-hover">
				<tr>
					
					<th>First Name</th>
					<th>Last Name</th>
					<th>User Name</th>
					<th>Role</th>
					
					
				</tr>
		
				<c:forEach var="tempStudent" items="${admins}" >
				
				
				
					
	
					
					<tr>
						<td> ${tempStudent.firstName} </td>
						<td> ${tempStudent.lastName} </td>
						<td> ${tempStudent.userName} </td>
						<td Style="color:green"> Admin </td>
		
						
						
					
						
					</tr>
				
				</c:forEach>
						
			</table>
			<br>
			<br>
			
			<table class="table table-striped table-dark table-hover">
				<tr>
					
					<th>First Name</th>
					<th>Last Name</th>
					<th>User Name</th>
					<th>Role</th>
					
					
				</tr>
		
				<c:forEach var="tempStudent" items="${studs}" >
				
				
				
					
	
					
					<tr>
						<td> ${tempStudent.firstName} </td>
						<td> ${tempStudent.lastName} </td>
						<td> ${tempStudent.userName} </td>
						<td Style="color:green"> Student </td>
		
						
						
					
						
					</tr>
				
				</c:forEach>
						
			</table>
			<p>Total number of Active Users  <span style="color:red">${activeU}</span></p>
			
			<br>
			<br>
			<br>
			<a href="${pageContext.request.contextPath}/login">Back
				to home</a>
</body>
</html>