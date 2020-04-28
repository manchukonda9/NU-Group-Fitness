<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NU Login page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
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

		<h2>NU Group Fitness</h2>
	</div>
	<br>
	<br>
	<hr>
	<br>
	<br>
	<div class="container a">
		<h3>Login here</h3>

		<form:form action="processlogin" method="POST">
			<!--  Check for login error -->
			<c:if test="${not empty errormsg}">
				<p style="color: red">Error: ${errormsg}</p>
			</c:if>
			<c:if test="${not empty errormsg2}">
				<p style="color: red">Error: ${errormsg}</p>
			</c:if>
			<br>
			
			<div class="form-group col-lg-6 center">
				<label for="email">Email address</label> <input type="text"
					class="form-control" placeholder="Enter email" name="fEmail">
			</div>
			<div class="form-group col-lg-6 center">
				<label for="pwd">Password</label> <input type="password"
					class="form-control" placeholder="Enter password" name="fPassword">
			</div>
			
		<!-- 	<p>
				Email: <input type="text" name="fEmail" />
			</p>
			<br>
			<p>
				Password: <input type="password" name="fPassword" />
			</p> -->
			<br>

			<a><input class="btn-danger" type="submit" value="Login" /></a>

		</form:form>
		<br> <a><input class="btn-danger" type="button" value="Sign up"
			onclick="window.location.href='showRegistration'; return false;" /></a>
			 
	</div>
</body>
</html>