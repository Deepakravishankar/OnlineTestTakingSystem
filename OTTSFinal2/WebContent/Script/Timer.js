function countdown(count){
	
	countdown=setInterval(function(){
		var min=count/60;
		var sec=count%60;
		document.getElementById("seconds").innerHTML=sec+"secs remaining!";
		document.getElementById("minutes").innerHTML=Math.floor(min)+" min";
		//$("#counter").val(count);
		if(count==0){
			count=70;
		}
		count--;
	},1000);
}
countdown(70);