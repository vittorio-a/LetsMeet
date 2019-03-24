<%@page import="it.unisa.studenti.letsmeet.control.gestione_account.LoginControl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%final String ID_EVENTO_PARAM = "idEvento";%>

<html>

	<head>
	
			
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
			<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&v=3&libraries=geometry"></script>
	
		<meta charset="	UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="../css/infoEvento.css" rel="stylesheet" type="text/css">
		<title>Info</title>
	</head>
	<script type="text/javascript">
	<%
		String idEvn = request.getParameter(ID_EVENTO_PARAM);
		if(idEvn == null) idEvn = "1";
	%>
	var idEvento = <%=idEvn%>;
	var idUser = <%=session.getAttribute(LoginControl.ID_IN_SESSION)%>;
	
	</script>
	<script type="text/javascript" src="../js/infoEvento.js"></script>
	
		<body>
			
			<div id="container">
			
			
				<div id="conferma_creazione">
						
						
							<p>Rating effettuato!</p>
						
						</div>
			
					<div id="container_event1">
					
						<h1 id="nome-txt">evento1</h1>
						
						<button id="tipo">Tipo</button>
						
						<a href="homePage.jsp"><img id="back" alt="freccia" src="../images/sign-out-option.png"></a>
						
						<div id="calendar">
							<img  alt="calendario" src="../images/calendar-with-a-clock-time-tools.png">
							<span id="date-txt">0</span>
						</div>
						
						<img id="like" alt="pollice" src="../images/thumbs-up.png" onclick="vota('true')">
						<img id="dislike" class="ruota180" alt="pollice" src="../images/thumbs-up.png" onclick="vota('false')">
						
						<div id="heart">
							<img alt="cuore" src="../images/like-of-filled-heart.png">
							<span id="voto-txt">0</span>
						</div>
						<div id="partecipanti">
							<img alt="people" src="../images/team.png">
							<span id="part-txt">0</span>
							<span id="verf-txt">0</span>
						</div>
				</div>
				
				<div id="descrizione">
						
						<h1>Descrizione</h1>
						<p class="break-word" id="desc-txt"></p>
				</div>
				
				<button id="btn_part" onclick="partecipa(false)">Partecipazione</button>
				
				<button id="btn_verf" onclick="partecipa(true)">Verifica</button>
				
				<div id="visualizza_commenti">
					
						<h1>Commenti</h1>
					<div id="container_commenti">
					</div>
				</div>
				
				<div id="scrivi_commento">
				
						<button id="pulsante1" onclick="sendCommento()">invio</button>
				
						
						<textarea id="commento-box"></textarea>
						
						
				
				</div>
				
				<footer>
				
					<h3>LetsMeet2019</h3>
				
				</footer>
			
			</div>
			
		</body>
	
</html>