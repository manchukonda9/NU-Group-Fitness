<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Class list</title>
<%-- <link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" /> --%>
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
	<h2>Available Class details</h2>
		<c:if test="${not empty qmsg}">
   		<p style="color:green">${qmsg}</p>
		</c:if>
			<c:if test="${not empty emsg}">
   		<p style="color:red">${emsg}</p>
		</c:if>
	<!--  html table here -->
	<table class="table table-striped table-dark table-hover">
	<thead style="color:white">
				<tr >
					<th>Cult Name</th>
					<th>Time</th>
					<th>Instructor</th>
					<th>Location</th>
					<th>Day</th>
					<th>Duration</th>
					<th>Capacity</th>
					<th>action</th>
					
				</tr>
		</thead>
				<c:forEach var="tempClass" items="${classes}">
				
				
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
						<td> ${tempClass.capacity} </td>
						
						
						<td>
							<!-- display the update link -->
							<a href="${addClassLink}">Register</a>
					
						</td>
						
					</tr>
				
				</c:forEach>
						
			</table>
			
						<a href="${pageContext.request.contextPath}/login">Back
				to home</a>
				
</body>
</html>