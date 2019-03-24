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
				show("conferma_creazione");
				setTimeout(function(){
					hide("conferma_creazione");
				}, 1000);
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

function show(id) {
    document.getElementById(id).style.visibility = "visible";
  }
function hide(id) {
    document.getElementById(id).style.visibility = "hidden";
  }
