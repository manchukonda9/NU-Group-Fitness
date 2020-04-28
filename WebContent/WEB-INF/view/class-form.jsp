<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>class Form</title>
<style>
		.error {color:red}
	</style>

<%-- <link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" /> --%>
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




	<div>
		<h3>Save Class</h3>
		<c:if test="${not empty emsg}">
   		<p style="color:red">	Error: ${emsg}</p>
		</c:if>

		<form:form action="saveClass" modelAttribute="groupClass"
			method="POST">
			<!--  need to associate this data with customer id-->
			<!--  very important-->
			<form:hidden path="id" />
			<table>
				<tbody>
					<tr>
						<td><label>Cult Name</label></td>
						<td><form:input path="name" class="form-control"/></td>
						<form:errors path="name" cssClass="error" />
						
					</tr>

					<tr>
						<td><label>Time</label></td>
						<td><form:select class="form-control" path="time">
								<form:option value="5'O clock" label="5'O clock" />
								<form:option value="6'O clock" label="6'O clock" />
								<form:option value="7'O clock" label="7'O clock" />
								<form:option value="8'O clock" label="8'O clock" />
								<form:option value="9'O clock" label="9'O clock" />
							</form:select></td>
					</tr>

					<tr>
						<td><label>Instructor</label></td>
						<td><form:input class="form-control" path="instructor" /></td>
						<form:errors path="instructor" cssClass="error" />
						
					</tr>
					<tr>
						<td><label>Location</label></td>
						<td><form:input class="form-control" path="location" /></td>
						<form:errors path="location" cssClass="error" />
					</tr>
					<tr>
						<td><label>Day of Week</label></td>
						<td><form:select path="day" class="form-control">
								<form:option value="Monday" label="Monday" />
								<form:option value="Tuesday" label="Tuesday" />
								<form:option value="Wednesday" label="Wednesday" />
								<form:option value="Thursday" label="Thursday" />
								<form:option value="Friday" label="Friday" />
							</form:select></td>
					</tr>
					

					<tr>
						<td><label>Duration</label></td>
						<td>
						<form:select path="duration" class="form-control">
								<form:option value="30 mins" label="30 mins" />
								<form:option value="45 mins" label="45 mins" />
								<form:option value="60 mins" label="60 mins" />
								<form:option value="90 mins" label="90 mins" />
				
							</form:select></td>
						
						
					
					</tr>


					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>



		</form:form>
		<br><br>
		<p>
			<a href="${pageContext.request.contextPath}/login">Back
				to home</a>
		</p>
	</div>


</body>
</html>