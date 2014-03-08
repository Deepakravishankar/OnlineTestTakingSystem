
<li class="nav-header">Manage</li>
<li class="active"><a href="/OTTS/WelcomeJSP/<%=session.getAttribute("role")%>.jsp" >Home</a></li>
<li><a href="<%=request.getContextPath() %>/awardjsp/search.jsp">Student Award Allocation</a></li>
<li><a href="<%=request.getContextPath() %>/awardjsp/viewallocatedawards.jsp">View Student Award</a></li>
<li><a href="<%=request.getContextPath() %>/awardjsp/updateallocatedaward.jsp">Update Student Award</a></li>
<li><a href="<%=request.getContextPath() %>/awardjsp/deleteaward.jsp">Delete Student Award</a></li>
