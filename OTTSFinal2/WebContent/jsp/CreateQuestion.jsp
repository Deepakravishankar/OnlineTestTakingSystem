<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<meta name="google-translate-customization" content="3e35f4afa88bad08-c98ecfd2e381b702-gb1144de31aa30118-1d"></meta>
  <head>
  <script type="text/javascript">
  function validate()
{

var x=document.forms["form1"]["questionname"].value;
if (x==null || x=="")
  {
  alert("Please enter the Questionname");
  return false;
  }

	

var x=document.forms["form1"]["Category"].value;
if (x=="select")
  {
  alert("Please enter the Category");
  return false;
  }

var x=document.forms["form1"]["option1"].value;
if (x==null || x=="")
  {
  alert("Please enter option1");
  return false;
  }
var x=document.forms["form1"]["option2"].value;
if (x==null || x=="")
{
	alert("Please enter option2");
	  return false;
}
var x=document.forms["form1"]["option3"].value;
if (x==null || x=="")
{
	alert("Please enter option3");
	  return false;
}
var x=document.forms["form1"]["option4"].value;
if (x==null || x=="")
{
	alert("Please enter option4");
	  return false;
}
var x=document.forms["form1"]["answer1"].value;
if (x==null || x=="")
{
	alert("Please enter answer");
	  return false;
}


return true;
}
  
function setAnswer(){
	var select=document.getElementById("answer1").value;
	var answer=document.getElementById(select).value;
	document.getElementById("answer").value=answer;
	
}  
</script>
    <meta charset="utf-8">
    <title>ManageTest</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
 
    <link rel="stylesheet" type="text/css" href="/OTTS/bootstrap/docs/assets/css/bootstrap.css">
     <link rel="stylesheet" type="text/css" href="/OTTS/bootstrap/docs/assets/css/bootstrap-responsive.css">
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
  <div id="google_translate_element"><script type="text/javascript">
function googleTranslateElementInit() {
	  new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.SIMPLE}, 'google_translate_element');
	}
</script><script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
</div>
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
				<%@ include file="/CRUDlinks/QuestionCrudLinks.jsp"%>             
            </ul>
          </div><!--/.well -->
        </div><!--/span-->

<div class="span6">
<form name=form1 onsubmit="return validate()" action="/OTTS/QuestionController" method="post" >
<table border=2>
<tr><td>Question</td><td><textarea name="questionname" rows="4" cols="20"></textarea></td></tr>
<tr><td>Category</td><td><select name="Category">
 <option value="select">select</option>
  <option value="english">English</option>
  <option value="maths">Maths</option>
  <option value="science">Science</option>
  <option value="computer">Computer</option>
</select></td></tr>
<tr><td>Option1</td><td><input type="text" value="" name="option1" id="option1" ></td></tr>
<tr><td>Option2</td><td><input type="text" value="" name="option2" id="option2" ></td></tr>
<tr><td>Option3</td><td><input type="text" value="" name="option3" id="option3" ></td></tr>
<tr><td>Option4</td><td><input type="text" value="" name="option4" id="option4" ></td></tr>
<tr><td>Answer</td><td>
<select name="answer1"id="answer1" onchange="setAnswer()">
<option value=""></option>
  <option value="option1">Option1</option>
  <option value="option2">Option2</option>
  <option value="option3">Option3</option>
  <option value="option4">Option4</option>
</select>
</td></tr>
</table>
<input type="hidden" name="answer" id="answer" value=""></input>
<input type="hidden" name="page"  value="createquestion"></input>
<br></br>
<input type="submit" value="create"></input>
</form>
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
<script type="text/javascript">
window.history.forward();
document.body.oncontextmenu="return false";
</script>
  </body>
</html>
    