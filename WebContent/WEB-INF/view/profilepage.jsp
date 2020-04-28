<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>profile page</title>
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
<hr>
<div class="container a">
<h2>User details</h2>
<br>

	<form:form action="updateUser" modelAttribute="userdetails" method="get">
		<!--  need to associate this data with customer id-->
		<!--  very important-->
		<c:if test="${not empty error}">
		 		<p style="color:red">	Error: ${error}</p>
		</c:if>
		<c:if test="${not empty successmsg}">
		 		<p style="color:green">	dbstatus: ${successmsg}</p>
		</c:if>
		<form:hidden path="id" />
		<table class="center">
				<tbody>
				
					<tr>	
						<td><label >First name</label></td>
						<td><form:input class="form-control" path="firstName" readonly= ' ${enabler}
						' /></td>
					
					</tr>
			
					<tr>
						<td><label>Last name</label></td>
						<td><form:input class="form-control" path="lastName" readonly='<c:out> ${enabler}
						</c:out>'  /></td>
					</tr>

					<tr>
						<td><label>Email</label></td>
						<td><form:input class="form-control" path="email" readonly="true"
					   /></td>
					</tr>
						<tr>
						<td><label>password</label></td>
						<td><form:input class="form-control" type="password" path="password" readonly='<c:out> ${enabler}
						</c:out>'   /></td>
					</tr>
					
					
					<tr>
						<td><label><br><br><br></label></td>
						<td><input type="submit" value="update" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
	
	
	
	</form:form>

	<br>
	<br>
	<br>
		<a href="${pageContext.request.contextPath}/login">Back
				to home</a>
			

        <br>
        <br>
	   <form action="logout" method="post">
            <input type="hidden" name="option" value="logout">
            <input type="submit" value="Logout">
        </form><br>
        
        
	

</div>


</body>
</html>