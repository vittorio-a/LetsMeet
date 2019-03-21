<%@page import="it.unisa.studenti.letsmeet.control.gestione_account.LoginControl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

		<head>
		
			<meta charset="UTF-8">
			<link href="../css/profilo.css" rel="stylesheet" type="text/css">
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
			<title>Insert title here</title>
			
		</head>
		<script type="text/javascript">
		
		$.ajax({
			 url: "/LetsMeetWeb/auth/account/profiloControl",
		      type: 'GET',
		      dataType: "json",
		      success:function(response){
		    	  utente = response.data.utente;
		     	  eventi = response.data.eventi;
		    	  console.log(response);
		    	  console.log(eventi);
		    	  $("#nome-text").text(utente.credentials.username);
		    	  $("#email-text").text("Email: "+ utente.email);
		    	  $("#feedback-text").text("FeedbackUtente: " + utente.feedbackUtente);
		    	  eventi.forEach(function(evento){
		    	  	$("#info-eventi-creati").append(" " + evento.nome);
		    	  });
		      	},
			error:function(){
				alert("Nu funzion NIENT");
			}
		});
		
		</script>
			<body>
			
				<div id="container">
			
						<div id="header">
					
							<h1><a href="../homePage.jsp">LetsMeet</a></h1> 
							
							<a href="profilo.jsp"><img id="profilo" alt="profilo" src="images/profilo.png"></a> 
							
							<a href="impostazioni.html"><img id="settings" alt="settings" src="images/two-cogwheels-configuration-interface-symbol.png"></a>
							
				
						</div>
						
						<div id="container_profilo">
						
								<h1 id = "nome-text">Nome Utente</h1>
								
								<ul id = "list-credentials">
									<li><p id = "feedback-text">Feedback</p></li>
											
									<li><p id="email-text">Email</p></li>
												
								</ul>
						
						</div>
						
						<div id="container_info">
						
								<h1>Info Eventi</h1>
								<p id = "info-eventi-creati"></p>
								<span><a href="infoEvento.jsp">Vai a InfoEvento</a></span>
						
						</div>
						
						<footer>
				
							<h3>LetsMeet2019</h3>
				
						</footer>
						
				</div>

			</body>
</html>