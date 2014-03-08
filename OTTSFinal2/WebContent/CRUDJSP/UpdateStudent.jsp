<%@ page import="java.sql.*,java.util.*,com.ilp.otts.bean.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Update Student</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
 
    <link rel="stylesheet" type="text/css" href="/OTTS/bootstrap/docs/assets/css/bootstrap.css">
     <link rel="stylesheet" type="text/css" href="/OTTS/bootstrap/docs/assets/css/bootstrap-responsive.css">
      <link rel="StyleSheet" type="text/css" href="/OTTS/Css/Style.css"/>
<script src="/OTTS/bootstrap/docs/assets/js/jquery.js"></script>
<script src="/OTTS/bootstrap/docs/assets/js/bootstrap-dropdown.js"></script>  
        <style type="text/css">

      /* Sticky footer styles
      -------------------------------------------------- */
 


      html,
      body {
      	background-image:url("/OTTS/grey.jpg");
      	
        height: 100%;
        padding-top: 60px;
        padding-bottom: 40px;
        /* The html and body elements cannot have any padding or margin. */
      }

      /* Wrapper for page content to push down footer */
      #wrap {
        min-height: 100%;
        height: auto !important;
        height: 100%;
        /* Negative indent footer by it's height */
        margin: 0 auto -60px;
      }

      /* Set the fixed height of the footer here */
      #push,
      #footer {
        height: 60px;
      }
      #footer {
        background-color: #f5f5f5;
      }

      /* Lastly, apply responsive CSS fixes as necessary */
      @media (max-width: 767px) {
        #footer {
          margin-left: -20px;
          margin-right: -20px;
          padding-left: 20px;
          padding-right: 20px;
          
        }
      }



      /* Custom page CSS
      -------------------------------------------------- */
      /* Not required for template or sticky footer method. */

      .container {
        width: auto;
        max-width: 680px;
      }
      .container .credit {
        margin: 20px 0;
      }

    </style>
    
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
 

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/OTTS/bootstrap/docs/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/OTTS/bootstrap/docs/assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/OTTS/bootstrap/docs/assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="/OTTS/bootstrap/docs/assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="/OTTS/bootstrap/docs/assets/ico/favicon.png">
  </head>

  <body>
 <!-- <img alt="not avail" src="/OTTS/grey.jpg">  -->
  
     <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">E-xam</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              Logged in as <a href="#" class="navbar-link"><%=session.getAttribute("name")%></a>&nbsp
              <a href="/OTTS/Logout" class="navbar-link">Logout</a>
            </p>
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
    <div class="container-fluid">
    
    <!-- your content here /container -->
    <!--/sidebar-->
          <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
				<%@ include file="/CRUDlinks/StudentCrudLinks.jsp"%>            
            </ul>
          </div><!--/.well -->
        </div><!--/span-->

<div class="span6" >

	<!-- The Main BODY Starts-->



	Enter ID Student of to Update<br></br>
	<form action="/OTTS/ManageStudentServlet" onsubmit="return validateId(this)">
		<input type="hidden"  id="type" name="type" value="getupdate"> 
		Student ID:<input type="text" name="id" id="id"> <input class="btn btn-primary"  type="submit" value="Get Student Details">
			<div class="alert alert-error span6" style="display:none" id="error">  
  <!-- <a class="close" data-dismiss="alert">×</a>   -->
  <strong>Error!</strong>
	Enter a proper ID
</div> 
	</form>


	<%
		ArrayList<StudentBean> List = (ArrayList<StudentBean>) request.getAttribute("result");
		
	%>

	<%
		if (List != null) {
			//out.println("inside if 1 loop");
			out.println("Result");
			if (!List.isEmpty()) {
				StudentBean studentBean=List.get(0);
				//out.println("inside if 2 loop");
	%>
	<form action="/OTTS/ManageStudentServlet" method="post" onsubmit="return validate(this)">
		<input id="type" type="hidden" name="type" value="update"> <input
			type="hidden" name="id" value=<%=studentBean.getId() %>>
		<table>
			<tr>
				<td>Student ID:</td>
				<td><input type="text" name="id"
					value=<%=studentBean.getId()%> disabled="true"></input></td>
			</tr>
			<tr>
				<td>FirstName:</td>
				<td><input type="text" name="firstname"
					value=<%=studentBean.getFirstname()%> disabled="true"></input></td>
			</tr>
			<tr>
				<td>MiddleName:(optional)</td>
				<td><input type="text" name="middlename"
					value=<%=studentBean.getMiddlename()%> disabled="true"></td>
			</tr>
			<tr>
				<td>LastName:</td>
				<td><input type="text" name="lastname"
					value=<%=studentBean.getLastname()%> disabled="true"></td>
			</tr>
			<tr>
				<td>DOB:</td>
				<td><input type="text" name="dob" value=<%=studentBean.getDob()%> disabled="true">
				</td>
			</tr>
			<tr>
				<td>Gender:</td>
				<td><input type="text" name="gender" value=<%=studentBean.getGender()%> disabled="true">
				</td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email"
					value=<%=studentBean.getEmail()%> id="email" onblur="check(this)"></td>
					<td id="email-error" class="error"></td>
			</tr>
			<tr>
				<td>PhoneNumber:</td>
				<td><input type="text" name="phonenumber"
					value=<%=studentBean.getPhonenumber()%> id="phonenumber" onblur="check(this)"></td>
					<td id="phonenumber-error" class="error"></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address"
					value=<%=studentBean.getAddress()%> disabled="true"></td>
			</tr>
			<tr>
				<td>SchoolID:</td>
				<td><input type="text" name="schoolid"
					value=<%=studentBean.getSchool()%>></td>
			</tr>

			<tr align="center">
				<td colspan="2"><input class="btn btn-success" id="submit" type="submit" name="submit"	value="Update Student"></td>
			</tr>

		</table>
	</form>
	<%
		} else {
				out.println("<br>No Records Found");
			}
		} else {
			//out.println("result null");
		}
	%>

	<%
		String status = (String) request.getAttribute("status");
		if (status != null) {
			out.println("Update "+status);
		}
	%>





	<!-- The Main BODY Ends-->

</div>
</div>

     
   
    
    
    
        </div> <!-- /container -->

 <div id="footer" class="avbar navbar-fixed-bottom">

      <div class="container">
      	 <div class="footer">
       		 <p class="muted credit">Site Designed by <a href="#">TCS ILP M134 GROUPD</a>&nbsp &copy TCS ILP 2013</p>
    	  </div>
      </div>
    </div>

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/OTTS/bootstrap/docs/assets/js/jquery.js"></script>
    <script src="/OTTS/bootstrap/docs/assets/js/bootstrap-transition.js"></script>
    <script src="/OTTS/bootstrap/docs/assets/js/bootstrap-alert.js"></script>
    <script src="/OTTS/bootstrap/docs/assets/js/bootstrap-modal.js"></script>
    <script src="/OTTS/bootstrap/docs/assets/js/bootstrap-dropdown.js"></script>
    <script src="/OTTS/bootstrap/docs/assets/js/bootstrap-scrollspy.js"></script>
    <script src="/OTTS/bootstrap/docs/assets/js/bootstrap-tab.js"></script>
    <script src="/OTTS/bootstrap/docs/assets/js/bootstrap-tooltip.js"></script>
    <script src="/OTTS/bootstrap/docs/assets/js/bootstrap-popover.js"></script>
    <script src="/OTTS/bootstrap/docs/assets/js/bootstrap-button.js"></script>
    <script src="/OTTS/bootstrap/docs/assets/js/bootstrap-collapse.js"></script>
    <script src="/OTTS/bootstrap/docs/assets/js/bootstrap-carousel.js"></script>
    <script src="/OTTS/bootstrap/docs/assets/js/bootstrap-typeahead.js"></script>
    <script type="text/javascript" src="/OTTS/Script/StudentValidations.js"></script>
<script type="text/javascript">
window.history.forward();
document.body.oncontextmenu="return false";
</script>
  </body>
</html>
    