<%@page import="it.unisa.studenti.letsmeet.control.gestione_account.LoginControl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta charset="UTF-8">
		<link href="css/homePage.css" rel="stylesheet" type="text/css">
		<title>LetsMeet</title>
			<script type="text/javascript" src="js/homePage.js"></script>
		
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDNXKJ5FYwMS0kMu4FVl-RvpJ8oVHvtjw4&callback=init" async defer></script>
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map-container {
        height: 100%;
      }
      #floating-panel {
        position: absolute;
        top: 10px;
        left: 25%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }
      #floating-panel {
        position: absolute;
        top: 5px;
        left: 50%;
        margin-left: -180px;
        width: 350px;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
      }
      #latlng {
        width: 225px;
      }
      
    </style>
		
	</head>
	
			<body>
				
				<div id="container">
						<div id="manager-info">
							<form>
							<div id=fsx>
										<label>Nome<input id="name_field" name="Name" type="text"/></label><br/>
										<label>Indirizzo<input id="addr_field" name="Address" type="text"/></label><br/>
										
							</div>
							<div id="fdx">
								<label>Tipo
												<select name="Type" id="type_field">
													<option value=1>Cultura</option>
													<option value=2>Sport</option>
													<option value=3>Educazione</option>
													<option value=4>Divertimento</option>
													<option value=5>Arte</option>
													<option value=6>Altro</option>
												</select></label><br/>
								
								<label>Data e ora d'inizio<input id="date_field" name="datetime" type="datetime-local"></label><br/>
							</div>
							<div id="fdw">
								<label>Descrizione<textarea id="desc_field" rows="3" cols="45"></textarea></label><br/>
								<input id="crea_btn" type="button" name="crea" value="Crea"  onclick="setMarker()">
								<input id="cancel_btn" type="button" name="cancel" value="Cancel" onclick="closeManager()">
							</div>
							</form>
						</div>
						<div id="header">
					
							<h1><a href="homePage.html">LetsMeet</a></h1> 
							
							<a href="auth/profilo.jsp"><img id="profilo" alt="profilo" src="images/profilo.png"></a> 
							
							<a href="impostazioni.html"><img id="settings" alt="settings" src="images/two-cogwheels-configuration-interface-symbol.png"></a>
				
						</div>
								
								
						<section id="map">	
						
							<!-- <h2><a href="mappa.html">Mappa</a></h2> -->
							<img id="info" alt="info" src="images/information-circle.png" onmouseenter="show('info-cont')" onmouseout="hide('info-cont')">
							<div id="info-cont">
								Clicca in un punto qualsiasi della mappa per aprire l'editor degli eventi<br>
								L'indirizzo sarà inserito automaticamente
							</div>
							<div id="map-container"></div>
						</section>
						
						
						<aside id="event">
						
						<h2> Eventi </h2>
						
						<div id="radio_buttons">
						
							 <input id="radio_button1" type="radio" name="selection_type" value="0"> Globale
							 
							 <input id="radio_button2" type="radio" name="selection_type" value="1"> A Distanza
							 
							 
  						
  						</div>
  						
  							<input id="number_box" type="number" name="quantity" min="1" max="9999">
  							
  							<span>Km</span>
						
							<div id="event_container">
								<ul id="event_list">
									
								</ul>
								
							</div>
							
						</aside>
				
				<footer>
				
					<h3>LetsMeet2019</h3>
				
				</footer>
				
			</div>
				
			</body>
</html>