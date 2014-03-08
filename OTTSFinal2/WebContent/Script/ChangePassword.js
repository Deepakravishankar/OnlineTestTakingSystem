function checkPassword(){
	var oldpwd=document.getElementById("oldpwd");
	var newpwd=document.getElementById("newpwd");
	var repeatpwd=document.getElementById("repeatnewpwd");
	try{
		if(newpwd.value!=repeatpwd.value){
			alert("The passwords do not match");
			return false;
		}
		if(oldpwd.value==newpwd.value){
			alert("New and Old Passwords are the same");
			return false;
		}
	}catch(e){
		return false;
	}

	return true;
}