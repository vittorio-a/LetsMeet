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
	fillEventInfo();
	fillCommenti();

	//devo prendere l'id dell'evento selezionato
	
var userPosition;
var eventPosition = null;

var userIsVerified = true, userIsPart = true;
var eventDate = null;

function updateUserPosition(){
	navigator.geolocation.getCurrentPosition(function (position){
		var lat = position.coords.latitude;
		var lng = position.coords.longitude;
		userPosition = ({lat: lat, lng: lng});
		
		if(eventPosition != null){
			if(userIsPart && !userIsVerified && 
					eventDate != null && eventDate < new Date() &&
					google.maps.geometry.spherical.computeDistanceBetween(new google.maps.LatLng(userPosition.lat, userPosition.lng),new google.maps.LatLng(eventPosition.lat, eventPosition.lng)) <= 100){
			new google.maps.LatLng(userPosition.lat, userPosition.lng)

				$("#btn_verf").prop("disabled", false);
			}else{
				$("#btn_verf").prop("disabled", true);
			}

		}
	});
}
updateUserPosition;
window.setInterval(updateUserPosition, 1000);

window.setInterval(fillCommenti, 1000);
window.setInterval(fillEventInfo, 1000);


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
					    var dformat = d.toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");
						
						$("#desc-txt").text(evento.descrizione);
						$("#part-txt").text(evento.nPartecipanti);
						$("#verf-txt").text(evento.nVerificati);
						$("#voto-txt").text(evento.feedback);
						$("#date-txt").text(dformat);
						$("#nome-txt").text(evento.nome);
						$("#tipo").text(evento.tipo.nomeTipo);
						//Prendere i commenti dall'array
						
						var userPart = false, userIsVer = false;
						response.data.partecipazioni.forEach(function(part){
							if(part.idUtente == idUser){
								userPart = true;
								if(part.verificato) userIsVer = true;
							}
						});
						if(userPart){
							$("#btn_part").prop("disabled", true);
							$('#btn_part').addClass("gia");
							if(userIsVer){
								$("#btn_verf").prop("disabled", true);
								$('#btn_verf').addClass("gia");
							}
						}else{
							$('#like').addClass("disabled");
							$('#like').attr("src", "../images/thumbs-up-gray.png");
							$('#like').attr("onclick", "");

							$('#dislike').addClass("disabled");
							$('#dislike').attr("src", "../images/thumbs-up-gray.png");
							$('#dislike').attr("onclick", "");

						}
						userIsVerified = userIsVer;
						userIsPart = userPart;
						var pos = response.data.evento.posizione;
						eventPosition = {lat:pos.latitudine, lng:pos.longitudine};
						eventDate = new Date(response.data.evento.oraInizio.seconds);
						updateUserPosition();
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

function partecipa(isVer){
	var isVerificato;
	if(isVer) isVerificato = isVer;
	$.ajax({
		//essa passa l'id dell'evento alla servelt
		url: "/LetsMeetWeb/auth/eventi/partecipazioneControl",
      	type: 'GET',
      	data : {isVerificato:isVerificato,idEvento:idEvento},
      	dataType: "json",
		success: function(response){
			console.log(response);
			if(response.errorcode == 0){
				userIsPart = true;
				$("#btn_part").prop("disabled", true);
				$('#btn_part').addClass("gia");
			
				$('#like').removeClass("disabled");
				$('#like').attr("src", "../images/thumbs-up.png");
				$('#like').attr("onclick", "vota(true)");

				$('#dislike').removeClass("disabled");
				$('#dislike').attr("src", "../images/thumbs-up.png");
				$('#dislike').attr("onclick", "vota(false)");

				if(isVer){
					userIsVerified = true;
					$("#btn_verf").prop("disabled", true);
					$('#btn_verf').addClass("gia");

				}
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