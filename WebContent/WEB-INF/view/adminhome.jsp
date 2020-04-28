<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<h2>Admin Home Page</h2>
Welcome ${ loggedUser.getUserName()}
<hr>

<a href="admin/profilepage">View Profile</a>

<br><br>


<a href="admin/scheduleclass">schedule Class</a>

<br><br>



<a href="admin/showclasses">edit Class</a>

<br><br>


<a href="admin/viewstats">view stats</a>

<br><br>

<a href="admin/addadmin">Add a new Admin</a>

<br><br>

<a href="admin/waitlist">Approve Users</a>

<br><br>
<a href="admin/charts">Analystics</a>
<br><br>

        <form action="logout" method="post">
            <input type="hidden" name="option" value="logout">
            <input type="submit" value="Logout">
        </form><br>



</body>