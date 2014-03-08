function dis(){
	alert("hi");
}


function check(inputTag){
	var pattern;

	switch(inputTag.id){
	case "firstname": case"lastname": case"middlename": 
		if(inputTag.id=="middlename" && inputTag.value==""){
			return true;
		}

		pattern=new RegExp("^[a-zA-Z]{1,20}$");
		if(!pattern.test(inputTag.value)){
			document.getElementById(inputTag.id+"-error").innerHTML="This field can contain only alphabets and no spaces";
			
			return false;
		}
		else
			document.getElementById(inputTag.id+"-error").innerHTML="";

		break;
		
	case "address":
		if(inputTag.value==""){
			return true;
		}
		
		pattern=new RegExp("^[A-Za-z0-9-, '.()\/]{5,100}$");
		if(!pattern.test(inputTag.value)){
			document.getElementById(inputTag.id+"-error").innerHTML="Please enter a valid Address";
			
			return false;
		}
		else
			document.getElementById(inputTag.id+"-error").innerHTML="";
		break;


	case "year":
		var day=document.getElementById("day");
		var month=document.getElementById("month");
		var year=document.getElementById("year");
		if(day.firstChild.selected || month.firstChild.selected || year.firstChild.selected){
			document.getElementById("dob-error").innerHTML="Please enter valid Date";
			
			return false;
		}
		else{
			document.getElementById("dob-error").innerHTML="";
			//alert(day.value+"-"+month.value+"-"+year.value);
			//alert(day.value+"-"+month.value.toLowerCase()+"-"+year.value);
			//document.getElementById("dob").value=day.value+"-"+month.value.toLowerCase()+"-"+year.value;
		}
		break;
		
	case "email":
		pattern=new RegExp("^[0-9a-z.]+@[a-z]+[.][a-z]{3}$");
		if(!pattern.test(inputTag.value)){
			document.getElementById(inputTag.id+"-error").innerHTML="Please enter a valid email";
			
			return false;
		}
		else
			document.getElementById(inputTag.id+"-error").innerHTML="";
		break;

	case "phonenumber":
		pattern=new RegExp("-");
		if(pattern.test(inputTag.value))			
			pattern=new RegExp("^[0-9]{3,5}-[0-9]{6,8}$");
		else
			pattern=new RegExp("^[987][0-9]{9}$");
		
		if(!pattern.test(inputTag.value)){
			document.getElementById(inputTag.id+"-error").innerHTML="Please enter a valid phone number";
			
			return false;
		}
		else
			document.getElementById(inputTag.id+"-error").innerHTML="";
		break;

	}
	return true;
}

function validate(form){
	var inputTags=document.getElementsByTagName("input");
	var result=true;
	result&=check(document.getElementById("address"));	
	result&=check(document.getElementById("year"));
	
	for(i=0;i<inputTags.length;i++){
		result&=check(inputTags[i]);
	}
	//alert(result==1?true:false);
	return result==1?true:false;
}

function generateDate(){
	var months=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	var day=document.getElementById("day");
	var month=document.getElementById("month");
	var year=document.getElementById("year");
	for(var i=1;i<32;i++){
		var option=document.createElement("option");
		
		option.innerHTML=i;
		day.appendChild(option);
	}
	for(var i=1940;i<2007;i++){
		var option=document.createElement("option");
		option.innerHTML=i;
		year.appendChild(option);
	}
	for(var i=0;i<months.length;i++){
		var option=document.createElement("option");
		option.innerHTML=months[i];
		month.appendChild(option);
	}

}

function validateId(form){
	var pattern=new RegExp("^[a-z]{2}[0-9]{2,5}$");
	if(!pattern.test(form.id.value)){
		document.getElementById("error").style.display="";
		return false;
	}
	else{
		document.getElementById("error").style.display="none";
	}
	
	
	return true;
}


function validateDeleteId(form){
	var pattern=new RegExp("^[a-z]{2}[0-9]{2,5}$");
	if(!pattern.test(form.id.value)){
		document.getElementById("error").style.display="";
		return false;
	}
	else{
		document.getElementById("error").style.display="none";
	}
	
	
	return confirm("Confirm Deletion ?");
}