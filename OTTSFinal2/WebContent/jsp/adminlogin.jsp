<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" media="screen">
<style type="text/css">
		html, body { margin: 0;	padding: 0; }
		ul.menu { margin: 50px auto 0 auto; }
	</style>
</head>

<body class="body">

<body class="body">
<h1><center>ONLINE TEST TAKING SYSTEM</center></h1>
<ul class="menu">
<li><a href="home.jsp">HOME</a></li>
	<li><a href="adminlogin.jsp">ADMIN LOGIN</a>
    
			
			<ul>
			<li><a href="CreateTest1.jsp" class="messages">CREATE TEST</a></li>
			<li><a href="viewbutton.jsp" >VIEW TEST</a></li>
			<li><a href="UpdateTest1.jsp" class="messages">UPDATE TEST</a></li>
			<li><a href="deletetest.jsp" class="signout">DELETE TEST</a></li>
			</ul>
			</li>
			
			
	<li><a href="teacherlogin.jsp">TEACHER LOGIN</a>
	<ul>
			<li><a href="CreateQuestion.jsp" class="documents">CREATE QUESTION</a></li>
			<li><a href="viewQuestion.jsp" class="messages">VIEW QUESTION</a></li>
			<li><a href="UpdateQuestions.jsp" class="signout">UPDATE QUESTION</a></li>
			<li><a href="deleteQuestion.jsp" class="signout">DELETE QUESTION</a></li>
		</ul>
		</li>
	<li><a href="Studentlogin.jsp">STUDENT LOGIN</a>

		<ul>
			<li><a href="Student.jsp" class="documents">TAKE TEST</a></li>
			
		</ul>

	</li>
	

</ul>

 <!-- end .menu -->






<div id="container" style="width:700px">
<div id="header" >


<div id="menu" style="height:200px;width:300px;float:left;">

<br><br><br><br><br><br>
<a href="home.jsp">HOME</a> 


</div>
</div>
</div>
</body>
</html>