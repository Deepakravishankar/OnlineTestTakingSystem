<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OTTS</title>
<link rel=StyleSheet href="<%=request.getContextPath() %>/css/admin.css" type="text/css"/>
<script type="text/javascript">
function validate()
{
var x=document.forms["myForm"]["QuestionId"].value;
if (x==null || x=="")
  {
  alert("Please enter the Testid");
  return false;
  }
}
</script>
</head>
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
<br><br><br><br>
<div id="content" style="height:200px;width:400px;float:left;">

<form name="myForm" method="post"
	action="<%=request.getContextPath()%>/QuestionController"
	onsubmit="return validate();">
	
<input type="hidden" name="page" value="view">
<h2>View Question</h2>
QuestionId:
<input type="text" name="QuestionId" value=""><br><br>

<input type="submit" value="view" >
<input type="reset" value="reset"></input>


</form>
</div>
</body>
</html>