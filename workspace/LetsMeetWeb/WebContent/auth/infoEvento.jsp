<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%final String ID_EVENTO_PARAM = "idEvento";%>

<html>

	<head>
			
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
		<meta charset="	UTF-8">
		<link href="../css/infoEvento.css" rel="stylesheet" type="text/css">
		<title>Info</title>
	</head>
	<script type="text/javascript">
	<%
		String idEvn = request.getParameter(ID_EVENTO_PARAM);
		if(idEvn == null) idEvn = "1";
	%>
	var idEvento = <%=idEvn%>;
	fillEventInfo();
	fillCommenti();

	//devo prendere l'id dell'evento selezionato
function fillEventInfo(){
		$.ajax({
				//essa passa l'id dell'evento alla servelt
				url: "/LetsMeetWeb/eventi/eventoInfoControl",
		      	type: 'GET',
		      	data : {idEvento:idEvento},
		      	dataType: "json",
				success: function(response){
					console.log(response);
					if(response.errorcode == 0){
						console.log("Tutt appost preso l'evento");
						var evento = response.data.evento;

						var d = new Date(evento.oraInizio.seconds * 1000);
					    var dformat = [d.getMonth()+1,
					               d.getDate(),
					               d.getFullYear()].join('/')+' '+
					              [d.getHours(),
					               d.getMinutes(),
					               d.getSeconds()].join(':');
						
						$("#desc-txt").text(evento.descrizione);
						$("#part-txt").text(evento.nPartecipanti);
						$("#verf-txt").text(evento.nVerificati);
						$("#voto-txt").text(evento.feedback);
						$("#date-txt").text(dformat);
						$("#nome-txt").text(evento.nome);
						$("#tipo").text(evento.tipo.nomeTipo);
						//Prendere i commenti dall'array
						
					}else{
						alert(response.error);
					}
			},
			error : function() {
				alert("C'è stato un problema con il caricamento dell'evento");
		    }
		});
		
	}
	
function fillCommenti(){
	$.ajax({
		//essa passa l'id dell'evento alla servelt
		url: "/LetsMeetWeb/auth/search/searchCommentControl",
      	type: 'GET',
      	data : {idEvento:idEvento},
      	dataType: "json",
		success: function(response){
			console.log(response);
			var list = document.getElementById("container_commenti");
		    $(list).empty();
			if(response.errorcode == 0){
				var commenti = 	response.data;
				commenti.forEach(function(commento){
					var node = document.createElement("li");
					var nodeUsername = document.createElement("span");
					nodeUsername.setAttribute("class", "commento-username");
					var textNodeUsername = document.createTextNode(commento.username);
					nodeUsername.appendChild(textNodeUsername);
					
					var nodeCommento = document.createElement("span");
					nodeCommento.setAttribute("class", "commento-contenuto");
					var textNodeCommento = document.createTextNode(commento.contenuto);
					nodeCommento.appendChild(textNodeCommento);
					
					node.appendChild(nodeUsername);
					node.appendChild(document.createElement("br"));
					node.appendChild(nodeCommento);
					list.appendChild(node);
				});
			
			}else{
				alert(response.error);
			}
		},
		error : function() {
			alert("C'è stato un problema con il caricamento dei commenti");
  	  }
	});
		
}


function vota(voto){
	$.ajax({
		//essa passa l'id dell'evento alla servelt
		url: "/LetsMeetWeb/auth/eventi/ratingControl",
      	type: 'GET',
      	data : {voto:voto,idEvento:idEvento},
      	dataType: "json",
		success: function(response){
			console.log(response);
			if(response.errorcode == 0){
				fillEventInfo();
			}else{
				alert(response.error);
			}
		},
		error : function() {
			alert("C'è stato un problema con il caricamento dei commenti");
  	  }
	});
}

function sendCommento(){
	var commento = $("#commento-box").val();
	if(commento == "") return;
	$.ajax({
		//essa passa l'id dell'evento alla servelt
		url: "/LetsMeetWeb/auth/eventi/commentoControl",
      	type: 'POST',
      	data : {text:commento,idEvento:idEvento},
      	dataType: "json",
		success: function(response){
			console.log(response);
			if(response.errorcode == 0){
				fillCommenti();
			}else{
				alert(response.error);
			}
		},
		error : function() {
			alert("C'è stato un problema con l'invio del commento");
  	  }
	});
	
}
	</script>
	
		<body>
			
			<div id="container">
			
					<div id="container_event1">
					
						<h1 id="nome-txt">evento1</h1>
						
						<button id="tipo">Tipo</button>
						
						<a href="event.html"><img id="back" alt="freccia" src="../images/sign-out-option.png"></a>
						
						<div id="calendar">
							<img  alt="calendario" src="../images/calendar-with-a-clock-time-tools.png">
							<span id="date-txt">0</span>
						</div>
						
						<img id="like" alt="pollice" src="../images/thumbs-up.png" onclick="vota(1)">
						<img id="dislike" class="ruota180" alt="pollice" src="../images/thumbs-up.png" onclick="vota(0)">
						
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
						<p id="desc-txt"></p>
				</div>
				
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