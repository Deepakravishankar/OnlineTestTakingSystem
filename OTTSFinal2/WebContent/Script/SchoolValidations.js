/**
 * 
 */

function check(inputTag){
	var pattern;
	
	switch(inputTag.id){
	
	case "schoolname": case"location": 
		pattern=new RegExp("^[a-zA-Z ]{1,50}$");
		if(!pattern.test(inputTag.value)){
			document.getElementById(inputTag.id+"-error").innerHTML="This field can contain only alphabets";
			return false;
		}
		else
			document.getElementById(inputTag.id+"-error").innerHTML="";

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

	case "board":
		if(inputTag.firstChild.selected){
			document.getElementById(inputTag.id+"-error").innerHTML="Please select any option";
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
	result&=check(document.getElementById("board"));	

	
	for(i=0;i<inputTags.length;i++){
		result&=check(inputTags[i]);
	}
	//alert(result==1?true:false);
	return result==1?true:false;
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