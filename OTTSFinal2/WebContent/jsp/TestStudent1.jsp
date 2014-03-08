<%@ page language="java" import="com.ilp.otts.bean.TestBean, java.util.ArrayList" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Student Association</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" media="screen">
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



<%ArrayList<TestBean> tb=new ArrayList<TestBean>(); 
tb=(ArrayList<TestBean>)session.getAttribute("teststud");
%>
<form name=form action="/OTTS/TestController" method="post">
<select name=test>
<%if(!tb.isEmpty()){
for(int i=0;i<tb.size();i++){ %>
<option value="<%=tb.get(i).getTestid()%>"><%=tb.get(i).getTestName() %></option>
<%} }else{
	
}%>
</select>
<input type="hidden" name="page" value="teststudent1"></input>
<input type="submit" value="select"></input>

</form>
</body>
</html>