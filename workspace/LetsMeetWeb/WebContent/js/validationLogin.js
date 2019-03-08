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
		return true;
	
	
}