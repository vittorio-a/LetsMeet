var map;
var userPosition;
var positionMarker;
var updatePositionTimer;
var events;
var geocoder;
var infowindow =null;
var markers;
var mapMarkes;




function init(){
    infowindow = new google.maps.InfoWindow;
	markers = [];
	mapMarkers = [];
	if(navigator.geolocation){
    	navigator.geolocation.getCurrentPosition(initMap);
    }else{
    	alert("Browser non abilitato alla geolocalizzazione pls, miett accett o nun funzion");
    }
	
	
	window.addEventListener("load", function() {
	    var now = new Date();
	    var utcString = now.toISOString().substring(0,19);
	    var year = now.getFullYear();
	    var month = now.getMonth() + 1;
	    var day = now.getDate();
	    var hour = now.getHours();
	    var minute = now.getMinutes();
	    var second = now.getSeconds();
	    var localDatetime = year + "-" +
	                      (month < 10 ? "0" + month.toString() : month) + "-" +
	                      (day < 10 ? "0" + day.toString() : day) + "T" +
	                      (hour < 10 ? "0" + hour.toString() : hour) + ":" +
	                      (minute < 10 ? "0" + minute.toString() : minute);
	    var datetimeField = document.getElementById("date_field");
	    datetimeField.value = localDatetime;
	});
	
}

function createCookie(name, value, days) {
    var expires;
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toGMTString();
    }
    else {
        expires = "";
    }
    document.cookie = name + "=" + value + expires + "; path=/";
}


var tm = 1;

function changeTheme(){
	if(tm == 2){
	
		$("#event").css("background-color","#4682b4");
		$("#header").css("background-color","#4682b4");

		tm = 1;
		loadEvents();
	}else{
	
		$("#event").css("background-color","#ffb6c1");
		$("#header").css("background-color","#ffb6c1");

		tm = 2;
		loadEvents();
	}
}

function onClickMarker(elem) {
	var i = elem.getAttribute("data-position");
	var event = markers[i];
	var lat = event.posizione.latitudine;
	var lng = event.posizione.longitudine;
	var coords = {lat:lat, lng:lng};
	map.setCenter(coords);
	//map.setZoom(13);
 //  	infowindow.setContent(getEventDesc(event));
//   	infowindow.open(map, markers[i].marker);		
}

var type = 0;
var dist = 25;
function loadEvents(){
	
	
	
	var list = document.getElementById("event_list");

    $(list).empty();
	mapMarkers.forEach(function(marker){
		marker.setMap(null);
	});
	mapMarkers = [];
    var selected = $("input[name='selection_type']:checked"). val();
    var dataFiltro = {tipo_filtro:"ALL"};
    switch (selected) {
	case "0":
		type = 0;
		break;
	case "1":
		type = 1;
		dist = $("#number_box").val() * 1000;

		break;
	default:
		break;
	}
    
	$.post(
			"/LetsMeetWeb/auth/search/searchEventControl",
			dataFiltro,
			function(result){
				var events = JSON.parse(result);
				console.log(events);
				if(type == 1){
					var eventsNew = [];
					events.forEach(function(event){
						if(google.maps.geometry.spherical.computeDistanceBetween(new google.maps.LatLng(userPosition.lat, userPosition.lng), new google.maps.LatLng(event.posizione.latitudine, event.posizione.longitudine)) <= dist)
							eventsNew.push(event);
					});
					events = eventsNew;
				}
				markers = events;
				var i = 0;
				events.forEach(function(event){
					addMarker({lat:event.posizione.latitudine, lng:event.posizione.longitudine}, getEventDesc(event) + getEventButtons(event) , event.nome);
					var node = document.createElement("li");
					node.setAttribute("data-position", i);
					var cl;
					if(tm == 1) cl = "li-1";
					else cl = "li-2";
					node.setAttribute("class",cl);
					i++;
					node.setAttribute("onclick", "onClickMarker(this)");
					var textNode = document.createTextNode(event.nome);
					
					node.appendChild(textNode);
					list.appendChild(node);
				});
			}).fail(function(){
				console.log("errore");
			});
	
}

function initMap(position){
	var lat = position.coords.latitude;
    var lng = position.coords.longitude;
    userPosition = ({lat: lat, lng: lng});
    map = new google.maps.Map(document.getElementById('map-container'), {
        zoom: 15,
        center: userPosition,
        styles: [{"featureType":"water","elementType":"geometry","stylers":[{"hue":"#165c64"},{"saturation":34},{"lightness":-69},{"visibility":"on"}]},{"featureType":"landscape","elementType":"geometry","stylers":[{"hue":"#b7caaa"},{"saturation":-14},{"lightness":-18},{"visibility":"on"}]},{"featureType":"landscape.man_made","elementType":"all","stylers":[{"hue":"#cbdac1"},{"saturation":-6},{"lightness":-9},{"visibility":"on"}]},{"featureType":"road","elementType":"geometry","stylers":[{"hue":"#8d9b83"},{"saturation":-89},{"lightness":-12},{"visibility":"on"}]},{"featureType":"road.highway","elementType":"geometry","stylers":[{"hue":"#d4dad0"},{"saturation":-88},{"lightness":54},{"visibility":"simplified"}]},{"featureType":"road.arterial","elementType":"geometry","stylers":[{"hue":"#bdc5b6"},{"saturation":-89},{"lightness":-3},{"visibility":"simplified"}]},{"featureType":"road.local","elementType":"geometry","stylers":[{"hue":"#bdc5b6"},{"saturation":-89},{"lightness":-26},{"visibility":"on"}]},{"featureType":"poi","elementType":"geometry","stylers":[{"hue":"#c17118"},{"saturation":61},{"lightness":-45},{"visibility":"on"}]},{"featureType":"poi.park","elementType":"all","stylers":[{"hue":"#8ba975"},{"saturation":-46},{"lightness":-28},{"visibility":"on"}]},{"featureType":"transit","elementType":"geometry","stylers":[{"hue":"#a43218"},{"saturation":74},{"lightness":-51},{"visibility":"simplified"}]},{"featureType":"administrative.province","elementType":"all","stylers":[{"hue":"#ffffff"},{"saturation":0},{"lightness":100},{"visibility":"simplified"}]},{"featureType":"administrative.neighborhood","elementType":"all","stylers":[{"hue":"#ffffff"},{"saturation":0},{"lightness":100},{"visibility":"off"}]},{"featureType":"administrative.locality","elementType":"labels","stylers":[{"hue":"#ffffff"},{"saturation":0},{"lightness":100},{"visibility":"off"}]},{"featureType":"administrative.land_parcel","elementType":"all","stylers":[{"hue":"#ffffff"},{"saturation":0},{"lightness":100},{"visibility":"off"}]},{"featureType":"administrative","elementType":"all","stylers":[{"hue":"#3a3935"},{"saturation":5},{"lightness":-57},{"visibility":"off"}]},{"featureType":"poi.medical","elementType":"geometry","stylers":[{"hue":"#cba923"},{"saturation":50},{"lightness":-46},{"visibility":"on"}]}]
    });
    
    geocoder = new google.maps.Geocoder;


    map.addListener('click', function(event){
        var cord = event.latLng
        actionClick(cord);
        showEventManager();
      });
    
    
    updateUserPosition();
    window.setInterval(updateUserPosition, 1000);
    
    
	events = loadEvents();

}

function updateUserPosition(){
	navigator.geolocation.getCurrentPosition(function (position){
		var lat = position.coords.latitude;
		var lng = position.coords.longitude;
		userPosition = ({lat: lat, lng: lng});
		if(positionMarker != null){
			positionMarker.setMap(null);
		}
		
		positionMarker = new google.maps.Marker({position : userPosition, 
			map: map, 
			icon: {
				path: google.maps.SymbolPath.CIRCLE,
				scale:10
			}});
	});
}


function showEventManager(){
	
	
	
	
    document.getElementById("manager-info").style.display="block";
}


function actionClick(cord){
	
    geocoder.geocode({'location': cord}, function(results, status) {
        if (status === 'OK') {
          if (results[0]) {
            
            
            var pos = results[0].geometry.location;
            document.getElementById("addr_field").value = results[0].formatted_address;
  
          } else {
            window.alert('No results found');
          }
        } else {
          window.alert('Geocoder failed due to: ' + status);
        }
      });
}

function closeManager(){
    document.getElementById("manager-info").style.display="none";
}


function setMarker(){
    var address = document.getElementById("addr_field").value;
    var type = $("#type_field").children("option:selected").val();
    var description = document.getElementById("desc_field").value;
    var datetime = document.getElementById("date_field").value;
    var name = document.getElementById("name_field").value;

	if(!(address && type && description && datetime && name)){
		alert("Compila tutti i campi");
		return;
	}
	var marker = null;

	geocoder.geocode({'address': address}, function(results, status) {
        if (status === 'OK') {
          if (results[0]) {
        	  	var nomeComune;
        	  	var nomeProvincia;
        	  	var nomeRegione;
        	  	var nomeNazione;
        	  	var sigla;
    	      	var event = {coords: results[0].geometry.location, full_address: results[0].formatted_address, name:name ,description:description,type:type,date:datetime};
			    console.log(results);
			    
			    results[0].address_components.forEach(function(component){
			    	component.types.forEach(function(type){
			    		switch(type){
				    	case "administrative_area_level_3":
				   			nomeComune = component.long_name;
				    		break;
				   		case "administrative_area_level_2":
				   			nomeProvincia = component.long_name;
				   			sigla = component.short_name;
				   			break;
				   		case "administrative_area_level_1":
				   			nomeRegione = component.long_name;
			    			break;
			    		case "country":
			    			nomeNazione = component.long_name;
			    			break;
				    	}
			    	});
			    	
			    });
			    			    
			    var eventToSend = {
			    		  nome : name,
			    		  ora_inizio : datetime,
			    		  ora_fine: datetime,
			    		  posizione : JSON.stringify({
			    			  longitudine : results[0].geometry.location.lng(), 
			    			  latitudine : results[0].geometry.location.lat(),
			    			  formattedAdress : results[0].formatted_address,
			    			  nomeComune : nomeComune,
			    			  nomeProvincia: nomeProvincia,
			    			  sigla : sigla,
			    			  nomeRegione : nomeRegione,
			    			  nomeNazione : nomeNazione,
			    		  }),
			    		  descrizione: description,
			    		  tipo_evento: type,
			    	}
			   console.log(eventToSend);
    	       $.post(
    	  			"/LetsMeetWeb/auth/eventi/eventoControl",
    	  			eventToSend,
    	  			function(result){
    	  				console.log(result);
    	  				loadEvents();
    	  				show("conferma_creazione");
    	  				setTimeout(function(){
    	  					hide("conferma_creazione");
    	  				}, 1000);
    	  		
    	  			}).fail(function(){
    	  				console.log("errore");
    	  			});

        	    closeManager();
            
          } else {
            window.alert('No results found');
          }
        } else {
          window.alert('Geocoder failed due to: ' + status);
        }
      });
}


function addMarker(cords, description, name){
	var marker = new google.maps.Marker({
        position: cords,
		map: map
      });
		markers.push({marker:marker, name:name});
	    marker.addListener('click',function(){
       	infowindow.setContent(description);
       	infowindow.open(map, marker);
      });
	 mapMarkers.push(marker);
}


function getEventDesc(event){
	var d = new Date(event.oraInizio.seconds * 1000);
	var formatted_datetime = d.getDate()+"/"+(d.getMonth()+1)+"/"+d.getFullYear()+" "+d.getHours()+":"+d.getMinutes();
	var descHtml = "<b>" + event.nome + "</b><br/>" +
		"<b>Tipo: </b>" + event.tipo.nomeTipo + "<br/>" + 
		"<b>Indirizzo: </b>" + event.posizione.formattedAdress + "<br/>" +
		"<b>Data e ora d'inizio: </b>" + d.toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ") + "<br/>" +
		"<b>Descrizione: </b>" + event.descrizione;
	return descHtml;
}

function getEventButtons(event){
	buttonPage = "<a style=\"float:right;\"href=\"/LetsMeetWeb/auth/infoEvento.jsp?idEvento=" + event.idEvento +"\"><button id=\"infoButton\"> Info Evento </button></a>";
	return buttonPage;
}


function show(id) {
    document.getElementById(id).style.visibility = "visible";
  }
function hide(id) {
    document.getElementById(id).style.visibility = "hidden";
  }

