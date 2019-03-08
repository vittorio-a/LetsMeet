/**
 * 
 */
function validateRegistration(registration){
	var usrValidator = /^(\w+[_\.\-]*\w*){4,}$/;
	var pswValidator = /^[a-zA-Z 0-9 \@\._\!\?\-]{8,}$/;
	var mailValidator = /^\w+([\._\-]?\w+)*@\w+([\.\-]?\w+)*(\.\w+)+$/;
	
	var usrIsOK = registration.username.value.match(usrValidator);
	var pswIsOK = registration.password.value.match(pswValidator);
	var pswConf = registration.confermapsw.value;
	var mailIsOK = registration.email.value.match(mailValidator);
	
	if (!usrIsOK) { //Check username
		alert('Lo username deve contenere lettere, numeri o i caratteri "_", "." "-"  e deve essere lungo almeno 5');
		document.getElementById("username").focus(); //Set focus
		return false; //Negate access
	} else
		if (!pswIsOK) { //Check password
			alert("La password non è valida");
			document.getElementById("password").value="";
			document.getElementById("confermapsw").value="";
			document.getElementById("password").focus(); //Set focus
			return false; //Negate access
	
	} else

		if (formRegistration.confermapsw.value != formRegistration.password.value) { //Check password
			alert("Le password inserite non corrispondono");
			document.getElementById("password").value="";
			document.getElementById("confermapsw").value="";
			document.getElementById("password").focus(); //Set focus
			return false; //Negate access
	}else
			
		if(!mailsOK){ // Check email
			alert("Email non corretta")
			document.getElementById("email").focus(); //Set focus
			return false; //Negate access
	}else 
		return true;
				
	
	
}