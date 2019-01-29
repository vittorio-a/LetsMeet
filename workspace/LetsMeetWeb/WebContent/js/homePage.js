var map;
var userPosition;
var positionMarker;
//var iconPosition = String.raw("file:\\C:\Users\vitto\IUM\LetsMeetWeb\WebContent\images\icon-circle.jpg");
var updatePositionTimer;
var events;
var geocoder;
var infowindow;
var markers;




function init(){
	markers = [];
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
	                      (minute < 10 ? "0" + minute.toString() : minute) +
	                      utcString.substring(16,19);
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

function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) {
                c_end = document.cookie.length;
            }
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";
}

function loadEvents(){
	var js = getCookie("events");
	var list = document.getElementById("event_list");
	if(js){
		var events = JSON.parse(js);
		events.forEach(function(event){
			addMarker(event.coords, getEventDesc(event), event.name);
			var node = document.createElement("LI");
			var textNode = document.createTextNode(event.name);
			node.appendChild(textNode);
			list.appendChild(node);
			node.addEventListener('click', function(){
				map.setCenter(event.coords);
				map.setZoom(15);
				for (var i=0; i<markers.length; i++) {
					var lat = markers[i].marker.getPosition().lat();
					var lng = markers[i].marker.getPosition().lng();
					if(event.name == markers[i].name){
					    var infoWindow = new google.maps.InfoWindow;
				       	infoWindow.setContent(getEventDesc(event));
				       	infoWindow.open(map, markers[i].marker);		
				    }
				}
			})
		});
		return events;
	}
	else{
		return [];
	}
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

/*
function setMarker(){
    geocoder.geocode({'location': latlng}, function(results, status) {
        if (status === 'OK') {
          if (results[0]) {
            var marker = new google.maps.Marker({
              position: latlng,
              map: map
            });
            infowindow.setContent(results[0].formatted_address);
            infowindow.open(map, marker);
          } else {
            window.alert('No results found');
          }
        } else {
          window.alert('Geocoder failed due to: ' + status);
        }
      });
	
    document.getElementById("manager-info").style.display="none";

}*/

function actionClick(cord){
	
    geocoder.geocode({'location': cord}, function(results, status) {
        if (status === 'OK') {
          if (results[0]) {
            
            
            
            infowindow = new google.maps.InfoWindow;
            var pos = results[0].geometry.location;
            document.getElementById("addr_field").value = results[0].formatted_address;
            
           /* var marker = null;
            marker = new google.maps.Marker({
                position: pos,
				map: map
              });
            
   		    marker.addListener('click',function(){
                   var infoWindow = new google.maps.InfoWindow;
               	infoWindow.setContent(event.full_address);
               	infoWindow.open(map, marker);
              });
              var event = {coords: cord, marker: marker, full_address: results[0].formatted_address};//,name,description,type,date};
              
            console.log(event.full_address);
            events.push(event);
            */
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
    var type = document.getElementById("type_field").value;
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
    	      var event = {coords: results[0].geometry.location, full_address: results[0].formatted_address, name:name ,description:description,type:type,date:datetime};

        	   /*	 addMarker(results[0].geometry.location, getEventDesc(event), event.name);
        		    
        	      map.setCenter(event.coords);
        	      map.setZoom(15);
        	      console.log(event);*/
    	      addEvent(event);
        	    events.push(event);
        	    createCookie("events", JSON.stringify(events));
        	    closeManager();
            
          } else {
            window.alert('No results found');
          }
        } else {
          window.alert('Geocoder failed due to: ' + status);
        }
      });
}


function addEvent(event){
	addMarker(event.coords, getEventDesc(event), event.name);
	var node = document.createElement("LI");
	var list = document.getElementById("event_list");

	var textNode = document.createTextNode(event.name);
	node.appendChild(textNode);
	list.appendChild(node);
	node.addEventListener('click', function(){
		map.setCenter(event.coords);
		map.setZoom(15);
		for (var i=0; i<markers.length; i++) {
			var lat = markers[i].marker.getPosition().lat();
			var lng = markers[i].marker.getPosition().lng();
			if(event.name == markers[i].name){
			    var infoWindow = new google.maps.InfoWindow;
		       	infoWindow.setContent(getEventDesc(event));
		       	infoWindow.open(map, markers[i].marker);		
		    }
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
	    var infoWindow = new google.maps.InfoWindow;
       	infoWindow.setContent(description);
       	infoWindow.open(map, marker);
      });
}


function getEventDesc(event){4
	var d = new Date(event.date);
	var formatted_datetime = d.getDate()+"/"+(d.getMonth()+1)+"/"+d.getFullYear()+" "+d.getHours()+":"+d.getMinutes();
	var descHtml = "<b>" + event.name + "</b><br/>" +
		"<b>Tipo: </b>" + event.type + "<br/>" + 
		"<b>Indirizzo: </b>" + event.full_address + "<br/>" +
		"<b>Data e ora d'inizio: </b>" + formatted_datetime + "<br/>" +
		"<b>Descrizione: </b>" + event.description;
	return descHtml;
}


function show(id) {
    document.getElementById(id).style.visibility = "visible";
  }
function hide(id) {
    document.getElementById(id).style.visibility = "hidden";
  }

