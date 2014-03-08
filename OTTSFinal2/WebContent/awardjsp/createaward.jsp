<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>ManageAdmin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
<script type="text/javascript">

var message = "Right click is disabled"; 
function rtclickcheck(keyp){ if (navigator.appName == "Netscape" && keyp.which == 3){ 	alert(message); return false; } 
if (navigator.appVersion.indexOf("MSIE") != -1 && event.button == 2) { 	alert(message); 	return false; } } 
document.onmousedown = rtclickcheck;

function validateForm()
{
	var message = "Right click is disabled"; 
	function rtclickcheck(keyp){ if (navigator.appName == "Netscape" && keyp.which == 3){ 	alert(message); return false; } 
	if (navigator.appVersion.indexOf("MSIE") != -1 && event.button == 2) { 	alert(message); 	return false; } } 
	document.onmousedown = rtclickcheck;
	var x=document.getElementById('awardtype');	

	if (!x.value.match(/^[a-zA-Z]+$/))
	{
   	alert("Please Enter a Valid Award Type");
	document.getElementById('awardname').focus;
	return false;
	}
	

	var y=document.getElementById('awardname');	

	if (!y.value.match(/^[a-zA-Z]+$/))
	{
   	alert("Please Enter the Award Name");
	document.getElementById('awardname').focus;
	return false;
	}
	var t=y.value;
	if(t.length>20)
	{
		alert("Character is too long");
	return false;
	}
var z=document.getElementById('awardamount');	

	if (!z.value.match(/^[0-9]/))
	{
		
   	alert("Please Enter the  valid Award Amount");
	document.getElementById('awardamount').focus;
	return false;
	}
	if(z.value.length>20)
	{
		alert("Award amount is invalid and its too long");
	return false;
	}
	var a=document.getElementById('awardreason');
	if (!a.value.match(/^[a-zA-Z]+$/))
	{
	 alert("Please Give a reason for Award");
	 document.getElementById('awardreason').focus();
	 return false;
	}
	if(a.value.length>50)
	{
		alert("Character is too long");
	return false;
	}

	var w=document.getElementById('low');	

		if (!w.value.match(/^[0-9]/))
		{
			
	   	alert("Please Enter the low value");
		document.getElementById('low').focus;
		return false;
		}
		if(w.value.length>3)
		{
			alert("Enter a number less than 100");
		return false;
		}

		var b=document.getElementById('high');	

			if (!b.value.match(/^[0-9]/))
			{
				
		   	alert("Please Enter the  high value");
			document.getElementById('high').focus;
			return false;
			}
			if(b.value.length>3)
			{
				alert("Enter a number less than 100");
			return false;
			}
			if(parseInt(b.value)<=parseInt(w.value)){
				alert("Please Enter the high value greater than low value");
				document.getElementById('high').focus;
				return false;
			}
			if(parseInt(w.value)>100){
				alert("Please Enter the valid low value");
				document.getElementById('low').focus;
				return false;
			}
			if(parseInt(b.value)>100){
				alert("Please Enter the valid high value");
				document.getElementById('high').focus;
				return false;
			}
			
return true;

}
</script>

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
            
            
            
            <!-- Side bar links placed here  -->
				<%@ include file="/CRUDlinks/AwardCrudLinks.jsp"%>    
				  <!-- Side bar links placed here  -->
				         
            </ul>
          </div><!--/.well -->
        </div><!--/span-->

<div class="span6">

	<!-- The Main BODY Starts-->
<center>
<h4 style="font-family:gentiumbasic;font-size:30px;position:relative;left:-1px">Award Creation</h4><br></br>


<form name="create" method="post" action="<%=request.getContextPath()%>/Controller" onsubmit="return validateForm()">


<table style="position:relative;top:-30px;left:-70px">
<tr>
<td align="left">Award type<font color="red">*</font></td>
<td><select name="awardtype" id="awardtype" >
<option value="">Select</option>
<option value="schoolaward">School award</option>
<option  value="studentaward">Student award</option>
</select></td>
</tr>
<tr><td align="left">Award Name<font color="red">*</font></td><td><input type="text" name="awardname" id="awardname" ></input></td></tr>
<tr><td align="left">Award amount<font color="red">*</font></td><td><input type="text" name="awardamount" id="awardamount"></input></td></tr>
<tr ><td align="left">Award reason<font color="red">*</font></td><td><input type="text" name="awardreason" id="awardreason"></input></td></tr>
<tr><td align="left">Award details</td><td><input type="text" name="awarddetails"></input></td></tr>


<tr ><td align="left">Low<font color="red">*</font></td><td><input type="text" name="low" id="low" ></input></td></tr>
<tr><td align="left">High<font color="red">*</font></td><td><input type="text" name="high" id="high"></input></td></tr>



<tr></tr><tr></tr><tr></tr><tr></tr>
<tr><td></td>&nbsp<td align="left"><input type="submit" class="btn btn-primary" name="page" value="Create"></input> &nbsp
&nbsp
<input type="reset" class="btn btn-primary" name="reset" value="Reset"></input></td></tr>

</table>


</center>
</form>






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

  </body>
</html>
    