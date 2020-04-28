<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML>
<html>
<head>
<title>Web analytics</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<style>
	a.one:link, a.one:visited {
  background-color: #f44336;
  color: white;
  padding: 14px 25px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}

a.one:hover, a.one:active {
  background-color: red;
}
	
	
	</style>
  <script type="text/javascript">
  
  window.onload = function () {
    var chart = new CanvasJS.Chart("chartContainer", {
    	 backgroundColor: "#616A6B",
      title:{
        text: "Total active classes happening this week"              
      },
      data: [//array of dataSeries              
        { //dataSeries object

         /*** Change type "column" to "bar", "area", "line" or "pie"***/
         type: "column",
         dataPoints: [
         { label: "Monday", y: parseInt("${monday}")},
         { label: "Tuesday", y: parseInt("${tuesday}") },
         { label: "Wednesday", y: parseInt("${wednesday}") },                                    
         { label: "Thursday", y: parseInt("${thursday}") },
         { label: "Friday", y: parseInt("${friday}") }
         ]
       }
       ]
     });

    chart.render();
    
    var chart2 = new CanvasJS.Chart("chartContainer2", {
   	 backgroundColor: "#616A6B",
     title:{
       text: "Students Vs total Active Students "              
     },
     data: [//array of dataSeries              
       { //dataSeries object

        /*** Change type "column" to "bar", "area", "line" or "pie"***/
        type: "column",
        dataPoints: [
        { label: "Total Registered Students", y: parseInt("${totalU}")},
        { label: "Total Active Students", y: parseInt("${activeU}") },
                                        
      
      
        ]
      }
      ]
    });

   chart2.render();
  }
  </script>
  <script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</head>
<body class="container p-3 my-3 bg-dark text-white">
	<div class="a">

		<h2 >NU Group Fitness</h2>
	</div>
	<br>
	<br>
	<hr>
	<h2>Web Analytics</h2>
  <div id="chartContainer" style="height: 300px; width: 100%;">
  <br>
  <br>
   </div>
    <br>
  <br>
   <br>
  <br>
   <div id="chartContainer2" style="height: 300px; width: 100%;">
   <br>
  <br>
   </div>
 
  <br>
  <br>
  	<p>
			<a class="one" href="${pageContext.request.contextPath}/login">Back
				to home</a>
		</p>
</body>
</html>