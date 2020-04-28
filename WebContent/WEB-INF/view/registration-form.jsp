<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration form</title>
<style>
		.error {color:red}
	</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
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
<h1 >NU Group Fitness</h1>
</div>
<br>
<br>
<hr>
<h2>Registration Page</h2>
<br>
<br>
<div style="width: 600px;">
	<form:form action="saveUser" method="POST">
		<!--  Check for login error -->
		<c:if test="${not empty errormsg}">
   		<p style="color:red">	Error: ${errormsg}</p>
		</c:if>
		<c:if test="${not empty errormsg2}">
   		<p style="color:red">	Error: ${errormsg}</p>
		</c:if>
		<p>
			First Name <input class="form-control" type="text" name="fFirstName" />
		</p>

		<p>
			Last Name <input  class="form-control" type="text" name="fLastName" />
		</p>
		<p>
			Email <input class="form-control" type="text" name="fEmail" />
		</p>

		<p>
			User Name <input class="form-control" type="text" name="fUserName" />
		</p>
			<p>
			Password <input class="form-control" type="password" name="fPassword" />
		</p>

		<input type="submit" value="Sign Up" />

	</form:form>
	</div>
	<br>
	<br>
	
	<a href="${pageContext.request.contextPath}/login">Back
				to home</a>
</body>
</html>