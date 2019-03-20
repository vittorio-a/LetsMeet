/**
 * 
 */

function validateLogin(login){
	
	var usrValidator = /^(\w+[_\.\-]*\w*){4,}$/;
	var pswValidator = /^[a-zA-Z 0-9 \@\._\!\?\-]{8,}$/;
	
	var usrIsOK = login.username.value.match(usrValidator);
	var pswIsOK = login.password.value.match(pswValidator);
	
	if (!usrIsOK) { //Check username
		alert('Lo username deve contenere lettere, numeri o i caratteri "_", "." "-"  e deve essere lungo almeno 5');
		document.getElementById("username").focus(); //Set focus
		return false; //Negate access
	} else
		if (!pswIsOK) { //Check password
			alert("La password non è valida");
			document.getElementById("password").value="";
			document.getElementById("password").focus(); //Set focus
			return false; //Negate access
	
	} else
		submitForm()
	
		
}
function submitForm(){
	console.log("agg bisogn e me fa l'amante");

	username = $('#username')[0];
	password =  $('#password')[0];
	data = {username:username.value, password:password.value};
	$.ajax({
	     // url: $('#login').attr('action'),
	      url: "/LetsMeetWeb/account/loginControl",
	      type: 'POST',
	      data : data,
	      dataType: "json",
	      success: function(data){
	    	console.log(data);
			if(data.errorcode == 0){
				window.location = "/LetsMeetWeb/homePage.jsp";
			}else{
				alert(data.error);
				/*
				$("#error-text").text(data.error);
				$("#error-text").css("visibility","visible");
			*/
			}
	      },
	    error : function() {
			alert("C'è stato un problema con il login");
	    }
	});

}