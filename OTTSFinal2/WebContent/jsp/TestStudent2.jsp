<%@ page language="java" import="com.ilp.otts.bean.TestStudentBean, java.util.ArrayList" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Student Association</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" media="screen">
</head>
<body class="body">
<<h1><center>ONLINE TEST TAKING SYSTEM</center></h1>
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

<%ArrayList<TestStudentBean> tsb=new ArrayList<TestStudentBean>();
tsb=(ArrayList<TestStudentBean>)session.getAttribute("studs");
%>
<form name=form action="/OTTS/TestController" method="post" onsubmit="return validate();">
<%for(int i=0;i<tsb.size();i++){ %>
<input type="checkbox" name="studenttest" value="<%=tsb.get(i).getStudentid()%>"><%=tsb.get(i).getStudentName() %>
<%} %>
<input type="hidden" name=page value=teststudentassociation></input>
<input type=submit value="associate student"></input>
</form>
</body>
</html>