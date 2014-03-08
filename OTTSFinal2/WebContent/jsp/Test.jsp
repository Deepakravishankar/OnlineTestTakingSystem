<%@ page import="com.ilp.otts.bean.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>ManageAdmin</title>
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
                                   
<script type="text/javascript">
function countdown(count){
	
	countdown=setInterval(function(){
		var min=count/60;
		var hours=min/60;
		min%=60;
		var sec=count%60;
		document.getElementById("hours").innerHTML=Math.floor(hours)+"hr ";
		document.getElementById("minutes").innerHTML=Math.floor(min)+" min";
		document.getElementById("seconds").innerHTML=sec+"secs remaining!";
		document.getElementById("remainingtime").value=count;
		var request=new ActiveXObject("Microsoft.XMLHTTP");
		request.open("GET","TestController?page=time&remaining="+count,"");
		request.send();
		//request.close();
		if(count==0){
			document.getElementById("endTest").submit();			
		}
		count--;
	},1000);
}
function append(anchor){
	var sec=document.getElementById("remainingtime").value;
	anchor.href+="&remainingtime="+sec;
}
</script>
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
				<%@ include file="/CRUDlinks/AdminCrudLinks.jsp"%>    
				  <!-- Side bar links placed here  -->
				         
            </ul>
          </div><!--/.well -->
        </div><!--/span-->

<div class="span6">

	<!-- The Main BODY Starts-->


<div style="position:absolute;right:100px;top:50px">
<label id="hours" style="float:left"></label>
<label id="minutes" style="float:left"></label>
<label id="seconds" style="float:left;padding-left:5px"></label>
</div>

<% QuestionBean qb=(QuestionBean)request.getAttribute("question");
	String qno=(String)request.getAttribute("qno");
	String testid=(String)session.getAttribute("testid");
%>
<%=qb.getQuestion()%>
<form action="TestController" method="post">
<div style="display:none">
<input type="hidden" name="page" value="save"><br>
<input type="hidden" name="qno" value="<%=qno%>"><br>
</div>
<input type="text" name="remainingtime" id="remainingtime"></input>
<input type="radio" name="answer" id="<%=qb.getOption1()%>" value="<%=qb.getOption1()%>"><%=qb.getOption1()%><br>
<input type="radio" name="answer" id="<%=qb.getOption2()%>" value="<%=qb.getOption2()%>"><%=qb.getOption2()%><br>
<input type="radio" name="answer" id="<%=qb.getOption3()%>" value="<%=qb.getOption3()%>"><%=qb.getOption3()%><br>
<input type="radio" name="answer" id="<%=qb.getOption4()%>" value="<%=qb.getOption4()%>"><%=qb.getOption4()%><br>
<input type="submit" value="save">
</form>
<form id="endTest" action="TestController" method="post">
<input type="hidden" name="page" value="endtest"><br>
<input type="submit" value="EndTest">
</form><br><br></br>
<table><tr>
<%for(int i=1;i<11;i++){ %>
<td>
<a href="TestController?page=getquestion&qno=<%=i%>" onclick="append(this)"><%=i%></a>
</td>
<%} %>
</tr></table>
<script type="text/javascript">
<%String answer=qb.getAnswer();
if(answer!=null)
out.println("document.getElementById(\""+answer+"\").checked=\"true\";");
%>
</script>
<script type="text/javascript">
//alert("hi");
countdown(<%=(Integer)session.getAttribute("duration")%>);
</script>



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
    